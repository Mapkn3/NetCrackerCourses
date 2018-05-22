<%-- 
    Document   : administration
    Created on : 29.03.2016, 15:55:49
    Author     : Nikolay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../styles/_styles.css">
        <link rel="stylesheet" type="text/css" href="../styles/profile_styles.css">
        <title>Library help system</title>
    </head>
    <body>
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
                <table align="center" class="profile_table">
                    <tbody>
                        <tr>
                            <td align="center">
                                <a href="../pages/catalog.jsp">
                                    <img width="80" height="80" src="../images/catalog.png" alt="Catalog"/><br/>
                                    Каталог
                                </a>
                            </td>
                            <td align="center">
                                <a href="../pages/mybooks.jsp">
                                    <img width="80" height="80" src="../images/user_books.png" alt="MyBooks"/><br/>
                                    Мои книги
                                </a>
                            </td>
                            <td align="center">
                                <a href="../pages/settings.jsp">
                                    <img width="80" height="80" src="../images/settings.png" alt="Settings"/><br/>
                                    Редактировать профиль
                                </a>
                            </td>
                            <td align="center">
                                <a href="../exclusive/libAction.jsp">
                                    Мероприятия
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <%
                    boolean flag = Boolean.parseBoolean(session.getAttribute("type").toString());
                    if (flag)
                    {
                %>
                <table align="center" class="profile_table">
                    <tbody>
                        <tr>
                            <td align="center">
                                <a href="../pages/users.jsp">
                                    <img width="80" height="80" src="../images/users.png" alt="Users"/><br/>
                                    Пользователи
                                </a>
                            </td>
                            <td align="center">
                                <a href="../pages/authors.jsp">
                                    <img width="80" height="80" src="../images/authors.png" alt="Authors"/><br/>
                                    Авторы
                                </a>
                            </td>
                            <td align="center">
                                <a href="../pages/books.jsp">
                                    <img width="80" height="80" src="../images/books.png" alt="Books"/><br/>
                                    Книги
                                </a>
                            </td>
                            <td align="center">
                                <a href="../pages/exemplars.jsp">
                                    <img width="80" height="80" src="../images/exemplars.png" alt="Exemplars"/><br/>
                                    Экземпляры
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <% }%>
            </main>
        </div>
    </body>
</html>
