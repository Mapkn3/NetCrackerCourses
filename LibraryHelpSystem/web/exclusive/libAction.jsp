<%@page import="jexclusive.excListEvents"%>
<%@page import="java.util.List"%>
<%@page import="jexclusive.excController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Мероприятия</title>
    </head>
    <body>
        <%
            excController ctrlr = new excController("markiz", "25031996");
            excListEvents events = new excListEvents(ctrlr.displayAll());
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Ответственный</th>
                    <th>Дата и время</th>
                    <th colspan="3">Функции</th>
                </tr>
            </thead>
            <tbody>
                <% for (String[] event : events.getEvents()) {%>
                <tr>
                    <td><%=event[1]%></td>
                    <td><%=event[2]%></td>
                    <td><%=ctrlr.getLoginById(Integer.parseInt(event[3]))%></td>
                    <td><%=event[4]%></td>
                    <td><a href="editEvent.jsp?id=<%=event[0]%>">Изменить</a></td>
                    <td><a href="copyEvent.jsp?id=<%=event[0]%>">Копировать</a></td>
                    <% if (Boolean.parseBoolean(session.getAttribute("type").toString())) { %>
                    <td><a href="deleteEvent.jsp?id=<%=event[0]%>">Удалить</a></td>
                    <%}%>
                </tr>
                <%}%>
            </tbody>
        </table>
        <% if (Boolean.parseBoolean(session.getAttribute("type").toString())) { %>
        
        <form action="addEvent.jsp" method="POST"><input type="submit" value="Добавить" name="addEventButton" /></form>
        <%}%>
        <a href="../pages/profile.jsp">На главную</a>
    </body>
</html>
