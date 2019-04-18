package Servlets;

import business.Invoice;
import business.Item;
import business.ItemDB;
import business.ItemList;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/cart.jsp", msg="";
        Invoice invoice = new Invoice();
        List<Item> allitems;
        List<Item> orderitems = new ArrayList<Item>();
        List<ItemList> itemlistlist = new ArrayList<ItemList>();
        String[] itemIDs;
        String[] itemQs;
        Date readytime = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String readynow;
        
        //really should set items now, then all other invoice info upon submit
        //upon submit, compare current time plus 30 to requested time
        
        //instead of using ItemList, I might want to just use the Item type object now, plus display quantity on the page, then only create the ItemList upon submit
        //...can use an existing full list of Item, then get item by id

        //check to see if itemlistlist has anything already (ie "add more" was clicked, also works if using back button).
        //Can duplicate an item if the user does so and selects an item they've already added. 
        //Maybe put check before adding item to itemlistlist in the else within the next try, and if its already there increase quantity
        //or could not display item on order.jsp if it's already in itemlistlist. probably the former option
        try {
            List<ItemList> ill = (List<ItemList>) request.getSession().getAttribute("itemlistlist");
            if (ill!=null && ill.size()>0){
                itemlistlist = ill;
            }
        } catch (Exception e) {
            
        }
        
        try {
            allitems = (List<Item>) request.getSession().getAttribute("items");
            itemIDs = request.getParameterValues("itemID");
            if (itemIDs==null) {
                msg = "No items selected";
                URL="/order.jsp";
            } else {
                for(int i = 0; i < itemIDs.length; i++){
                    //orderitems.add(ItemDB.getItemByID(itemIDs[i])); replaced with inner loop instead of having to grab from db again:
                    for (Item item : allitems) {
                        if (item.getItemID() == Integer.parseInt((itemIDs[i]))) {
                            //msg += "found item: " + item.getName() +". ";
                            orderitems.add(item);
                            
                            int q = Integer.parseInt(request.getParameter(itemIDs[i]));
                            ItemList il = new ItemList();
                            il.setItemID(Integer.parseInt(itemIDs[i]));
                            il.setQuantity(q);
                            il.setItem(item);
                            //don't se orderID, that's done once the orderID is automatically generated by DB upon submission
                            itemlistlist.add(il);
                            request.getSession().setAttribute("itemlistlist", itemlistlist);
                        }
                    }
                }
                request.getSession().setAttribute("orderitems", orderitems);
            }
        } catch (NumberFormatException e) {
            msg = "Please enter a number for quantity. " + e.getMessage() + ". Items with valid quantities were succesfully added to cart";
            URL = "/order.jsp";
        } catch (Exception e) {
            msg = "item error " + e.getMessage();
        }
        

        
                try{
            
        } catch (Exception e) {
            msg = "Ready time error: " + e.getMessage();
        }
        try {
            readynow = String.valueOf(request.getSession().getAttribute("readynow"));
            if (readynow.equals("later")) {
                try {
                    
                    readytime = formatter.parse(request.getParameter("pickuptime")+":00");
                    //String rt = String.valueOf(request.getSession().getAttribute("readytime"));
                    //readytime = formatter.parse(rt);
                    //readytime = formatter.parse(request.getSession().getAttribute("pickuptime"));
                    //request.getSession().setAttribute("readytime",readytime);
                    invoice.setPickupdate(readytime);
                    request.getSession().setAttribute("invoice",invoice);
                    
                } catch (Exception e) {
                    msg = "Date error: " + e.getMessage();
                }

            } else {
                //really shouldn't set time until submit? Maybe just change upon submit if it's less than 25 minutes away
                Date currenttime = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(currenttime);
                cal.add(Calendar.MINUTE, 30);
//                String rt;
//                rt = formatter.format(cal.getTime());
//                readytime = formatter.parse(rt);
                readytime = cal.getTime();
                invoice.setPickupdate(readytime);
                request.getSession().setAttribute("invoice",invoice);
            }
        } catch (Exception e) {
            msg = "Pickup time error: " + e.getMessage();
        }
        

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
