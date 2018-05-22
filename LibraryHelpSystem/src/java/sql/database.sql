call AUTHOR_INSERT('Galicin', 'Petr', 'Ivanovich', SYSDATE);
call BOOK_INSERT('German language grade 8', SYSDATE, 100, 51);
call AUTHORS_BOOKS_INSERT(1, 0);
call EXEMPLAR_INSERT(6589, 1, NULL);