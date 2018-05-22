<%-- 
    Document   : exemplar
    Created on : 05.04.2016, 17:55:00
    Author     : Nikolay
--%>

<%@page import="controller.UserController"%>
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
            String invNum = request.getParameter("exemplar");
            String str = request.getParameter("save");
            String book = "";
            String user = "";
            if (action != null)
            {
                if (action.equals("edit"))
                {
                    if (invNum != null)
                    {
                        String[] exemplar = dc.getExemplar(Integer.parseInt(invNum));
                        book = exemplar[1];
                        user = exemplar[2];
                    }
                }
            }
            if (str != null)
            {
                if (str.equals("save"))
                {
                    if (action.equals("new"))
                    {
                        book = request.getParameter("book");
                        user = request.getParameter("user");
                        if (user.equals(""))
                        {
                            flag = dc.addExemplar(Integer.parseInt(invNum), Integer.parseInt(book));
                        } else
                        {
                            flag = dc.addExemplar(Integer.parseInt(invNum), Integer.parseInt(book), Integer.parseInt(user));
                        }
                    } else if (action.equals("edit"))
                    {
                        book = request.getParameter("book");
                        user = request.getParameter("user");
                        if (!user.equals(""))
                        {
                            flag = dc.updateExemplar(Integer.parseInt(invNum), Integer.parseInt(book), Integer.parseInt(user));
                        } else
                        {
                            flag = dc.updateExemplar(Integer.parseInt(invNum), Integer.parseInt(book));
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
                <form action="../pages/exemplar.jsp?save=save&action=<%=action%>" method="POST">
                    <table class="input_panel">
                        <tbody>
                            <tr>
                                <td class="rightalign">
                                    Инвентарный номер:
                                </td>
                                <td>
                                    <input type="text" required name="exemplar" value="<% if (invNum == null)
                                        {
                                            out.print("");
                                        } else
                                        {
                                            out.print(invNum);
                                        } %>" <% if (action.equals("edit"))
                                        {
                                            out.print("readonly");
                                        } %>/>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Книга:
                                </td>
                                <td>
                                    <select required name="book">
                                        <%
                                            List<String[]> books = dc.getBooks();
                                            for (String[] arr : books)
                                            {
                                        %> 
                                        <option <% if (arr[0].equals(book))
                                            {
                                                out.print("selected");
                                            }%> value="<%=arr[0]%>">
                                            <%= arr[0] + " " + arr[1]%>
                                        </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Пользователь:
                                </td>
                                <td>
                                    <select name="user">
                                        <option value="">Отсутствует</option>
                                        <%
                                            UserController uc = new UserController();
                                            List<String[]> users = uc.getUsers();
                                            for (String[] arr : users)
                                            {
                                        %> 
                                        <option <% if (arr[0].equals(user))
                                            {
                                                out.print("selected");
                                            }%> value="<%=arr[0]%>">
                                            <%= arr[0] + " " + arr[1]%>
                                        </option>
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
                            <form action="../pages/exemplars.jsp" method="POST">
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
                            <form action="../pages/exemplars.jsp" method="POST">
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
