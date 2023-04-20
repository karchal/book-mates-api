insert into book (title, author, description, pages, picture_url, rating, year) values ('Gdzie śpiewają raki', 'Delia Owens', 'Światowa sensacja, bijący wszelkie rekordy fenomen, który przykuł uwagę ponad 13 milionów czytelników.
Pogłoski o Dziewczynie z Bagien latami krążyły po Barkley Cove, sennym miasteczku u wybrzeży Karoliny Północnej. Dlatego pod koniec 1969 roku, gdy na mokradłach znaleziono ciało przystojnego Chase’a Andrewsa, miejscowi zwrócili się przeciwko Kyi Clark, zwanej Dziewczyną z Bagien.
Lecz Kya nie jest taka, jak o niej szepczą. Wrażliwa i inteligentna, zdołała sama przetrwać wiele lat na bagnach, które nazywa domem, choć jej ciało tęskniło za dotykiem i miłością. Przyjaźni szukała u mew, a wiedzę czerpała z natury. Kiedy dzikie piękno dziewczyny intryguje dwóch młodych mężczyzn z miasteczka, Kya otwiera się na nowe doznania — i dzieją się rzeczy niewyobrażalne.', 416, 'https://s.lubimyczytac.pl/upload/books/5019000/5019183/1045632-352x500.jpg', 7.9, 2023);
insert into book (external_id, title, author, description,  pages, picture_url, rating, year) values ('fC8kzwEACAAJ','Opowieści o pilocie Pirxie', 'Stanisław Lem', 'Jedna z najbardziej znanych i lubianych książek Lema', 515, 'https://s.lubimyczytac.pl/upload/books/148000/148887/352x500.jpg', 7.9, 2012);
insert into book (external_id, title, author, description, pages, picture_url, rating, year) values ('xZwYnQEACAAJ','Wyspa Robinsona', 'Arkady Fiedler', 'Piękna powieść przygodowa dla młodzieży', 246, 'https://static2.tezeusz.pl/recommended_big/images/7d/cf/2d//72c1d83faaff7a6feb368baa89f17e2bdbf8ea61.jpeg', 6.5, 1973);
insert into book (external_id, title, author, description, pages, picture_url, rating, year) values ('bytUtgEACAAJ','451 stopni Fahrenheita', 'Ray Bradbury', 'Przerażająco prorocza powieść o przyszłości w świecie bez książek', 152, 'https://s.lubimyczytac.pl/upload/books/251000/251265/371262-352x500.jpg', 7.6, 2018);
insert into book (external_id, title, author, description, pages, picture_url, rating, year) values ('sQOTzQEACAAJ','Wiedźmin. Ostatnie życzenie', 'Andrzej Sapkowski', 'Pierwsza książka z cyklu o Wiedźminie wprowadza nas w świat stworzony przez Sapkowskiego.', 330, 'https://s.lubimyczytac.pl/upload/books/240000/240310/490965-352x500.jpg', 8.4, 2014);
insert into book (external_id, title, author, description, pages, picture_url, rating, year) values ('KO9azwEACAAJ','Zanim wystygnie kawa', 'Toshikazu Kawaguchi', 'Mała kawiarnia w Tokio pozwala swoim gościom na podróż w czasie. Pod warunkiem, że wrócą, zanim wystygnie kawa.', 219, 'https://s.lubimyczytac.pl/upload/books/5017000/5017747/984776-352x500.jpg', 7.0, 2022);
insert into book (external_id, title, author, description, pages, picture_url, rating, year) values ('wY5AzwEACAAJ','Fakty muszą zatańczyć', 'Mariusz Szczygieł', 'Nowa książka Mariusza Szczygła jest esejem napisanym z miłości do reportażu. To lektura dla wszystkich, którzy kochają ten gatunek oraz dla tych, którzy mają wątpliwości, czy jest wiarygodny, a także poradnik dla tych, którzy sami chcą pisać.', 400, 'https://s.lubimyczytac.pl/upload/books/5013000/5013390/978467-352x500.jpg', 7.2, 2022);
insert into book (external_id, title, author, description, pages, picture_url, rating, year) values ('pIPyzgEACAAJ','Secretum', 'Katarzyna Grzegrzółka', 'Mroczny, niepokojący kryminał, który sprawi, że zwątpicie w dobre intencje któregokolwiek z bohaterów. Czy każdy jest tym, za kogo się podaje? Jakie relacje faktycznie łączą poszczególne osoby? Czy można zaufać komukolwiek?', 316, 'https://s.lubimyczytac.pl/upload/books/4993000/4993296/943986-352x500.jpg', 7.7, 2021);

insert into reader (creation_date, email, password, nickname, role) values ('2018-03-28T12:00', 'admin@bm.pl', '$2a$10$vG4nnG2eKe.FRAAOwxjZN.v/7iu9Yz4Y7.Agetvy4fdf0xh9d4s.C', 'admin', 'ADMIN');
insert into reader (creation_date, email, password, nickname, role) values ('2018-03-28T13:00', 'moderator@bm.pl', '$2a$10$C./EkLRzRhvgQkeuZRaK9eKMvRS5NexIwuXXKIcoT1hu2PEXjDqTe', 'moderator', 'MODERATOR');
insert into reader (creation_date, email, password, nickname, role) values ('2018-03-28T13:00', 'user@bm.pl', '$2a$10$47f2yfX21Xn/KGdj.QIDweVr9P6kpYOg4ROlfpA768Qqi0nfDElmm', 'user', 'READER');

INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,event_type) VALUES (100, '2023-04-03 16:02:06.010885', 'Opis', '2023-04-03 14:02:03.336000', 4, 'Tytuł', '', 1,  0);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (101, '2023-04-03 16:02:30.878221', 'asdfasdf', '2023-04-03 14:02:24.656000', 12, '', 'sadfasdf', 5,  0);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (102, '2023-04-03 16:07:36.519647', 'Opis', '2023-04-03 14:07:32.032000', 5, 'Tytuł', '', 1,  0);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (103, '2023-04-03 16:09:47.132448', 'Opis', '2023-04-03 14:09:41.410000', 6, 'Tytuł', '', 1,  0);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (104, '2023-04-03 16:11:08.725736', 'Opis', '2023-04-03 14:11:06.820000', 7, 'Tytuł', '', 2,  0);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (105, '2023-04-03 16:11:19.338741', 'Opis', '2023-04-03 14:11:17.894000', 8, 'Tytuł', '', 2,  0);

insert into event_details(participant_type, event_id,user_id)
values (0,100,2);
insert into event_details(participant_type, event_id,user_id)
values (0,101,2);
insert into event_details(participant_type, event_id,user_id)
values (0,102,2);
insert into event_details(participant_type, event_id,user_id)
values (0,103,2);
insert into event_details(participant_type, event_id,user_id)
values (0,104,2);
insert into event_details(participant_type, event_id,user_id)
values (0,105,2);


insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 1, 1);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 9, 2, 1);
insert into book_details (shelf, book_id, user_id) values (2, 3, 1);
insert into book_details (shelf, user_rating, book_id, user_id) values (3, 9, 5, 1);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 7, 1, 2);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 2, 2);
insert into book_details (shelf, user_rating, book_id, user_id) values (4, 7, 3, 2);
insert into book_details (shelf, user_rating, book_id, user_id) values (4, 8, 4, 2);
insert into book_details (shelf, user_rating, book_id, user_id) values (4, 8, 5, 2);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 1, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 2, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 3, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 4, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 5, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 6, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 7, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (1, 8, 8, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (0, 8, 7, 3);
insert into book_details (shelf, user_rating, book_id, user_id) values (0, 8, 8, 3);

insert into topic (creation_time, message, title, author_id ,book_id)
values ('2023-03-28T12:00', 'Nie wiem co myśleć', 'Co myślicie o głównej bohaterce ?',1,1);
insert into topic (creation_time, message, title, author_id ,book_id)
values ('2023-03-28T12:00', 'Nie wiem co myśleć', 'Co myślicie o głównej bohaterce ?',1,2);
insert into topic (creation_time, message, title, author_id ,book_id)
values ('2023-03-28T12:00', 'Nie wiem co myśleć', 'Co myślicie o głównej bohaterce ?',3,3);
insert into topic (creation_time, message, title, author_id ,book_id)
values ('2023-03-28T12:00', 'Nie wiem co myśleć', 'Co myślicie o głównej bohaterce ?',3,4);



insert into comment (creation_time, message, author_id, topic_id)
values ('2023-03-28T12:00', 'nie wiem', 2, 3);

-- insert into topic_comments (topic_id, comments_id)
-- values (3,1)
