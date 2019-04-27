/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import business.Item;
import business.ItemDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wmscottsimpsonjr
 */
public class OrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/order.jsp", msg="";
        Date pickuptime;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        
        //removes cart when "begin order" is clicked
        try {
            request.getSession().removeAttribute("cart");
        } catch (Exception e) {
            msg = "Cart not removed. " + e.getMessage() + "<br>";
        }
        
        
        try{
            String readynow = request.getParameter("readynow");
            request.getSession().setAttribute("readynow",readynow);
        } catch (Exception e) {
            msg += "Ready now error: " + e.getMessage() + "<br>";
        }
        
        
        
        List<Item> items;
        try {
            items = ItemDB.getItems();
            if (items != null && items.size() > 0) {
                request.getSession().setAttribute("items", items);
            } else {
                msg += "No items read from file<br>";
            }
        } catch (Exception e) {
            msg += "Servlet error: " + e.getMessage() + "<br>";
        }
        
        
//        try {
//            String pt = request.getParameter("pickuptime");
//            if (!pt.equals("")) {
//            request.getSession().setAttribute("readytime", request.getParameter("pickuptime"));
//            }
//        } catch (Exception e) {
//            msg += "Pickup Time error: " + e.getMessage();
//        }
        
        
        
        request.setAttribute("msg", msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
