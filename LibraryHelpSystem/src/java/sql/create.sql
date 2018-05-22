CREATE TABLE sections
(
  section_id NUMBER NOT NULL,
  name VARCHAR(100) NOT NULL,
  parent_id NUMBER,
  description VARCHAR(200),
  PRIMARY KEY (section_id),
  FOREIGN KEY (parent_id) REFERENCES sections(section_id)
);

CREATE SEQUENCE sections_sequence
START WITH 55
INCREMENT BY 1
NOMAXVALUE
MINVALUE 55;

CREATE TABLE books
(
  book_id NUMBER NOT NULL,
  title VARCHAR(100) DEFAULT '-' NOT NULL,
  pub_date DATE DEFAULT TO_DATE('01.01.1970', 'DD.MM.YYYY') NOT NULL,
  pages NUMBER DEFAULT 0 NOT NULL,
  section_id NUMBER NOT NULL,
  PRIMARY KEY (book_id),
  FOREIGN KEY (section_id) REFERENCES sections(section_id)
);

CREATE SEQUENCE books_sequence
START WITH 0
INCREMENT BY 1
NOMAXVALUE
MINVALUE 0;

CREATE TABLE authors
(
  author_id NUMBER NOT NULL,
  first_name VARCHAR(50) DEFAULT '-' NOT NULL,
  last_name VARCHAR(50) DEFAULT '-' NOT NULL,
  surname VARCHAR(50) DEFAULT '-' NOT NULL,
  birth_date DATE,
  PRIMARY KEY (author_id)
);

CREATE SEQUENCE authors_sequence
START WITH 0
INCREMENT BY 1
NOMAXVALUE
MINVALUE 0;

CREATE TABLE authors_books
(
  author_id NUMBER NOT NULL,
  book_id NUMBER NOT NULL, 
  FOREIGN KEY (author_id) REFERENCES authors(author_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id),
  PRIMARY KEY (author_id, book_id)
);

CREATE TABLE user_types 
(
  type_id NUMBER NOT NULL,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (type_id)
);

CREATE SEQUENCE user_types_sequence
START WITH 0
INCREMENT BY 1
NOMAXVALUE
MINVALUE 0;

CREATE TABLE library_users 
(
  user_id NUMBER NOT NULL,
  login VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  first_name VARCHAR(50) DEFAULT '-' NOT NULL,
  last_name VARCHAR(50) DEFAULT '-' NOT NULL,
  surname VARCHAR(50) DEFAULT '-' NOT NULL,
  type_id NUMBER NOT NULL,
  PRIMARY KEY (user_id),
  FOREIGN KEY (type_id) REFERENCES user_types(type_id)
);

CREATE SEQUENCE library_users_sequence
START WITH 0
INCREMENT BY 1
NOMAXVALUE
MINVALUE 0;

CREATE TABLE exemplars 
(
  inventory_number NUMBER NOT NULL,
  book_id NUMBER NOT NULL,
  user_id NUMBER,
  PRIMARY KEY (inventory_number),
  FOREIGN KEY (user_id) REFERENCES library_users(user_id)
);

CREATE TABLE activities
(
    a_id NUMBER NOT NULL,
    title VARCHAR(50) DEFAULT '-' NOT NULL,
    description VARCHAR(300) DEFAULT '-' NOT NULL,
    responsible NUMBER DEFAULT 0 NOT NULL,
    date_time DATE DEFAULT TO_DATE('01/01/1970 00:00:00', 'dd/mm/yyyy hh24:mi:ss') NOT NULL,
    PRIMARY KEY (a_id)
);
CREATE SEQUENCE activities_seq
INCREMENT BY 1
START WITH 0
NOMAXVALUE
MINVALUE 0
NOCYCLE
NOCACHE;
CREATE TABLE participants
(
    a_id NUMBER NOT NULL,
    p_id NUMBER NOT NULL,
    FOREIGN KEY (a_id) REFERENCES activities(a_id),
    FOREIGN KEY (p_id) REFERENCES library_users(user_id),
    PRIMARY KEY (a_id, p_id)
);
CREATE TABLE child_activities
(
    a_id NUMBER NOT NULL,
    ca_id NUMBER NOT NULL,
    FOREIGN KEY (a_id) REFERENCES activities(a_id),
    FOREIGN KEY (ca_id) REFERENCES activities(a_id),
    PRIMARY KEY (a_id, ca_id)
);


INSERT INTO user_types VALUES (user_types_sequence.NEXTVAL, 'User');
INSERT INTO user_types VALUES (user_types_sequence.NEXTVAL, 'Admin');
INSERT INTO library_users VALUES (library_users_sequence.NEXTVAL, 'admin', '12345', 'Arkady', 'Parovozov', 'Arkadievich', 1);
INSERT INTO library_users VALUES (library_users_sequence.NEXTVAL, 'user', '12345', 'Prostoy', 'Smertniy', 'Cherviak', 0);
INSERT INTO authors VALUES (authors_sequence.NEXTVAL, 'John Ronald Reuel', 'Tolkien ', '-', TO_DATE('03-01-1981', 'DD-MM-YYYY'));

INSERT INTO sections VALUES (0, 'Catalog', NULL, '');
INSERT INTO sections VALUES (1, 'Fiction for adults', 0, '');
INSERT INTO sections VALUES (2, 'Art', 0, '');
INSERT INTO sections VALUES (3, 'Notes. Music', 0, '');
INSERT INTO sections VALUES (4, 'Childrens literature', 0, '');
INSERT INTO sections VALUES (5, 'Educational literature', 0, '');
INSERT INTO sections VALUES (6, 'Humanitarian (naskalnye) discipline', 0, '');
INSERT INTO sections VALUES (7, 'Foreign languages', 0, '');
INSERT INTO sections VALUES (8, 'Business literature', 0, '');
INSERT INTO sections VALUES (9, 'Technical literature', 0, '');
INSERT INTO sections VALUES (10, 'Medicine', 0, '');
INSERT INTO sections VALUES (11, 'A healthy lifestyle', 0, '');
INSERT INTO sections VALUES (12, 'Esoteric. Magic. Mysterious', 0, '');

INSERT INTO sections VALUES (13, 'Patriotic prose', 1, '');
INSERT INTO sections VALUES (14, 'Foreign prose', 1, '');
INSERT INTO sections VALUES (15, 'Poetry', 1, '');
INSERT INTO sections VALUES (16, 'Historical novels', 1, '');
INSERT INTO sections VALUES (17, 'The lives of famous people. Memoirs', 1, '');
INSERT INTO sections VALUES (18, 'The domestic detectives', 1, '');
INSERT INTO sections VALUES (19, 'Foreign detectives', 1, '');
INSERT INTO sections VALUES (20, 'Sentimental novels 18+', 1, '');
INSERT INTO sections VALUES (21, 'Fiction. Fantasy', 1, '');
INSERT INTO sections VALUES (22, 'Journalism. Sensation', 1, '');
INSERT INTO sections VALUES (23, 'Comics', 1, '');

INSERT INTO sections VALUES (24, 'Art ALBUMS', 2, '');
INSERT INTO sections VALUES (25, 'Art MONOGRAPHS', 2, '');

INSERT INTO sections VALUES (26, 'Childrens fiction', 4, '');
INSERT INTO sections VALUES (27, 'Childrens educational literature', 4, '');
INSERT INTO sections VALUES (28, 'Coloring', 4, '');
INSERT INTO sections VALUES (29, 'For the little ones', 4, '');
INSERT INTO sections VALUES (30, 'Visual AIDS', 4, '');

INSERT INTO sections VALUES (31, 'Early childhood education', 5, '');
INSERT INTO sections VALUES (32, 'Speech therapy. The development of speech', 5, '');
INSERT INTO sections VALUES (33, 'Russian language', 5, '');
INSERT INTO sections VALUES (34, 'Literature', 5, '');
INSERT INTO sections VALUES (35, 'Alphabet. Primers. Recipe', 5, '');
INSERT INTO sections VALUES (36, 'History', 5, '');
INSERT INTO sections VALUES (37, 'Social studies', 5, '');
INSERT INTO sections VALUES (38, 'Life safety', 5, '');
INSERT INTO sections VALUES (39, 'Economy', 5, '');
INSERT INTO sections VALUES (40, 'Math', 5, '');
INSERT INTO sections VALUES (41, 'Informatics', 5, '');
INSERT INTO sections VALUES (42, 'Physics', 5, '');
INSERT INTO sections VALUES (43, 'Chemistry', 5, '');
INSERT INTO sections VALUES (44, 'Biology', 5, '');
INSERT INTO sections VALUES (45, 'Geography', 5, '');
INSERT INTO sections VALUES (46, 'Drawing', 5, '');
INSERT INTO sections VALUES (47, 'Fine art', 5, '');
INSERT INTO sections VALUES (48, 'Logic', 5, '');
INSERT INTO sections VALUES (49, 'Physical education', 5, '');

INSERT INTO sections VALUES (50, 'English', 7, '');
INSERT INTO sections VALUES (51, 'German language', 7, '');
INSERT INTO sections VALUES (52, 'French language', 7, '');
INSERT INTO sections VALUES (53, 'Italian language', 7, '');
INSERT INTO sections VALUES (54, 'Other languages', 7, '');

INSERT INTO activities VALUES (activities_seq.NEXTVAL, 'Poety tournament I round', 'First round poety tournament, where everybody read poet', 0, TO_DATE('07/08/2002', 'DD/MM/YYYY'));
INSERT INTO activities VALUES (activities_seq.NEXTVAL, 'Folk convention', 'All countries in one room', 0, TO_DATE('10/08/2002', 'DD/MM/YYYY'));
INSERT INTO activities VALUES (activities_seq.NEXTVAL, 'Poety tournament II round', 'Second round poety tournament, where everybody read poet', 0, TO_DATE('14/08/2002', 'DD/MM/YYYY'));
INSERT INTO activities VALUES (activities_seq.NEXTVAL, 'Poety tournament III round', 'Third round poety tournament, where everybody read poet', 0, TO_DATE('23/08/2002', 'DD/MM/YYYY'));
INSERT INTO activities VALUES (activities_seq.NEXTVAL, 'Game-room', 'Play all games in room', 0, TO_DATE('29/08/2002', 'DD/MM/YYYY'));
INSERT INTO activities VALUES (activities_seq.NEXTVAL, 'Presentation training', 'You learn make perfect presentation', 0, TO_DATE('12/09/2002', 'DD/MM/YYYY'));
INSERT INTO participants VALUES (0, 1);
INSERT INTO participants VALUES (1, 1);
INSERT INTO participants VALUES (2, 1);
INSERT INTO participants VALUES (3, 1);
INSERT INTO participants VALUES (4, 1);
INSERT INTO participants VALUES (5, 1);
INSERT INTO child_activities VALUES (0, 2);
INSERT INTO child_activities VALUES (2, 3);