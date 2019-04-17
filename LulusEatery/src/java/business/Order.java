
package business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
public class Order {
    
    @Id
    @Column(name="InvoiceID")
    private int orderID;
    
    @Column(name="CustomerID")
    private int customerID;
    
    @Column(name="InvoiceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;//sql timestamp
    
    @Column(name="FoodReady")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickupdate;//sql datetime
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="InvoiceID")
    @Cascade(CascadeType.ALL)
    @OrderBy("FoodID")
    private List<ItemList> itemlist;
    
    public Order() {
        this.orderID=0;
        this.customerID=0;
        this.orderdate=null;
        this.pickupdate=null;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
    }

    public List<ItemList> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<ItemList> itemlist) {
        this.itemlist = itemlist;
    }
    public String getFormatteddate() {
        String pattern = "yyyy-MM-dd hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formatdate = simpleDateFormat.format(new Date());
        return formatdate;
    }
}
