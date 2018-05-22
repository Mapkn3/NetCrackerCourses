<%@page import="jexclusive.excController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить мероприятие</title>
    </head>
    <body>
        <form method="POST" action="addSQLevent.jsp">
            <table>
                <tbody>
                    <tr>
                        <td>Название</td>
                        <td><input type="text" name="title" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Описание</td>
                        <td>
                            <textarea name="desc" rows="5" cols="20">
                            </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Ответственный</td>
                        <td><input type="text" name="resp" value="" /></td>
                    </tr>
                    <tr>
                        <td>Дата проведения</td>
                        <td><input type="text" name="date" value="" size="8" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Добавить" name="addEventButton" />
        </form>
    </body>
</html>
