<%-- 
    Document   : books
    Created on : 05.04.2016, 10:50:09
    Author     : Nikolay
--%>

<%@page import="java.util.List"%>
<%@page import="controller.DatabaseController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    DatabaseController dc = new DatabaseController();

    void delete(HttpSession session, String book)
    {
        int id = Integer.parseInt(book);
        dc.deleteBook(id);
    }
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
            String str = request.getParameter("del");
            if (str != null)
            {
                delete(session, str);
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
                <form style="margin: 10px 0 10px 0" action="../pages/book.jsp?action=new" method="POST">
                    <input type="submit" value="Создать новую книгу">
                </form>
                <table id="table" class="sortable">
                    <thead>
                        <tr>
                            <th style="width: 5%"><h3>№</h3></th>
                    <th style="width: 20%"><h3>Название</h3></th>
                    <th style="width: 25%"><h3>Авторы</h3></th>
                    <th style="width: 10%"><h3>Дата издания</h3></th>
                    <th style="width: 5%"><h3>Кол-во страниц</h3></th>
                    <th style="width: 25%"><h3>Категория</h3></th>
                    <th class="nosort" style="width: 5%"><h3>Изменить</h3></th>
                    <th class="nosort" style="width: 5%"><h3>Удалить</h3></th>
                    </tr>
                    </thead>
                    <tbody>
                        <%
                            dc = new DatabaseController();
                            List<String[]> books = dc.getBooks();
                            for (String[] book : books)
                            {
                        %>
                        <tr>
                            <td>
                                <%= book[0]%>
                            </td>
                            <td>
                                <%= book[1]%>
                            </td>
                            <td>
                                <%= book[2]%>
                            </td>
                            <td>
                                <%= book[3]%>
                            </td>
                            <td>
                                <%= book[4]%>
                            </td>
                            <td>
                                <%= book[5]%>
                            </td>
                            <td>
                                <a href="../pages/book.jsp?action=edit&book=<%= book[0]%>">
                                    <img width="20" height="20" src="../images/edit.png" alt="edit"/>
                                </a>
                            </td>
                            <td>
                                <a href="../pages/books.jsp?del=<%= book[0]%>">
                                    <img width="20" height="20" src="../images/cancel.png" alt="delete"/>
                                </a>
                            </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
                <div id="controls">
                    <table>
                        <tr>
                            <td>
                                <div id="perpage">
                                    <select onchange="sorter.size(this.value)">
                                        <option value="5">5</option>
                                        <option value="10" selected="selected">10</option>
                                        <option value="20">20</option>
                                        <option value="50">50</option>
                                        <option value="100">100</option>
                                    </select>
                                    <span>Записей на страницу</span>
                                </div>
                            </td>
                            <td>
                                <div id="navigation">
                                    <img src="../images/first.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1, true)" />
                                    <img src="../images/previous.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1)" />
                                    <img src="../images/next.gif" width="16" height="16" alt="First Page" onclick="sorter.move(1)" />
                                    <img src="../images/last.gif" width="16" height="16" alt="Last Page" onclick="sorter.move(1, true)" />
                                </div>
                            </td>
                            <td>
                                <div id="text">Страница <span id="currentpage"></span> из <span id="pagelimit"></span></div>
                            </td>
                        </tr>
                    </table>
                </div>
            </main>
        </div>
        <script type="text/javascript" src="../scripts/script.js"></script>
        <script type="text/javascript">
                                        var sorter = new TINY.table.sorter("sorter");
                                        sorter.head = "head";
                                        sorter.asc = "asc";
                                        sorter.desc = "desc";
                                        sorter.even = "evenrow";
                                        sorter.odd = "oddrow";
                                        sorter.evensel = "evenselected";
                                        sorter.oddsel = "oddselected";
                                        sorter.paginate = true;
                                        sorter.currentid = "currentpage";
                                        sorter.limitid = "pagelimit";
                                        sorter.init("table", 1);
        </script>
    </body>
</html>
