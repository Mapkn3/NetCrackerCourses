CREATE OR REPLACE PROCEDURE Library_User_Insert(
    login IN VARCHAR,
    password IN VARCHAR,
    first_name IN VARCHAR,
    last_name IN VARCHAR,
    surname IN VARCHAR,
    type_id IN NUMBER
)
AS
BEGIN
    INSERT INTO library_users 
    VALUES (
        library_users_sequence.NEXTVAL,
        login,
        password,
        first_name,
        last_name,
        surname,
        type_id
    );
END Library_User_Insert;
/
CREATE OR REPLACE PROCEDURE Library_User_Update(
    user_id IN NUMBER,
    login IN VARCHAR,
    password IN VARCHAR,
    first_name IN VARCHAR,
    last_name IN VARCHAR,
    surname IN VARCHAR,
    type_id IN NUMBER
)
AS
BEGIN
    UPDATE library_users
    SET library_users.login = Library_User_Update.login,
        library_users.password = Library_User_Update.password,
        library_users.first_name = Library_User_Update.first_name,
        library_users.last_name = Library_User_Update.last_name,
        library_users.surname = Library_User_Update.surname,
        library_users.type_id = Library_User_Update.type_id
    WHERE library_users.user_id = Library_User_Update.user_id;
END Library_User_Update;
/
CREATE OR REPLACE PROCEDURE Library_User_Login_Update(
    user_id IN NUMBER,
    login IN VARCHAR
)
AS
BEGIN
    UPDATE library_users
    SET library_users.login = Library_User_Login_Update.login
    WHERE library_users.user_id = Library_User_Login_Update.user_id;
END Library_User_Login_Update;
/
CREATE OR REPLACE PROCEDURE Library_User_Password_Update(
    user_id IN NUMBER,
    password IN VARCHAR
)
AS
BEGIN
    UPDATE library_users
    SET library_users.password = Library_User_Password_Update.password
    WHERE library_users.user_id = Library_User_Password_Update.user_id;
END Library_User_Password_Update;
/
CREATE OR REPLACE PROCEDURE Library_User_First_Name_Update(
    user_id IN NUMBER,
    first_name IN VARCHAR
)
AS
BEGIN
    UPDATE library_users
    SET library_users.first_name = Library_User_First_Name_Update.first_name
    WHERE library_users.user_id = Library_User_First_Name_Update.user_id;
END Library_User_First_Name_Update;
/
CREATE OR REPLACE PROCEDURE Library_User_Last_Name_Update(
    user_id IN NUMBER,
    last_name IN VARCHAR
)
AS
BEGIN
    UPDATE library_users
    SET library_users.last_name = Library_User_Last_Name_Update.last_name
    WHERE library_users.user_id = Library_User_Last_Name_Update.user_id;
END Library_User_Last_Name_Update;
/
CREATE OR REPLACE PROCEDURE Library_User_Surname_Update(
    user_id IN NUMBER,
    surname IN VARCHAR
)
AS
BEGIN
    UPDATE library_users
    SET library_users.surname = Library_User_Surname_Update.surname
    WHERE library_users.user_id = Library_User_Surname_Update.user_id;
END Library_User_Surname_Update;
/
CREATE OR REPLACE PROCEDURE Library_User_Type_Update(
    user_id IN NUMBER,
    type_id IN NUMBER
)
AS
BEGIN
    UPDATE library_users
    SET library_users.type_id = Library_User_Type_Update.type_id
    WHERE library_users.user_id = Library_User_Type_Update.user_id;
END Library_User_Type_Update;
/
CREATE OR REPLACE PROCEDURE Library_User_Delete(
    user_id IN NUMBER
)
AS
BEGIN
    UPDATE exemplars
    SET exemplars.user_id = NULL
    WHERE exemplars.user_id = Library_user_Delete.user_id;
    DELETE FROM library_users 
    WHERE library_users.user_id = Library_user_Delete.user_id;
END Library_user_Delete;
/
CREATE OR REPLACE PROCEDURE User_Type_Insert(
    name IN VARCHAR
)
AS
BEGIN
    INSERT INTO user_types 
    VALUES (
        user_types_sequence.NEXTVAL, 
        name
    );
END User_Type_Insert;
/
CREATE OR REPLACE PROCEDURE User_Type_Update(
    type_id IN NUMBER,
    name IN VARCHAR
)
AS
BEGIN
    UPDATE user_types
    SET user_types.name = User_Type_Update.name
    WHERE user_types.type_id = User_Type_Update.type_id;
END User_Type_Update;
/
CREATE OR REPLACE PROCEDURE User_Type_Delete(
    type_id IN NUMBER
)
AS
BEGIN
    DELETE FROM user_types 
    WHERE user_types.type_id = User_Type_Delete.type_id;
END User_Type_Delete;
/
CREATE OR REPLACE FUNCTION Book_Insert(
    title IN VARCHAR,
    pub_date IN DATE,
    pages IN NUMBER,
    section_id IN NUMBER
)
RETURN NUMBER
AS
  book_id NUMBER;
BEGIN
  book_id := books_sequence.NEXTVAL;
  INSERT INTO books 
  VALUES (
      book_id, title, pub_date, pages, section_id
  );
  RETURN book_id;
END Book_Insert;
/
CREATE OR REPLACE FUNCTION GET_AUTHORS(
    book_id IN NUMBER
)
RETURN VARCHAR
IS
    str VARCHAR(200) := '';
    CURSOR authorsCur IS
        SELECT AUTHOR_ID 
        FROM AUTHORS_BOOKS 
        WHERE AUTHORS_BOOKS.book_id = GET_AUTHORS.book_id;
BEGIN
    FOR auth IN authorsCur LOOP
        SELECT (str || LAST_NAME || ' ' || SUBSTR(first_name, 1, 1) || '. ' || SUBSTR(surname, 1, 1) || '.<br/>')
        INTO str
        FROM authors
        WHERE AUTHOR_ID = auth.AUTHOR_ID;
    END LOOP;
    return str;
END GET_AUTHORS;
/
CREATE OR REPLACE PROCEDURE Book_Update(
    book_id IN NUMBER,
    title IN VARCHAR,
    pub_date IN DATE,
    pages IN NUMBER,
    section_id IN NUMBER
)
AS
BEGIN
    UPDATE books
    SET books.title = Book_Update.title,
        books.pub_date = Book_Update.pub_date,
        books.pages = Book_Update.pages,
        books.section_id = Book_Update.section_id
    WHERE books.book_id = Book_Update.book_id;
END Book_Update;
/
CREATE OR REPLACE PROCEDURE Book_Title_Update(
    book_id IN NUMBER,
    title IN VARCHAR
)
AS
BEGIN
    UPDATE books
    SET books.title = Book_Title_Update.title
    WHERE books.book_id = Book_Title_Update.book_id;
END Book_Title_Update;
/
CREATE OR REPLACE PROCEDURE Book_Pub_Date_Update(
    book_id IN NUMBER,
    pub_date IN DATE
)
AS
BEGIN
    UPDATE books
    SET books.pub_date = Book_Pub_Date_Update.pub_date
    WHERE books.book_id = Book_Pub_Date_Update.book_id;
END Book_Pub_Date_Update;
/
CREATE OR REPLACE PROCEDURE Book_Pages_Update(
    book_id IN NUMBER,
    pages IN NUMBER
)
AS
BEGIN
    UPDATE books
    SET books.pages = Book_Pages_Update.pages
    WHERE books.book_id = Book_Pages_Update.book_id;
END Book_Pages_Update;
/
CREATE OR REPLACE PROCEDURE Book_Section_Id_Update(
    book_id IN NUMBER,
    section_id IN NUMBER
)
AS
BEGIN
    UPDATE books
    SET books.section_id = Book_Section_Id_Update.section_id
    WHERE books.book_id = Book_Section_Id_Update.book_id;
END Book_Section_Id_Update;
/
CREATE OR REPLACE PROCEDURE Book_Delete(
    book_id IN NUMBER
)
AS
BEGIN
    DELETE FROM authors_books
    WHERE authors_books.book_id = Book_Delete.book_id;
    DELETE FROM books 
    WHERE books.book_id = Book_Delete.book_id;
END Book_Delete;
/
CREATE OR REPLACE PROCEDURE Author_Insert(
    first_name IN VARCHAR,
    last_name IN VARCHAR,
    surname IN VARCHAR,
    birth_date IN DATE
)
AS
BEGIN
    INSERT INTO authors
    VALUES (
        authors_sequence.NEXTVAL,
        first_name,
        last_name,
        surname,
        birth_date
    );
END Author_Insert;
/
CREATE OR REPLACE PROCEDURE Author_Update(
    author_id IN NUMBER,
    first_name IN VARCHAR,
    last_name IN VARCHAR,
    surname IN VARCHAR,
    birth_date IN DATE
)
AS
BEGIN
    UPDATE authors
    SET authors.first_name = Author_Update.first_name,
        authors.last_name = Author_Update.last_name,
        authors.surname = Author_Update.surname,
        authors.birth_date = Author_Update.birth_date
    WHERE authors.author_id = Author_Update.author_id;
END Author_Update;
/
CREATE OR REPLACE PROCEDURE Author_First_Name_Update(
    author_id IN NUMBER,
    first_name IN VARCHAR
)
AS
BEGIN
    UPDATE authors
    SET authors.first_name = Author_First_Name_Update.first_name
    WHERE authors.author_id = Author_First_Name_Update.author_id;
END Author_First_Name_Update;
/
CREATE OR REPLACE PROCEDURE Author_Last_Name_Update(
    author_id IN NUMBER,
    last_name IN VARCHAR
)
AS
BEGIN
    UPDATE authors
    SET authors.last_name = Author_Last_Name_Update.last_name
    WHERE authors.author_id = Author_Last_Name_Update.author_id;
END Author_Last_Name_Update;
/
CREATE OR REPLACE PROCEDURE Author_Surname_Update(
    author_id IN NUMBER,
    surname IN VARCHAR
)
AS
BEGIN
    UPDATE authors
    SET authors.surname = Author_Surname_Update.surname
    WHERE authors.author_id = Author_Surname_Update.author_id;
END Author_Surname_Update;
/
CREATE OR REPLACE PROCEDURE Author_Birth_Date_Update(
    author_id IN NUMBER,
    birth_date IN DATE
)
AS
BEGIN
    UPDATE authors
    SET authors.birth_date = Author_Birth_Date_Update.birth_date
    WHERE authors.author_id = Author_Birth_Date_Update.author_id;
END Author_Birth_Date_Update;
/
CREATE OR REPLACE PROCEDURE Author_Delete(
    author_id IN NUMBER
)
AS
BEGIN
    DELETE FROM authors_books
    WHERE authors_books.author_id = Author_Delete.author_id;
    DELETE FROM authors
    WHERE authors.author_id = Author_Delete.author_id;
END Author_Delete;
/
CREATE OR REPLACE PROCEDURE Exemplar_Insert(
    inventory_number IN NUMBER,
    book_id IN NUMBER,
    user_id IN NUMBER
)
AS
BEGIN
    INSERT INTO exemplars
    VALUES (
        inventory_number,
        book_id,
        user_id
    );
END Exemplar_Insert;
/
CREATE OR REPLACE PROCEDURE Exemplar_Update(
    inventory_number IN NUMBER,
    book_id IN NUMBER,
    user_id IN NUMBER
)
AS
BEGIN
    UPDATE exemplars
    SET exemplars.book_id = Exemplar_Update.book_id,
        exemplars.user_id = Exemplar_Update.user_id
    WHERE exemplars.inventory_number = Exemplar_Update.inventory_number;
END Exemplar_Update;
/
CREATE OR REPLACE PROCEDURE Exemplar_Book_Update(
    inventory_number IN NUMBER,
    book_id IN NUMBER
)
AS
BEGIN
    UPDATE exemplars
    SET exemplars.book_id = Exemplar_Book_Update.book_id
    WHERE exemplars.inventory_number = Exemplar_Book_Update.inventory_number;
END Exemplar_Book_Update;
/
CREATE OR REPLACE PROCEDURE Exemplar_User_Id_Update(
    inventory_number IN NUMBER,
    user_id IN NUMBER
)
AS
BEGIN
    UPDATE exemplars
    SET exemplars.user_id = Exemplar_User_Id_Update.user_id
    WHERE exemplars.inventory_number = Exemplar_User_Id_Update.inventory_number;
END Exemplar_User_Id_Update;
/
CREATE OR REPLACE PROCEDURE Exemplar_User_Id_Delete(
    inventory_number IN NUMBER
)
AS
BEGIN
    UPDATE exemplars
    SET exemplars.user_id = NULL
    WHERE exemplars.inventory_number = Exemplar_User_Id_Delete.inventory_number;
END Exemplar_User_Id_Delete;
/
CREATE OR REPLACE PROCEDURE Exemplar_Delete(
    inventory_number IN NUMBER
)
AS
BEGIN
    DELETE FROM exemplars
    WHERE exemplars.inventory_number = Exemplar_Delete.inventory_number;
END Exemplar_Delete;
/
CREATE OR REPLACE PROCEDURE Authors_Books_Insert(
    author_id IN NUMBER,
    book_id IN NUMBER
)
AS
BEGIN
    INSERT INTO authors_books VALUES (author_id, book_id);
END Authors_Books_Insert;
/
CREATE OR REPLACE PROCEDURE Section_Insert(
    name IN VARCHAR,
    parent_id IN NUMBER,
    description IN VARCHAR
)
AS
BEGIN
    INSERT INTO sections 
    VALUES (
        sections_sequence.NEXTVAL, 
        name,
        parent_id,
        description
    );
END Section_Insert;
/
CREATE OR REPLACE PROCEDURE Section_Update(
    section_id IN NUMBER,
    name IN VARCHAR,
    parent_id IN NUMBER,
    description IN VARCHAR
)
AS
BEGIN
    UPDATE sections
    SET sections.name = Section_Update.name,
        sections.parent_id = Section_Update.parent_id,
        sections.description = Section_Update.description
    WHERE sections.section_id = Section_Update.section_id;
END Section_Update;
/
CREATE OR REPLACE PROCEDURE Section_Name_Update(
    section_id IN NUMBER,
    name IN VARCHAR
)
AS
BEGIN
    UPDATE sections
    SET sections.name = Section_Name_Update.name
    WHERE sections.section_id = Section_Name_Update.section_id;
END Section_Name_Update;
/
CREATE OR REPLACE PROCEDURE Section_Parent_Id_Update(
    section_id IN NUMBER,
    parent_id IN NUMBER
)
AS
BEGIN
    UPDATE sections
    SET sections.parent_id = Section_Parent_Id_Update.parent_id
    WHERE sections.section_id = Section_Parent_Id_Update.section_id;
END Section_Parent_Id_Update;
/
CREATE OR REPLACE PROCEDURE Section_Description_Update(
    section_id IN NUMBER,
    description IN VARCHAR
)
AS
BEGIN
    UPDATE sections
    SET sections.description = Section_Description_Update.description
    WHERE sections.section_id = Section_Description_Update.section_id;
END Section_Description_Update;
/
CREATE OR REPLACE PROCEDURE Section_Delete(
    section_id IN NUMBER
)
AS
BEGIN
    DELETE FROM sections 
    WHERE sections.section_id = Section_Delete.section_id;
END Section_Delete;