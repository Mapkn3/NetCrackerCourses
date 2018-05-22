<%-- 
    Document   : user
    Created on : 03.04.2016, 6:21:34
    Author     : Nikolay
--%>

<%@page import="controller.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    boolean flag = false;
    UserController uc = new UserController();
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
            String userID = request.getParameter("user");
            String str = request.getParameter("save");
            String login = "";
            String password = "";
            String firstname = "";
            String lastname = "";
            String surname = "";
            int type = 0;
            if (action != null)
            {
                if (action.equals("edit"))
                {
                    userID = request.getParameter("user");
                    if (userID != null)
                    {
                        String[] user = uc.getUser(Integer.parseInt(userID));
                        login = user[1];
                        password = user[2];
                        firstname = user[3];
                        lastname = user[4];
                        surname = user[5];
                        type = Integer.parseInt(user[6]);
                    }
                }
            }
            if (str != null)
            {
                if (str.equals("save"))
                {
                    if (action.equals("new"))
                    {
                        login = request.getParameter("login");
                        password = request.getParameter("password");
                        firstname = request.getParameter("firstname");
                        lastname = request.getParameter("lastname");
                        surname = request.getParameter("surname");
                        uc = new UserController();
                        if (request.getParameter("type") != null)
                        {
                            type = Integer.parseInt(request.getParameter("type").toString());
                            flag = uc.register(login, password, firstname, lastname, surname, type);
                        }
                    } else if (action.equals("edit"))
                    {
                        login = request.getParameter("login");
                        password = request.getParameter("password");
                        firstname = request.getParameter("firstname");
                        lastname = request.getParameter("lastname");
                        surname = request.getParameter("surname");
                        uc = new UserController();
                        if (request.getParameter("type") != null)
                        {
                            type = Integer.parseInt(request.getParameter("type").toString());
                            boolean typeBool = false;
                            if (type == 1)
                            {
                                typeBool = true;
                            }
                            flag = uc.update(Integer.parseInt(userID), login, password, firstname, lastname, surname, typeBool);
                        }
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
                <form action="../pages/user.jsp?save=save&action=<%=action%>&user=<%=userID%>" method="POST">
                    <table class="input_panel">
                        <tbody>
                            <tr>
                                <td class="rightalign">
                                    Учетная запись:
                                </td>
                                <td>
                                    <input type="text" name="login" value="<%= login%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Пароль:
                                </td>
                                <td>
                                    <input type="text" name="password" value="<%= password%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Имя:
                                </td>
                                <td>
                                    <input type="text" name="firstname" value="<%= firstname%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Фамилия:
                                </td>
                                <td>
                                    <input type="text" name="lastname" value="<%= lastname%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Отчество:
                                </td>
                                <td>
                                    <input type="text" name="surname" value="<%= surname%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Администратор:
                                </td>
                                <td>
                                    <%
                                        String yes = "";
                                        String no = "";
                                        if (type == 1)
                                        {
                                            yes = "checked";
                                        } else if (type == 0)
                                        {
                                            no = "checked";
                                        }
                                    %>
                                    <input type="radio" name="type" value="1" <%=yes%>/>Да
                                    <input type="radio" name="type" value="0" <%=no%>/>Нет
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
                            <form action="../pages/users.jsp" method="POST">
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
                            <form action="../pages/users.jsp" method="POST">
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
