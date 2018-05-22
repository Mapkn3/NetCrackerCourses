<%-- 
    Document   : book
    Created on : 05.04.2016, 11:07:41
    Author     : Nikolay
--%>

<%@page import="java.util.List"%>
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
            String bookID = request.getParameter("book");
            String str = request.getParameter("save");
            String title = "";
            String[] authors = new String[0];
            String pubdate = "";
            String pages = "";
            String sectionID = "";
            if (action != null)
            {
                if (action.equals("edit"))
                {
                    if (bookID != null)
                    {
                        String[] book = dc.getBook(Integer.parseInt(bookID));
                        title = book[1];
                        authors = book[2].split(" ");
                        pubdate = book[3];
                        pages = book[4];
                        sectionID = book[5];
                    }
                }
            }
            if (str != null)
            {
                if (str.equals("save"))
                {
                    if (action.equals("new"))
                    {
                        title = request.getParameter("title");
                        authors = request.getParameterValues("authors[]");
                        pubdate = request.getParameter("pubdate");
                        pages = request.getParameter("pages");
                        sectionID = request.getParameter("sectionID");
                        flag = dc.addBook(title, authors, pubdate, Integer.parseInt(pages), Integer.parseInt(sectionID));
                    } else if (action.equals("edit"))
                    {
                        title = request.getParameter("title");
                        authors = request.getParameterValues("authors[]");
                        pubdate = request.getParameter("pubdate");
                        pages = request.getParameter("pages");
                        sectionID = request.getParameter("sectionID");
                        flag = dc.updateBook(Integer.parseInt(bookID), title, authors, pubdate, Integer.parseInt(pages), Integer.parseInt(sectionID));
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
                <form action="../pages/book.jsp?save=save&action=<%=action%>&book=<%=bookID%>" method="POST">
                    <table class="input_panel">
                        <tbody>
                            <tr>
                                <td class="rightalign">
                                    Название:
                                </td>
                                <td>
                                    <input type="text" required name="title" value="<%= title%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Авторы:
                                </td>
                                <td>
                                    <form>
                                        <select size="10" required multiple name="authors[]">
                                            <%
                                                List<String[]> authorsList = dc.getAuthors();
                                                for (String[] author : authorsList)
                                                {
                                            %> 
                                            <option <%
                                                for (String s : authors)
                                                {
                                                    if (s.equals(author[0]))
                                                    {
                                                        out.print("selected");
                                                    }
                                                }
                                                %> value="<%= author[0]%>">
                                                <%=author[2] + " " + author[1] + " " + author[3] + " " + author[4]%></option>
                                                <%
                                                    }
                                                %>
                                        </select>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Дата публикации:
                                </td>
                                <td>
                                    <input type="date" required name="pubdate" value="<%= pubdate%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Количество страниц:
                                </td>
                                <td>
                                    <input type="number" required name="pages" min="1" value="<%= pages%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Категория:
                                </td>
                                <td>
                                    <select required name="sectionID">
                                        <option value="">Выберите категорию</option>
                                        <%
                                            List<String[]> sections = dc.getSections();
                                            for (String[] section : sections)
                                            {
                                        %> 
                                        <option <%
                                                if (section[0].equals(sectionID))
                                                {
                                                    out.print("selected");
                                                }
                                                %> value="<%=section[0]%>"><%=section[1]%></option>
                                        <%
                                            }
                                        %>
                                    </select>
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
                            <form action="../pages/books.jsp" method="POST">
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
                            <form action="../pages/books.jsp" method="POST">
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
