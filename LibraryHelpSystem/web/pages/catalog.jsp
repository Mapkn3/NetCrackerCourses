<%-- 
    Document   : catalog
    Created on : 27.03.2016, 15:12:21
    Author     : Nikolay
--%>

<%@page import="java.util.List"%>
<%@page import="controller.DatabaseController"%>
<%!
    DatabaseController dc = new DatabaseController();

    void takeBook(HttpSession session, String book)
    {
        int userID = Integer.parseInt(session.getAttribute("user_id").toString());
        int bookID = Integer.parseInt(book);
        dc.takeExemplar(userID, bookID);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Library help system</title>
        <link rel="stylesheet" type="text/css" href="../styles/_styles.css">
        <link rel="stylesheet" type="text/css" href="../styles/catalog.css">
    </head>
    <body>
        <%
            String str = request.getParameter("take");
            if (str != null)
            {
                takeBook(session, str);
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
                                <a href="../pages/profile.jsp">Профиль</a>
                                <a href="../index.jsp">Выйти</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </header>
            <table id="main1" style="padding: 0 0; border-spacing: 0 0">
                <tbody>
                    <tr>
                        <td style="padding: 0 0" id="main2">
                            <main id="main2">
                                <table id="table" class="sortable">
                                    <thead>
                                        <tr>
                                            <th style="width: 35%"><h3>Название</h3></th>
                                    <th style="width: 35%"><h3>Автор</h3></th>
                                    <th style="width: 15%"><h3>Дата издания</h3></th>
                                    <th style="width: 5%"><h3>Кол-во страниц</h3></th>
                                    <th style="width: 5%"><h3>Доступно книг</h3></th>
                                    <th style="width: 5%" class="nosort"><h3>Взять</h3></th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                        <%
                                            if (request.getParameter("category") != null)
                                            {
                                                int category = Integer.parseInt(request.getParameter("category"));
                                                List<String[]> books = dc.getAvailableBooksBySection(category);
                                                for (String[] book : books)
                                                {%>
                                        <tr>
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
                                                <a href="catalog.jsp?category=<%= request.getParameter("category")%>&take=<%= book[0]%>">
                                                    <img width="20" height="20" src="../images/confirm.png" alt="take"/>
                                                </a>
                                            </td>
                                        </tr>
                                        <% }
                                            }%>
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
                        </td>
                        <td style="padding: 0 0" id="menu">
                            <nav id="menu" width="300px">
                                <jsp:include page="menu.jsp" flush="true"/>
                            </nav>
                        </td>
                    </tr>
                </tbody>
            </table>
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
