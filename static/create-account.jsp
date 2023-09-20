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
        <link rel="stylesheet" href="css/style.css"/>
        <title>Doohickies and Widgets</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>Create an Account</h1>
            <fieldset>
                <%
                    String validation = (String) session.getAttribute("signup-validation");
                    validation = validation != null ? validation : "";
                %>
                <p style="color:red"><%= validation %></p>
                <legend>Account Details</legend>
                <form action="create-account" method="post">
                    <label>Username:</label><input type="text" name="username" required/>
                    <label>First Name:</label><input type="text" name="firstName" required/>
                    <label>Last Name:</label><input type="text" name="lastName" required/>
                    <label>Email:</label><input type="text" name="email" required/>
                    <label>Address:</label><textarea name="address" required></textarea>
                    <label>Password:</label><input type="password" name="password" required/>
                    
                    <button type="submit">Create Account</button>
                </form>
            </fieldset>
        </main>
    </body>
</html>
