package com.codecool.bookclub.forum.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.service.BookService;
import com.codecool.bookclub.forum.dto.NewTopicDto;
import com.codecool.bookclub.forum.dto.TopicDto;
import com.codecool.bookclub.forum.model.Status;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.repository.TopicRepository;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final BookService bookService;
    private final UserRepository userRepository;

    public TopicDto getTopicById(long topicId) {
        return topicRepository.findById(topicId).map(this::convertToDto).orElse(null);
    }

    public void createTopic(Book book, NewTopicDto newTopicDto, Long userId) {
       Book bookInDb = bookService.saveBook(book);
       log.debug("Saving book to db {}", bookInDb);
       User user = userRepository.findById(userId).orElse(null);
       log.debug("User saving book {}", user);
       if (user != null) {
           Topic topic = new Topic();
           topic.setAuthor(user);
           topic.setBook(bookInDb);
           topic.setTitle(newTopicDto.getTitle());
           topic.setMessage(newTopicDto.getMessage());
           topicRepository.save(topic);
           log.debug("Topic saved {}", topic);
       }
    }

    public List<TopicDto> getTopFourTopics(){
        return topicRepository
                .findFirst4ByOrderByCreationTimeDesc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TopicDto> getTopicsByBookExternalId(String bookId) {
        return topicRepository.findTopicsByBook_ExternalId(bookId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteTopicById(long id) {
        topicRepository.deleteById(id);
    }

    public ResponseEntity<String> reportAbuse(long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isEmpty()) {
            return ResponseEntity.status(404).body("Wątek o podanym id nie istnieje.");
        }
        Topic topic = optionalTopic.get();
        if (topic.getStatus() == Status.VERIFIED) {
            return ResponseEntity.status(404).body("Wątek został już zweryfikowany i zaakceptowany.");
        }
        topic.reportAbuse();
        topicRepository.save(topic);
        return ResponseEntity.status(202).body("Komentarz został zgłoszony do weryfikacji.");
    }

    public TopicDto convertToDto(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .bookId(topic.getBook().getId())
                .bookPictureUrl(topic.getBook().getPictureUrl())
                .bookTitle(topic.getBook().getTitle())
                .bookAuthor(topic.getBook().getAuthor())
                .title(topic.getTitle())
                .authorName(topic.getAuthor().getNickname())
                .creationTime(topic.getCreationTime())
                .authorId(topic.getAuthor().getId())
                .numberOfComments(topic.getComments().size())
                .bookExternalId(topic.getBook().getExternalId())
                .build();
    }

}
