<%-- 
    Document   : menu
    Created on : 26.03.2016, 14:19:51
    Author     : Nikolay
--%>

<%@page import="model.Tree"%>
<%@page import="controller.DatabaseController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../styles/menu.css" media="screen">
        <title>Library help system</title>
    </head>
    <body>
        <%!
            private DatabaseController dc = new DatabaseController();
        %>
        <%
            Tree tree = dc.getTree();
            out.print("<ol class=\"tree\">\n" + tree.toHTML() + "</ol>\n");
        %>
    </body>
</html>
