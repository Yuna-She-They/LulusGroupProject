
package business;

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
    @Temporal(TemporalType.DATE)
    private Date invoicedate;//sql timestamp
    
    @Column(name="FoodReady")
    @Temporal(TemporalType.DATE)
    private Date pickupdate;//sql datetime
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="InvoiceID")
    @Cascade(CascadeType.ALL)
    @OrderBy("InvoiceID")
    private List<ItemList> itemlist;
    
    public Invoice() {
        this.invoiceID=0;
        this.customerID=0;
        this.invoicedate=null;
        this.pickupdate=null;
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

    public List<ItemList> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<ItemList> itemlist) {
        this.itemlist = itemlist;
    }

}
