insert into book (title, author, description, pages, picture_url, rating, year) values ('Gdzie śpiewają raki', 'Delia Owens', 'Światowa sensacja, bijący wszelkie rekordy fenomen, który przykuł uwagę ponad 13 milionów czytelników.
Pogłoski o Dziewczynie z Bagien latami krążyły po Barkley Cove, sennym miasteczku u wybrzeży Karoliny Północnej. Dlatego pod koniec 1969 roku, gdy na mokradłach znaleziono ciało przystojnego Chase’a Andrewsa, miejscowi zwrócili się przeciwko Kyi Clark, zwanej Dziewczyną z Bagien.
Lecz Kya nie jest taka, jak o niej szepczą. Wrażliwa i inteligentna, zdołała sama przetrwać wiele lat na bagnach, które nazywa domem, choć jej ciało tęskniło za dotykiem i miłością. Przyjaźni szukała u mew, a wiedzę czerpała z natury. Kiedy dzikie piękno dziewczyny intryguje dwóch młodych mężczyzn z miasteczka, Kya otwiera się na nowe doznania — i dzieją się rzeczy niewyobrażalne.', 416, 'https://s.lubimyczytac.pl/upload/books/5019000/5019183/1045632-352x500.jpg', 7.9, 2023);
insert into book (title, author, description,  pages, picture_url, rating, year) values ('Opowieści o pilocie Pirxie', 'Stanisław Lem', 'Jedna z najbardziej znanych i lubianych książek Lema', 515, 'https://s.lubimyczytac.pl/upload/books/148000/148887/352x500.jpg', 7.9, 2012);
insert into book (title, author, description, pages, picture_url, rating, year) values ('Wyspa Robinsona', 'Arkady Fiedler', 'Piękna powieść przygodowa dla młodzieży', 246, 'https://static2.tezeusz.pl/recommended_big/images/7d/cf/2d//72c1d83faaff7a6feb368baa89f17e2bdbf8ea61.jpeg', 6.5, 1973);
insert into book (title, author, description, pages, picture_url, rating, year) values ('451 stopni Fahrenheita', 'Ray Bradbury', 'Przerażająco prorocza powieść o przyszłości w świecie bez książek', 152, 'https://s.lubimyczytac.pl/upload/books/251000/251265/371262-352x500.jpg', 7.6, 2018);
insert into book (title, author, description, pages, picture_url, rating, year) values ('Wiedźmin. Ostatnie życzenie', 'Andrzej Sapkowski', 'Pierwsza książka z cyklu o Wiedźminie wprowadza nas w świat stworzony przez Sapkowskiego.', 330, 'https://s.lubimyczytac.pl/upload/books/240000/240310/490965-352x500.jpg', 8.4, 2014);
insert into book (title, author, description, pages, picture_url, rating, year) values ('Zanim wystygnie kawa', 'Toshikazu Kawaguchi', 'Mała kawiarnia w Tokio pozwala swoim gościom na podróż w czasie. Pod warunkiem, że wrócą, zanim wystygnie kawa.', 219, 'https://s.lubimyczytac.pl/upload/books/5017000/5017747/984776-352x500.jpg', 7.0, 2022);
insert into book (title, author, description, pages, picture_url, rating, year) values ('Fakty muszą zatańczyć', 'Mariusz Szczygieł', 'Nowa książka Mariusza Szczygła jest esejem napisanym z miłości do reportażu. To lektura dla wszystkich, którzy kochają ten gatunek oraz dla tych, którzy mają wątpliwości, czy jest wiarygodny, a także poradnik dla tych, którzy sami chcą pisać.', 400, 'https://s.lubimyczytac.pl/upload/books/5013000/5013390/978467-352x500.jpg', 7.2, 2022);
insert into book (title, author, description, pages, picture_url, rating, year) values ('Secretum', 'Katarzyna Grzegrzółka', 'Mroczny, niepokojący kryminał, który sprawi, że zwątpicie w dobre intencje któregokolwiek z bohaterów. Czy każdy jest tym, za kogo się podaje? Jakie relacje faktycznie łączą poszczególne osoby? Czy można zaufać komukolwiek?', 316, 'https://s.lubimyczytac.pl/upload/books/4993000/4993296/943986-352x500.jpg', 7.7, 2021);

insert into reader (creation_date, email, password, nickname, role) values ('2018-03-28T12:00', 'admin@bm.pl', '$2a$10$vG4nnG2eKe.FRAAOwxjZN.v/7iu9Yz4Y7.Agetvy4fdf0xh9d4s.C', 'admin', 'ADMIN');
insert into reader (creation_date, email, password, nickname, role) values ('2018-03-28T13:00', 'moderator@bm.pl', '$2a$10$C./EkLRzRhvgQkeuZRaK9eKMvRS5NexIwuXXKIcoT1hu2PEXjDqTe', 'moderator', 'MODERATOR');
insert into reader (creation_date, email, password, nickname, role) values ('2018-03-28T13:00', 'user@bm.pl', '$2a$10$47f2yfX21Xn/KGdj.QIDweVr9P6kpYOg4ROlfpA768Qqi0nfDElmm', 'user', 'READER');

INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,event_type) VALUES (55, '2023-04-03 16:02:06.010885', '', '2023-04-03 14:02:03.336000', null, '', '', 1,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (56, '2023-04-03 16:02:30.878221', 'asdfasdf', '2023-04-03 14:02:24.656000', 12, 'dsadfa', 'sadfasdf', 5,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (57, '2023-04-03 16:07:36.519647', '', '2023-04-03 14:07:32.032000', null, '', null, 1,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (58, '2023-04-03 16:09:47.132448', '', '2023-04-03 14:09:41.410000', null, '', null, 1,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (59, '2023-04-03 16:11:08.725736', '', '2023-04-03 14:11:06.820000', null, '', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (60, '2023-04-03 16:11:19.338741', '', '2023-04-03 14:11:17.894000', null, '', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (61, '2023-04-03 16:15:53.808051', '', '2023-04-03 14:13:18.758000', null, '', null, 2, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (62, '2023-04-03 16:16:03.294106', '', '2023-04-03 14:16:00.910000', null, '', null, 2, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (63, '2023-04-03 16:16:07.445691', '', '2023-04-03 14:16:00.910000', null, '', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (64, '2023-04-03 16:18:54.061226', 'aaaaaa', '2023-04-03 14:18:49.052000', 12, 'aaaa', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (65, '2023-04-03 16:19:40.289510', 'yyyyy', '2023-04-03 14:19:33.175000', 12, 'yyyy', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (66, '2023-04-03 16:20:58.165178', 'sss', '2023-04-03 14:20:15.155000', 12, 'aa', null, 2, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (67, '2023-04-03 16:22:33.049059', 'asdasd', '2023-04-03 14:22:20.099000', 12, 'sadasd', null, 2, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (68, '2023-04-03 16:23:21.563105', 'qqq', '2023-04-03 14:23:15.886000', 12, 'qqq', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (69, '2023-04-03 16:31:34.864193', 'sddf', '2023-04-03 14:31:29.196000', 12, 'sdsd', null, 1,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (70, '2023-04-03 16:31:57.420787', 'qq', '2023-04-13 14:31:29.000000', 12, 'qq', null, 1, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (71, '2023-04-03 16:33:50.958654', 'kk', '2023-04-03 14:33:46.836000', 12, 'kk', null, 1,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (72, '2023-04-03 16:35:07.073887', 'ss', '2023-04-12 02:00:00.000000', 12, 'aa', null, 1,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (73, '2023-04-03 16:36:27.191616', 'f', '2023-04-03 14:36:22.271000', 12, 'f', null, 1, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (74, '2023-04-03 16:37:10.827933', 'aa', '2023-04-03 14:37:05.497000', 12, 'aa', null, 1, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (75, '2023-04-03 16:38:00.890719', 'vv', '2023-04-03 14:37:53.701000', 12, 'vv', null, 1, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (76, '2023-04-03 16:43:23.696186', 'rr', '2023-04-03 14:43:18.527000', 12, 'rr', null, 5,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (77, '2023-04-03 16:44:40.046576', 'yy', '2023-04-03 14:44:34.753000', 12, 'yy', null, 5, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (78, '2023-04-03 16:46:15.524075', 'tt', '2023-04-03 14:46:10.706000', 12, 'tt', null, 5,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (79, '2023-04-03 16:47:57.294745', 'ee', '2023-04-03 14:47:53.254000', 12, 'ee', null, 5,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (80, '2023-04-03 16:52:03.249319', 'qq', '2023-04-03 00:00:00.000000', 12, 'qq', null, 5,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (81, '2023-04-03 16:53:00.004019', 'uu', '2023-04-03 14:52:54.971000', 12, 'uu', null, 5,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (82, '2023-04-03 16:56:39.332660', 'ee', '2023-04-03 16:56:34.606000', 12, 'ee', null, 5,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (83, '2023-04-03 18:38:56.166740', 'qqqqqqqqqqqqq', '2023-04-17 20:00:39.000000', 12, 'qqqqqqqqq', null, 2, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (84, '2023-04-03 20:28:12.554315', 'xdtxdtdx', '2023-04-19 20:27:59.000000', 12, 'drdxtdxt', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (85, '2023-04-03 20:50:51.714432', 'aaaa', '2023-04-27 12:00:35.000000', 6, 'adsd', null, 5,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (86, '2023-04-03 20:58:07.127335', 'aa', '2023-04-18 09:57:49.000000', 1, 'aa', null, 4, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (87, '2023-04-03 21:04:05.743502', 'xxx', '2023-04-11 21:03:56.000000', 12, 'xxx', null, 1,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (88, '2023-04-03 21:16:37.541198', 'ttttttttt', '2023-04-03 21:16:29.700000', 3, 'tttttttttttt', null, 2, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (89, '2023-04-04 09:20:14.109332', 'rrr', '2023-04-14 09:19:53.000000', 4, 'rrr', null, 1, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (90, '2023-04-04 11:55:09.168309', 't', '2023-04-04 11:55:02.316000', 12, 'zt', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (91, '2023-04-04 15:49:02.473885', 'aaa', '2023-04-04 15:48:57.016000', 12, 'zzzzzaaa', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (92, '2023-04-04 16:53:32.571301', 'teraz', '2023-04-04 16:53:26.610000', 12, 'teraz', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (93, '2023-04-05 11:33:25.604989', 'sdsd', '2023-04-05 11:33:10.155000', 12, 'sdsd', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (94, '2023-04-05 11:34:27.433436', 'asas', '2023-04-05 11:34:19.037000', 12, 'as', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (95, '2023-04-05 11:35:45.694884', 'sadasd', '2023-04-05 11:35:39.030000', 12, 'dsad', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (96, '2023-04-05 11:37:17.304063', 'asdas', '2023-04-05 11:37:11.616000', 12, 'asdsd', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (97, '2023-04-05 11:38:50.867580', 'tt', '2023-04-05 11:38:45.049000', 1, 'tt', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (98, '2023-04-05 11:39:25.929455', 'sad', '2023-04-05 11:39:21.174000', 12, 'asd', null, 2,  null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (99, '2023-04-05 11:39:45.121488', '21321', '2023-04-05 11:39:39.582000', 12, '21312', null, 2, null);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id, event_type) VALUES (100, '2023-04-05 11:41:19.541736', 'sadasd', '2023-04-05 11:41:14.518000', 12, 'asdasd', null, 2, 1);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (101, '2023-04-05 11:50:38.419115', 'teraz dodałam', '2023-04-05 11:50:23.892000', 12, 'teraz dodałam', null, 2,  3);
INSERT INTO public.event (id, creation_date_and_time, description, event_date, max_participants, title, url, book_id,  event_type) VALUES (102, '2023-04-06 14:04:34.640760', 'dfgfdg', '2023-04-06 14:04:24.775000', 2, 'dg', null, 1, 1);


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

-- insert into event_participants (events_id, participants_id)
-- values (65,3);
-- insert into event_participants (events_id, participants_id)
-- values (66,3);
-- insert into event_participants (events_id, participants_id)
-- values (67,3);
-- insert into event_participants (events_id, participants_id)
-- values (68,3);
-- insert into event_participants (events_id, participants_id)
-- values (69,3);

insert into comment (creation_time, message, author_id, topic_id)
values ('2023-03-28T12:00', 'nie wiem', 2, 3);

-- insert into topic_comments (topic_id, comments_id)
-- values (3,1)
