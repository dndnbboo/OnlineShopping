<%@page import="java.util.Iterator"%>
<%@page import="model.ProductsTable"%>
<%@page import="model.Products"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DVD Catalog</title>
    </head>
    
    <jsp:useBean id="mov" class="model.Products" scope="request"/>
    <body>
        <form name="addMovie" action="AddMovieController" method="POST">
            <center>
            <h1>DVD Catalog</h1>
            <table border="1">
            <tr>
                <th>DVD Names</th>
                <th>Rate</th>
                <th>Year</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>    
            <tbody>
            <% List<Products> productsList = ProductsTable.findAllProducts(); %>
            <% for (Products pd : productsList) { %>
                <tr>
                    <td><input type="checkbox" name="movies" value="<%= pd.getId() %>"/> 
                        <%= pd.getMovie() %>
                    </td>
                    <td><%= pd.getRating() %></td>
                    <td><%= pd.getYearcreate() %></td>
                    <td><%= pd.getPrice() %></td>
                    <td>
                        <input type="number" name="quantity<%= pd.getId() %>" style="width: 100%;" />
                    </td>
                </tr>
            <% } %>
            </tbody>
            </table>
            <a href="index.html">Add To Cart</a>
        </center>
        </form>
    </body>
</html>
