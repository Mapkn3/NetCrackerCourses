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
            ctrlr.addEvent(request.getParameter("title"), request.getParameter("desc"), Integer.parseInt(request.getParameter("resp")), request.getParameter("date"));
        %>
        <jsp:forward page="libAction.jsp"></jsp:forward>
    </body>
</html>