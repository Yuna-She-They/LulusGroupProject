
package Servlets;

import business.Customer;
import business.CustomerDB;
import business.Invoice;
import business.InvoiceDB;
import business.ItemList;
import business.ItemListDB;
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
public class PlaceOrderServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/checkout.jsp", msg = "";
        Invoice invoice = new Invoice();
        Customer customer = new Customer();
        List<ItemList> cart = new ArrayList<ItemList>();
        Date pickuptime = null;
        String pickupstring = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        boolean custadded = false;
        boolean invoiceadded = false;
        boolean itemlistadded = false;
        boolean timechanged = false;
        

        try {
            customer.setFname(request.getParameter("firstname"));
            customer.setLname(request.getParameter("lastname"));
            customer.setPhone(request.getParameter("phone"));
            customer.setEmail(request.getParameter("email"));
            customer.setCcnumber(request.getParameter("ccnumber"));
            custadded = CustomerDB.addCustomer(customer);
            if (custadded) {
                //msg += "Customer added: " + customer.getCustomerID() + "<br>";
                request.getSession().setAttribute("placedcustomer", customer);
            } else {
                msg += "Customer not added.<br>";
            }
            
            
        } catch (Exception e) {
            msg = "Customer write error: " + e.getMessage();
        }
        
        if (custadded) {
            try {
                pickupstring = String.valueOf(request.getSession().getAttribute("pickuptime"));
                pickuptime = formatter.parse(String.valueOf(request.getSession().getAttribute("pickuptime")));
                
                try {
                    Date currenttime = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(currenttime);
                    cal.add(Calendar.MINUTE, 20);
                    Date timeplustwenty = cal.getTime();
                    if (timeplustwenty.after(pickuptime)) {
                        pickuptime = timeplustwenty;
                        timechanged = true;
                        request.getSession().setAttribute("timechanged", timechanged);
                    }
                } catch (Exception e) {
                    msg += "Time change error: " + e.getMessage() + "<br>";
                }
                //msg = pickupstring;
                Double total = (Double) request.getSession().getAttribute("total");
                Date currenttime = new Date();
                invoice.setInvoicedate(currenttime);
                //todo: check that it's at least 20 minutes later
                invoice.setPickupdate(pickuptime);
                invoice.setCustomerID(customer.getCustomerID());
                invoice.setTotalprice(total);
                invoiceadded = InvoiceDB.addInvoice(invoice);
                if (invoiceadded) {
                    //msg += "Invoice added: " + invoice.getInvoiceID() + "<br>";
                } else {
                    msg += "Invoice not added.<br>";
                }

            } catch (Exception e) {
                msg += "Invoice write error: " + e.getMessage() + "<br>";
            }
        }
        
        if (invoiceadded) {
            try {
                cart = (List<ItemList>) request.getSession().getAttribute("cart");
                //int q = 0;
                for (ItemList il : cart) {
                    il.setOrderid(invoice.getInvoiceID());
                    itemlistadded = ItemListDB.addItemList(il);
                    //q++;
                }
                if (itemlistadded) {
                    //msg += q + " items ordered.<br>";
                }
            } catch (Exception e) {
                msg += "Itemlist write error: " + e.getMessage() + "<br>";
            }
        }
        
        if (itemlistadded) {
            URL = "/orderplaced.jsp";
            //to display on success page:
            request.getSession().setAttribute("placedtotal", request.getSession().getAttribute("total"));
            request.getSession().setAttribute("placedcart", request.getSession().getAttribute("cart"));
            request.getSession().setAttribute("placedinvoice", request.getSession().getAttribute("invoice"));
            request.getSession().setAttribute("placedreadytime", request.getSession().getAttribute("readytime"));
            
            //then clear everything:
            request.getSession().removeAttribute("total");
            request.getSession().removeAttribute("cart");
            request.getSession().removeAttribute("invoice");
            request.getSession().removeAttribute("items");
            request.getSession().removeAttribute("readynow");
            request.getSession().removeAttribute("readytime");
            
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