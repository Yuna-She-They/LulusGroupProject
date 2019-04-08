
package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author wmscottsimpsonjr
 */

@Entity
@Table(name="food_list")
public class ItemList {
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="InvoiceID")
    private int orderid;
    
    @Column(name="FoodID")
    private int itemID;
    
    @Column(name="Quantity")
    private int quantity;
    
    @OneToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="itemID",insertable=false,updatable=false)
    private Item item;
}
