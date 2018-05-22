<%@page import="java.util.List"%>
<%@page import="jexclusive.excController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изменить мероприятие</title>
    </head>
    <body>
        <%
            excController ctrlr = new excController("markiz", "25031996");
            String[] info = ctrlr.displayById(Integer.parseInt(request.getParameter("id")));
        %>
        <form method="POST" action="editSQLevent.jsp">
            <select name="participants">
                <%
                    for (String user : ctrlr.allUsers()) {
                %>
                <option><%=user%></option>
                <%}%>
            </select>
            <input type="hidden" name="id" value="<%=info[0]%>" readonly="readonly" />
            <table>
                <tbody>
                    <tr>
                        <td>Название</td>
                        <td><input type="text" name="title" value="<%=info[1]%>" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Описание</td>
                        <td>
                            <textarea name="desc" rows="5" cols="20">
                                <%=info[2]%>
                            </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Ответственный</td>
                        <td><input type="text" name="resp" value="<%=info[3]%>" /></td>
                    </tr>
                    <tr>
                        <td>Дата проведения</td>
                        <td><input type="text" name="date" value="<%=info[4]%>" size="8" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Изменить" name="editEventButton" />
        </form>
    </body>
</html>
