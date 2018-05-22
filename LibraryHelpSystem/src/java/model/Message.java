/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Nikolay
 */
public class Message implements Serializable
{

    public static enum MessageNames
    {
        //CLient names
        ADD_BOOK,
        CHANGE_BOOK,
        REMOVE_BOOKS,
        ADD_EXEMPLAR,
        CHANGE_EXEMPLAR,
        REMOVE_EXEMPLARS,
        CLEAR_DATA,
        SAVE_DATA,
        OPEN_DATA,
        GET_UPDATE,
        GET_SEARCH,
        LOGIN,
        //Server names
        SEND_BOOKS,
        SEND_EXEMPLARS,
        LOGIN_AS_ADMIN,
        LOGIN_AS_USER,
        LOGIN_FALSE
    }

    private final MessageNames name;
    private final Object data;

    public Message(MessageNames name, Object data)
    {
        this.name = name;
        this.data = data;
    }

    public MessageNames getName()
    {
        return name;
    }

    public Object getData()
    {
        return data;
    }

}
