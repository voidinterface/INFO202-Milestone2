<%-- 
    Document   : sign-in
    Created on : 24 Aug 2023, 1:15:28 pm
    Author     : erictgb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <title>Doohickies and Widgets</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>Sign In</h1>
            <p>Please sign in to continue.</p>
            <fieldset>
                <legend>Account Details</legend>
                <%
                    String validation = (String) session.getAttribute("validation");
                    validation = validation != null ? validation : "";
                %>
                <p style="color:red"><%= validation %></p>
                <form action="sign-in" method="post">
                    <label>Username:</label><input type="text" name="username" required/>
                    <label>Password:</label><input type="password" name="password" required/>
                    
                    <button type="submit">Sign In</button>
                </form>
            </fieldset>
            <p> If you don't have an account then you can 
                <a href="create-account.jsp">create one</a>.</p>
        </main>
    </body>
</html>
