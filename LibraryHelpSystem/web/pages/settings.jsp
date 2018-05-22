<%-- 
    Document   : settings
    Created on : 29.03.2016, 20:25:02
    Author     : Nikolay
--%>

<%@page import="controller.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! boolean flag = false;%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../styles/_styles.css">
        <link rel="stylesheet" type="text/css" href="../styles/settings.css">
        <title>Library hel system</title>
    </head>
    <body>
        <%
            String str = request.getParameter("save");
            if (str != null)
            {
                if (str.equals("save"))
                {
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    String firstname = request.getParameter("firstname");
                    String lastname = request.getParameter("lastname");
                    String surname = request.getParameter("surname");
                    UserController uc = new UserController();
                    int userID = Integer.parseInt(session.getAttribute("user_id").toString());
                    boolean type = Boolean.parseBoolean(session.getAttribute("type").toString());
                    flag = uc.update(userID, login, password, firstname, lastname, surname, type);
                    uc.login(login, password);
                    if (flag)
                    {
                        session.setAttribute("user_id", uc.getUserID());
                        session.setAttribute("login", uc.getLogin());
                        session.setAttribute("password", uc.getPassword());
                        session.setAttribute("firstname", uc.getFirstname());
                        session.setAttribute("lastname", uc.getLastname());
                        session.setAttribute("surname", uc.getSurname());
                        session.setAttribute("type", uc.isAdmin());
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
                <% if (str == null)
                    {
                %> 
                <form action="settings.jsp?save=save" method="POST">
                    <table class="input_panel">
                        <tbody>
                            <tr>
                                <td class="rightalign">
                                    Учетная запись:
                                </td>
                                <td>
                                    <input type="text" required pattern="^[a-zA-Z0-9]{4,20}" name="login" value="<%= session.getAttribute("login")%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Пароль:
                                </td>
                                <td>
                                    <input type="text" required pattern="^[a-zA-Z0-9]{4,20}" name="password" value="<%= session.getAttribute("password")%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Имя:
                                </td>
                                <td>
                                    <input type="text" required name="firstname" value="<%= session.getAttribute("firstname")%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Фамилия:
                                </td>
                                <td>
                                    <input type="text" required name="lastname" value="<%= session.getAttribute("lastname")%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Отчество:
                                </td>
                                <td>
                                    <input type="text" required name="surname" value="<%= session.getAttribute("surname")%>"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td align="right">
                                    <input type="submit" value="Сохранить"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <%
                } else if (str.equals("save"))
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
                            <form action="../pages/settings.jsp" method="POST">
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
                            <form action="../pages/settings.jsp" method="POST">
                                <input type="submit" value="Вернуться">
                            </form>
                        </td>
                    </tr>
                </table>
                <%
                        }
                    }%>
            </main>
        </div>
    </body>
</html>
