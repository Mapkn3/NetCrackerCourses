<%-- 
    Document   : author
    Created on : 04.04.2016, 15:24:38
    Author     : Nikolay
--%>

<%@page import="controller.DatabaseController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    boolean flag = false;
    DatabaseController dc = new DatabaseController();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../styles/_styles.css">
        <title>Library help system</title>
    </head>
    <body>
        <%
            String action = request.getParameter("action");
            String authorID = request.getParameter("author");
            String str = request.getParameter("save");
            String firstname = "";
            String lastname = "";
            String surname = "";
            String birthdate = "";
            if (action != null)
            {
                if (action.equals("edit"))
                {
                    if (authorID != null)
                    {
                        String[] author = dc.getAuthor(Integer.parseInt(authorID));
                        firstname = author[1];
                        lastname = author[2];
                        surname = author[3];
                        birthdate = author[4];
                    }
                }
            }
            if (str != null)
            {
                if (str.equals("save"))
                {
                    if (action.equals("new"))
                    {
                        firstname = request.getParameter("firstname");
                        lastname = request.getParameter("lastname");
                        surname = request.getParameter("surname");
                        birthdate = request.getParameter("birthdate");
                        flag = dc.addAuthor(firstname, lastname, surname, birthdate);
                    } else if (action.equals("edit"))
                    {
                        firstname = request.getParameter("firstname");
                        lastname = request.getParameter("lastname");
                        surname = request.getParameter("surname");
                        birthdate = request.getParameter("birthdate");
                        flag = dc.updateAuthor(Integer.parseInt(authorID), firstname, lastname, surname, birthdate);
                    }
                }
            }
        %>
        <div id="wrapper">
            <header id="header">
                <img width="250" height="100" src="../images/logotip.png" alt="logo"/>
                <div id="info">
                    <table>
                        <tr>
                            <td>
                                <img width="100" height="100" src="../images/profile.png"/>
                            </td>
                            <td>
                                Добро пожаловать, <%=session.getAttribute("login")%>!<br/>
                                <a href="profile.jsp">Профиль</a>
                                <a href="../index.jsp">Выйти</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </header>
            <main id="main1">
                <%
                    if (str == null)
                    {
                %>
                <form action="../pages/author.jsp?save=save&action=<%=action%>&author=<%=authorID%>" method="POST">
                    <table class="input_panel">
                        <tbody>
                            <tr>
                                <td class="rightalign">
                                    Имя:
                                </td>
                                <td>
                                    <input type="text" required name="firstname" value="<%= firstname%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Фамилия:
                                </td>
                                <td>
                                    <input type="text" required name="lastname" value="<%= lastname%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Отчество:
                                </td>
                                <td>
                                    <input type="text" required name="surname" value="<%= surname%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Дата рождения:
                                </td>
                                <td>
                                    <input type="date" required name="birthdate" value="<%= birthdate%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td align="right">
                                    <input type="submit" value="<%
                                        if (action.equals("new"))
                                        {
                                            out.print("Создать");
                                        } else if (action.equals("edit"))
                                        {
                                            out.print("Изменить");
                                        }
                                           %>"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <% } else
                {
                    if (flag)
                    {
                %>
                <table class="input_panel">
                    <tr>
                        <td>
                            <img width="100" height="100" src="../images/confirm.png" alt="confirm"/>
                        </td>
                        <td>
                            <h1>Сохранено</h1>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="right">
                            <form action="../pages/authors.jsp" method="POST">
                                <input type="submit" value="Вернуться">
                            </form>
                        </td>
                    </tr>
                </table>
                <%
                } else
                {
                %>

                <table class="input_panel">
                    <tr>
                        <td>
                            <img width="100" height="100" src="../images/cancel.png" alt="cancel"/>
                        </td>
                        <td>
                            <h1>Ошибка</h1>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="right">
                            <form action="../pages/authors.jsp" method="POST">
                                <input type="submit" value="Вернуться">
                            </form>
                        </td>
                    </tr>
                </table>
                <%
                        }
                    }
                %>
            </main>
        </div>
    </body>
</html>
