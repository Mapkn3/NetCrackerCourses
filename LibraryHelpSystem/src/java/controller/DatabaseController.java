/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.sql.AuthorsBooksSQLController;
import controller.sql.AuthorsSQLController;
import controller.sql.BooksSQLController;
import controller.sql.ExemplarsSQLController;
import controller.sql.LibraryUsersSQLController;
import controller.sql.SectionsSQLController;
import controller.sql.UserTypesSQLController;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import model.Tree;

/**
 *
 * @author Nikolay
 */
public class DatabaseController
{

    private final BooksSQLController books;
    private final AuthorsBooksSQLController authorsBooks;
    private final AuthorsSQLController authors;
    private final ExemplarsSQLController exemplars;
    private final SectionsSQLController sections;

    public DatabaseController()
    {
        books = new BooksSQLController();
        authorsBooks = new AuthorsBooksSQLController();
        authors = new AuthorsSQLController();
        exemplars = new ExemplarsSQLController();
        sections = new SectionsSQLController();
    }

    public List<String[]> getAvailableBooksBySection(int section_id)
    {
        List<String[]> bookList = books.selectAvailableBySection(section_id);
        return bookList;
    }

    public List<String[]> getExemplarsByUser(int userID)
    {
        List<String[]> bookList = exemplars.selectByUser(userID);
        return bookList;
    }

    public void returnExemplar(int invNumber)
    {
        exemplars.clearUserID(invNumber);
    }

    public void takeExemplar(int userID, int bookID)
    {
        int invNumber = exemplars.getAvailableExemplar(bookID);
        if (invNumber != -1) exemplars.updateUserID(invNumber, userID);
    }
    
    public Tree getTree()
    {
        return sections.getTree();
    }
    
    public List<String[]> getAuthors()
    {
        return authors.select();
    }
    
    public String[] getAuthor(int authorID)
    {
        return authors.selectByID(authorID);
    }
    
    public boolean addAuthor(String firstname, String lastname, String surname, String birthdate)
    {
        return authors.insert(firstname, lastname, surname, Date.valueOf(birthdate));
    }
    
    public boolean updateAuthor(int authorID, String firstname, String lastname, String surname, String birthdate)
    {
        return authors.update(authorID, firstname, lastname, surname, Date.valueOf(birthdate));
    }
    
    public void deleteAuthor(int authorID)
    {
        authors.deleteByID(authorID);
    }
    
    public List<String[]> getBooks()
    {
        return books.select();
    }
    
    public String[] getBook(int bookID)
    {
        return books.selectByID(bookID);
    }
    
    public boolean addBook(String title, String[] authors, String date, int pages, int sectionID)
    {
        return books.insert(title, authors, Date.valueOf(date), pages, sectionID);
    }
    
    public boolean updateBook(int bookID, String title, String[] authors, String date, int pages, int sectionID)
    {
        if (books.update(bookID, title, Date.valueOf(date), pages, sectionID))
        {
            authorsBooks.deleteByBook(bookID);
            for (String author : authors)
            {
                authorsBooks.insert(Integer.parseInt(author), bookID);
            }
            return true;
        }
        return false;
    }
    
    public void deleteBook(int bookID)
    {
        books.deleteByID(bookID);
    }
    
    public List<String[]> getSections()
    {
        return sections.selectLeafs();
    }
    
    public boolean addExemplar(int invNum, int bookID)
    {
        return exemplars.insert(invNum, bookID);
    }
    
    public boolean addExemplar(int invNum, int bookID, int userID)
    {
        return exemplars.insert(invNum, bookID, userID);
    }
    
    public boolean updateExemplar(int invNum, int bookID, int userID)
    {
        return exemplars.update(invNum, bookID, userID);
    }
    
    public boolean updateExemplar(int invNum, int bookID)
    {
        exemplars.clearUserID(invNum);
        return exemplars.updateBookID(invNum, bookID);
    }
    
    public void deleteExemplar(int invNum)
    {
        exemplars.deleteByInventoryNumber(invNum);
    }
    
    public List<String[]> getExemplars()
    {
        return exemplars.select();
    }
    
    public String[] getExemplar(int invNum)
    {
        return exemplars.selectByInventoryNumber(invNum);
    }
}
