package Servlets;

import business.Invoice;
import business.Item;
import business.ItemDB;
import business.ItemList;
import java.io.IOException;
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
        List<Item> orderitems = new ArrayList<Item>();//May not need this. notice that it's filled a few times.
        List<ItemList> cart = new ArrayList<ItemList>();
        String[] itemIDs;
        String[] itemQs;
        Date pickuptime = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String readynow;
        double total = 0.0;
        boolean invalidtime=false;
        
        //the following is commented out because now I just want to rebuild the whole thing since items stay checked when going back
        //check to see if cart has anything already (ie "add more" was clicked, also works if using back button).
        //Can duplicate an item if the user does so and selects an item they've already added. 
        //Maybe put check before adding item to cart in the else within the next try, and if its already there increase quantity
        //or could not display item on order.jsp if it's already in cart. probably the former option
//        try {
//            List<ItemList> ill = (List<ItemList>) request.getSession().getAttribute("cart");
//            if (ill!=null && ill.size()>0){
//                cart = ill;
//            }
//        } catch (Exception e) {
//            msg += "Cart read error. " + e.getMessage();
//        }
        
        //grab total if it exists, otherwise doesn't count items added before clicking "add more" / going back
//        try {
//            total = (Double) request.getSession().getAttribute("total");
//        } catch (Exception e) {
//            //there was no total
//        }
        
        
        if (request.getParameter("viewcart") != null) {

            try {
                
                readynow = String.valueOf(request.getSession().getAttribute("readynow"));
                if (readynow.equals("later")) {
                    
                    try {
                        String pt = String.valueOf(request.getParameter("pickuptime"));
                        if (pt != null) {
                            request.getSession().setAttribute("ptformat", pt);
                        }
                    } catch (Exception e) {
                        //nothing
                    }

                      //use String to check availability:
                      try {
                          SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
                          Date ptdate = formatter2.parse(request.getParameter("pickuptime"));
                          String pt = String.valueOf(ptdate);
                          //split by space:
                          String[] splitpt = pt.split("\\s+");
                          //pos 3 has HH:mm:
                          String splittime = splitpt[3];
                          //Separate hour and minute:
                          String[] splithourmin = splittime.split(":");
                          int splithour = Integer.parseInt(splithourmin[0]);
                          int splitmin = Integer.parseInt(splithourmin[1]);
                          //if before 12 or after 9pm (hour is greater than 8)
                          if (splithour < 12 || splithour > 20) {
                              invalidtime=true;
                          }
                          //if it's 8pm something, and the minutes is greater than 30
                          if (splithour == 20 && splitmin > 30) {
                              invalidtime=true;
                          }
                          //if it's sunday
                          String dayofweek = splitpt[0];
                          if (dayofweek.startsWith("Su")) {
                              invalidtime=true;
                          }
                          if (invalidtime){
                              URL = "/order.jsp";
                              msg = "Please select a time Monday&ndash;Saturday, Noon&ndash;8:30 PM<br>";
                          } else {
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
                                                cart.add(il);
                                                total += q*item.getPrice();

                                            }
                                        }
                                    }
                                    request.getSession().setAttribute("cart", cart);
                                    request.getSession().setAttribute("total", total);
                                    request.getSession().setAttribute("orderitems", orderitems);
                                }
                            } catch (NumberFormatException e) {
                                msg = "Please enter a number for quantity. " + e.getMessage() + ". Items with valid quantities were succesfully added to cart";
                                URL = "/order.jsp";
                            } catch (Exception e) {
                                msg = "item error " + e.getMessage();
                            }
                              
                              //unparseable by PlaceOrderServlet: shows up in format: Sat Apr 27 15:22:00 CDT 2019
                              request.getSession().setAttribute("pickuptime",ptdate);
                              invoice.setPickupdate(ptdate);
                              request.getSession().setAttribute("invoice",invoice);
                          }
                          
                          
                      } catch (Exception e) {
                          URL = "/order.jsp";
                          msg = "Please select a pickup time.<br>";
                      }


                //if ready at soonest available time:
                } else {
                    
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
                                        cart.add(il);
                                        total += q*item.getPrice();

                                    }
                                }
                            }
                            request.getSession().setAttribute("cart", cart);
                            request.getSession().setAttribute("total", total);
                            request.getSession().setAttribute("orderitems", orderitems);
                        }
                    } catch (NumberFormatException e) {
                        msg = "Please enter a number for quantity. " + e.getMessage() + ". Items with valid quantities were succesfully added to cart";
                        URL = "/order.jsp";
                    } catch (Exception e) {
                        msg = "item error " + e.getMessage();
                    }

                    Date currenttime = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(currenttime);
                    cal.add(Calendar.MINUTE, 30);
                    pickuptime = cal.getTime();
                    String pickupstring = String.valueOf(pickuptime);
                    
                    //split by space:
                    String[] splitpt = pickupstring.split("\\s+");
                    String dayofweek = splitpt[0];
                    //pos 3 gives HH:mm:ss
                    String splittime = splitpt[3];
                    String splithourmin[] = splittime.split(":");
                    int splithour = Integer.parseInt(splithourmin[0]);
                    int splitmin = Integer.parseInt(splithourmin[1]);
                    
                    //if sunday, move to next day at noon
                    if (dayofweek.startsWith("Su")) {
                        cal.add(Calendar.DATE, 1);
                        cal.set(Calendar.HOUR_OF_DAY, 12);
                        cal.set(Calendar.MINUTE, 0);
                        pickuptime = cal.getTime();
                        msg = "Your pickup has been scheduled on the next available day at noon.<br>";
                    } else if (splithour < 12){
                        cal.set(Calendar.HOUR_OF_DAY, 12);
                        cal.set(Calendar.MINUTE, 0);
                        pickuptime = cal.getTime();
                        msg = "Your pickup has been scheduled at noon after opening.<br>";
                    } else if (splithour > 20 || (splithour == 20 && splitmin > 30)) {
                        //if it's already saturday, you need to add an extra day so that pickup day doesn't become sunday
                        if (dayofweek.startsWith("Sa")){
                            cal.add(Calendar.DATE, 1);
                        }
                        cal.add(Calendar.DATE, 1);
                        cal.set(Calendar.HOUR_OF_DAY, 12);
                        cal.set(Calendar.MINUTE, 0);
                        pickuptime = cal.getTime();
                        msg = "Your pickup has been scheduled on the next available day at noon.<br>";
                    }
                    
                    
                    
                    String pickupformat = formatter.format(pickuptime);
                    invoice.setPickupdate(pickuptime);
                    request.getSession().setAttribute("pickuptime",pickupformat);
                    request.getSession().setAttribute("invoice",invoice);
                }
            } catch (Exception e) {
                msg = "Pickup time error: " + e.getMessage();
            }
        }
        
        
        if (request.getParameter("back") != null) {
            URL = "/order.jsp";
        }
        
        
        if (request.getParameter("update") != null) {
            total = 0.0;
            try {
                allitems = (List<Item>) request.getSession().getAttribute("items");
                itemIDs = request.getParameterValues("itemID");
                List<ItemList> newcart = new ArrayList<ItemList>();
                for(int i = 0; i < itemIDs.length; i++){
                    for (Item item : allitems) {
                        if (item.getItemID() == Integer.parseInt((itemIDs[i]))) {

                            int q = Integer.parseInt(request.getParameter(itemIDs[i]));
                            if (q != 0) {
                                orderitems.add(item);
                                ItemList il = new ItemList();
                                il.setItemID(Integer.parseInt(itemIDs[i]));
                                il.setQuantity(q);
                                il.setItem(item);
                                newcart.add(il);
                                total += q*item.getPrice();
                            }
                        }
                    }
                }
                request.getSession().setAttribute("cart", newcart);
                request.getSession().setAttribute("total", total);
                request.getSession().setAttribute("orderitems", orderitems);
            } catch (Exception e) {
                msg +="Cart update error: " + e.getMessage();
            }
            
        }
        
        
        if (request.getParameter("checkout") != null) {
            URL ="/checkout.jsp";
            
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
