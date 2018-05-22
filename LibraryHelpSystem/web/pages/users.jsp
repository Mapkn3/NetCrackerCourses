<%-- 
    Document   : users
    Created on : 03.04.2016, 5:01:11
    Author     : Nikolay
--%>

<%@page import="java.util.List"%>
<%@page import="controller.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    UserController uc = new UserController();

    void delete(HttpSession session, String user)
    {
        int id = Integer.parseInt(user);
        if (!session.getAttribute("user_id").toString().equals(user))
        {
            uc.deleteUser(id);
        }
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
                <form style="margin: 10px 0 10px 0" action="../pages/user.jsp?action=new" method="POST">
                    <input type="submit" value="Создать нового пользователя">
                </form>
                <table id="table" class="sortable">
                    <thead>
                        <tr>
                            <th style="width: 5%"><h3>№</h3></th>
                    <th style="width: 15%"><h3>Учетная запись</h3></th>
                    <th style="width: 15%"><h3>Пароль</h3></th>
                    <th style="width: 15%"><h3>Имя</h3></th>
                    <th style="width: 15%"><h3>Фамилия</h3></th>
                    <th style="width: 15%"><h3>Отчество</h3></th>
                    <th style="width: 10%"><h3>Тип</h3></th>
                    <th class="nosort" style="width: 5%"><h3>Изменить</h3></th>
                    <th class="nosort" style="width: 5%"><h3>Удалить</h3></th>
                    </tr>
                    </thead>
                    <tbody>
                        <%
                            UserController uc = new UserController();
                            List<String[]> users = uc.getUsers();
                            for (String[] user : users)
                            {
                        %>
                        <tr>
                            <td>
                                <%= user[0]%>
                            </td>
                            <td>
                                <%= user[1]%>
                            </td>
                            <td>
                                <%= user[2]%>
                            </td>
                            <td>
                                <%= user[3]%>
                            </td>
                            <td>
                                <%= user[4]%>
                            </td>
                            <td>
                                <%= user[5]%>
                            </td>
                            <td>
                                <%= user[6]%>
                            </td>
                            <td>
                                <a href="../pages/user.jsp?action=edit&user=<%= user[0]%>">
                                    <img width="20" height="20" src="../images/edit.png" alt="edit"/>
                                </a>
                            </td>
                            <td>
                                <a href="../pages/users.jsp?del=<%= user[0]%>">
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
