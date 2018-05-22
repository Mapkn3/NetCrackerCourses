<%@page import="jexclusive.excController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Отправка данных</title>
    </head>
    <body>
        <%
            excController ctrlr = new excController("markiz", "25031996");
            String[] info = ctrlr.displayById(Integer.parseInt(request.getParameter("id")));
            ctrlr.addEvent(info[1], info[2], Integer.parseInt(info[3]), info[4]);
        %>
        <jsp:forward page="libAction.jsp"></jsp:forward>
    </body>
</html>
