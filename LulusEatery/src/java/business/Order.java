
package business;

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
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    
    @Column(name="FoodReady")
    @Temporal(TemporalType.DATE)
    private Date pickupdate;
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="orderID")
    @Cascade(CascadeType.ALL)
    @OrderBy("itemID")
    private List<ItemList> itemlist;
}
