<%@page import="jexclusive.excController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Обработка данных</title>
    </head>
    <body>
        <%
            excController ctrlr = new excController("markiz", "25031996");
            ctrlr.deleteById(Integer.parseInt(request.getParameter("id")));
        %>
        <jsp:forward page="libAction.jsp"></jsp:forward>
    </body>
</html>
