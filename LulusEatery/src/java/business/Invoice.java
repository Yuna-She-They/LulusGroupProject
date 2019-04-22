
package business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author wmscottsimpsonjr
 */

@Entity
@Table(name="invoice")
public class Invoice {
    
    @Id
    @Column(name="InvoiceID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int invoiceID;
    
    @Column(name="CustomerID")
    private int customerID;
    
    @Column(name="InvoiceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoicedate;//sql timestamp
    
    @Column(name="FoodReady")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickupdate;//sql datetime
    
    @Column(name="TotalPrice")
    private double totalprice;
    
    @Column(name="ReadyForPickup")
    private boolean readyforpickup;
    
    @Column(name="PickedUp")
    private boolean pickedup;
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="InvoiceID")
    @Cascade(CascadeType.ALL)
    @OrderBy("InvoiceID")
    private List<ItemList> itemlist;
    
    //took out bc it broke the invoice
//    @OneToOne (fetch=FetchType.EAGER)
//    @JoinColumn (name="CustomerID",insertable=false,updatable=false)
//    private Customer customer;
    
    public Invoice() {
        this.invoiceID=0;
        this.customerID=0;
        this.invoicedate=null;
        this.pickupdate=null;
        this.totalprice=0.0;
        this.readyforpickup=false;
        this.pickedup=false;
        //this.customer = null;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public Date getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isReadyforpickup() {
        return readyforpickup;
    }

    public void setReadyforpickup(boolean readyforpickup) {
        this.readyforpickup = readyforpickup;
    }

    public boolean isPickedup() {
        return pickedup;
    }

    public void setPickedup(boolean pickedup) {
        this.pickedup = pickedup;
    }

    public List<ItemList> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<ItemList> itemlist) {
        this.itemlist = itemlist;
    }
    
    public String getFormattedpickupdate() {
    String pattern = "yyyy-MM-dd hh:mm a";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String formatdate = simpleDateFormat.format(this.pickupdate);
    return formatdate;
    }
    
    public String getWeekdaypickupdate() {
    String pattern = "E, yyyy-MM-dd hh:mm a";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String formatdate = simpleDateFormat.format(this.pickupdate);
    return formatdate;
    }
    
    public String getFormattedinvoicedate() {
    String pattern = "yyyy-MM-dd hh:mm a";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String formatdate = simpleDateFormat.format(this.invoicedate);
    return formatdate;
    }
    
    public boolean isWithinRange(Date testDate) {
        String pattern = "hh:mm A";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startTime = null;
        Date endTime = null;
        
        try {
        startTime = simpleDateFormat.parse("12:00 AM");
        endTime = simpleDateFormat.parse("8:30 PM");
        
        } catch (Exception e) {
            
        }
        if (startTime != null && endTime != null) {
            return !(testDate.before(startTime) || testDate.after(endTime));
        } else {
            return true;
        }
     }

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

}
