<%-- 
    Document   : sign-in
    Created on : 24 Aug 2023, 1:15:28 pm
    Author     : erictgb
--%>

<%@page import="dao.JdbiDaoFactory"%>
<%@page import="domain.Product"%>
<%@page import="java.util.Collection"%>
<%@page import="dao.ProductCollectionsDAO"%>
<%@page import="dao.ProductDAO"%>
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
            <h1>Products</h1>
            <a href="view-products.jsp?major=All"><button>All</button></a>

            <%
//                ProductDAO dao = new ProductCollectionsDAO();
                ProductDAO dao = JdbiDaoFactory.getProductDAO();
                Collection<String> categories = dao.getCategories();

                for (String category : categories) {
            %>

                <a href="view-products.jsp?category=<%= category %>"><button><%= category %></button></a>

            <%
                }
            %>
            <table>
		<thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Available</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        String selectedCategory = request.getParameter("category");

                        Collection<Product> products;

                        // if there is no major parameter, or "All" is requested, return all students
                        if (selectedCategory == null || selectedCategory.equals("All")) {
                                products = dao.getProducts();
                        } else {
                                products = dao.filterByCategory(selectedCategory);
                        }

                        for (Product product : products) {
                    %>
                    <tr>
                        <td><%= product.getName() %></td>
                        <td><%= product.getDescription() %></td>
                        <td><%= product.getListPrice() %></td>
                        <td><%= product.getQuantityInStock() %></td>
                        <td><button>Buy</button></td>
                    </tr>
                    <%
                            }
                    %>
                </tbody>
            </table>
        </main>
    </body>
</html>
