package com.codecool.bookclub.event.repository;

import com.codecool.bookclub.event.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface EventPaginationRepository extends PagingAndSortingRepository<Event,Long> {

}
