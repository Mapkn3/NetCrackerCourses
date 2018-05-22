<%-- 
    Document   : auth
    Created on : 29.03.2016, 11:27:14
    Author     : Nikolay
--%>

<%@page import="controller.UserController"%>
<%@page import="controller.DatabaseController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../styles/_styles.css">
        <title>Library help system</title>
    </head>
    <body>
        <%
            switch (Integer.parseInt(request.getParameter("type")))
            {
                case 0:
                {
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    UserController uc = new UserController();
                    boolean flag = uc.login(login, password);
                    if (flag)
                    {
                        session.setAttribute("user_id", uc.getUserID());
                        session.setAttribute("login", uc.getLogin());
                        session.setAttribute("password", uc.getPassword());
                        session.setAttribute("firstname", uc.getFirstname());
                        session.setAttribute("lastname", uc.getLastname());
                        session.setAttribute("surname", uc.getSurname());
                        session.setAttribute("type", uc.isAdmin());%>
        <jsp:forward page="profile.jsp"></jsp:forward>
        <%} else
                {
                    out.print("Error!");
                }
            }
            break;
            case 1:
            {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String surname = request.getParameter("surname");
                UserController uc = new UserController();
                if (uc.loginIsAvailable(login))
                {
                    uc.register(login, password, firstname, lastname, surname);
                    boolean flag = uc.login(login, password);
                    if (flag)
                    {
                        session.setAttribute("user_id", uc.getUserID());
                        session.setAttribute("login", uc.getLogin());
                        session.setAttribute("password", uc.getPassword());
                        session.setAttribute("firstname", uc.getFirstname());
                        session.setAttribute("lastname", uc.getLastname());
                        session.setAttribute("surname", uc.getSurname());
                        session.setAttribute("type", uc.isAdmin());%>
        <jsp:forward page="profile.jsp"></jsp:forward>
        <%} else
        {
        %> 
        <div id="wrapper">
            <header id="header">
                <img width="250" height="100" src="../images/logotip.png" alt="logo"/>
            </header>
            <main id="main1">
                <table class="input_panel">
                    <tr>
                        <td>
                            <img width="100" height="100" src="../images/cancel.png" alt="cancel"/>
                        </td>
                        <td>
                            <h1>Ошибка.</h1>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="right">
                            <form action="../index.jsp" method="POST">
                                <input type="submit" value="Вернуться">
                            </form>
                        </td>
                    </tr>
                </table>
            </main>
        </div>
        <%
            }
        } else
        {
        %> 
        <div id="wrapper">
            <header id="header">
                <img width="250" height="100" src="../images/logotip.png" alt="logo"/>
            </header>
            <main id="main1">
                <table class="input_panel">
                    <tr>
                        <td>
                            <img width="100" height="100" src="../images/cancel.png" alt="cancel"/>
                        </td>
                        <td>
                            <h1>Учетная запись с таким именем уже занята.</h1>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="right">
                            <form action="../index.jsp" method="POST">
                                <input type="submit" value="Вернуться">
                            </form>
                        </td>
                    </tr>
                </table>
            </main>
        </div>
        <%
                    }
                }
                break;
            }
        %>
    </body>
</html>
