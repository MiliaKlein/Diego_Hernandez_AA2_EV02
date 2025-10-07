<%-- 
    Document   : landing
    Created on : 7 oct. 2025, 10:05:01
    Author     : Smarts3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
        >
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("Connected") == null) {
                response.sendRedirect("index.html");
                return;
            }
            %>
        <h1>Hello World!</h1>
    </body>
</html>
