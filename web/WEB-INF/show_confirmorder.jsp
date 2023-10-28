<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ShoppingcartTable"%>
<%@page import="model.Shoppingcart"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
            int cartId = ShoppingcartTable.findLastCART();
        %>
        <h1>Your Order is confirmed!</h1>
        <h1>The total amount is $<%= totalPrice %></h1>
    </body>
</html>
