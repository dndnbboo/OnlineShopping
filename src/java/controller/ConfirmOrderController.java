/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Products;
import model.ProductsTable;
import model.Shoppingcart;
import model.ShoppingcartPK;
import model.ShoppingcartTable;
import utilities.UpdateShoppingCart;

/**
 *
 * @author User
 */
public class ConfirmOrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String[] productIds = request.getParameterValues("productId");
        Logger logger = Logger.getLogger(ConfirmOrderController.class.getName());
        
        synchronized(this.getServletContext()) {
            List<Shoppingcart> shoppingcartList = ShoppingcartTable.findAllShoppingcart();
            int cartId;
            
            int checkUser = 1;
            UpdateShoppingCart.finishUpdating(this.getServletContext(), checkUser);
            this.getServletContext().notifyAll(); 
            try {
                Random rand = new Random();
                int num = rand.nextInt(10)+1;
                Thread.sleep(num * 1000);
            } catch (InterruptedException e) {
                logger.severe("An error occurred: " + e.getMessage());
            }
            
            if (shoppingcartList.isEmpty()) {
                cartId = 1;
            } else {
                Shoppingcart lastProduct = shoppingcartList.get(shoppingcartList.size() - 1);
                cartId = lastProduct.getShoppingcartPK().getCartId() + 1;
            }
            
            for (String productId : productIds) {
                int quantity = Integer.parseInt(request.getParameter("quantity" + productId));
                int Id = Integer.parseInt(productId);
                Products prod = ProductsTable.findProductsById(Id);
                Shoppingcart cart = new Shoppingcart();
                ShoppingcartPK cartPK = new ShoppingcartPK(cartId, Id);
                cart.setShoppingcartPK(cartPK);
                cart.setQuantity(quantity);
                cart.setProducts(prod);
                ShoppingcartTable.insertShoppingcart(cart);
            }        
        }
        HttpSession session = request.getSession();
        session.invalidate();
        
        request.getRequestDispatcher("RemoveOrderController").forward(request, response);
    }
}
