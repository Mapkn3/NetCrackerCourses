/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.sql.LibraryUsersSQLController;
import controller.sql.UserTypesSQLController;
import java.util.List;

/**
 *
 * @author Nikolay
 */
public class UserController
{
    int userID;
    String login;
    String password;
    String firstname;
    String lastname;
    String surname;
    boolean admin;
    
    LibraryUsersSQLController users;
    UserTypesSQLController userTypes;
    
    public UserController()
    {
        users = new LibraryUsersSQLController();
        userTypes = new UserTypesSQLController();
    }
    
    public boolean login(String login, String password)
    {
        String[] user = users.selectByLogin(login);
        if (user == null) return false;
        if (!user[2].equals(password)) return false;
        String[] userType = userTypes.selectByID(Integer.parseInt(user[6]));
        if (userType == null) return false;
        if (userType[1].equals("Admin")) admin = true;
        else admin = false;
        userID = Integer.parseInt(user[0]);
        this.login = user[1];
        this.password = user[2];
        firstname = user[3];
        lastname = user[4];
        surname = user[5];
        return true;
    }
    
    public boolean isAdmin()
    {
        return admin;
    }

    public int getUserID()
    {
        return userID;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getSurname()
    {
        return surname;
    }
    
    public boolean loginIsAvailable(String login)
    {
        return users.selectByLogin(login) == null;
    }
    
    public boolean register(String login, String password, String firstname, String lastname, String surname)
    {
         return users.insert(login, password, firstname, lastname, surname, 0);
    }
    
    public boolean register(String login, String password, String firstname, String lastname, String surname, int type)
    {
        return users.insert(login, password, firstname, lastname, surname, type);
    }
    
    public boolean update(int userID, String login, String password, String firstname, String lastname, String surname, boolean type)
    {
        int t = 0;
        if (type) t = 1;
        return users.update(userID, login, password, firstname, lastname, surname, t);
    }
    
    public List<String[]> getUsers()
    {
        List<String[]> list = users.select();
        
        return list;
    }
    
    public String[] getUser(int id)
    {
        String[] list = users.selectByID(id);
        return list;
    }
    
    public void deleteUser(int id)
    {
        users.deleteByID(id);
    }
}
