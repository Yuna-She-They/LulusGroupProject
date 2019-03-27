
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
@Table(name="ItemList")
public class ItemList {
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="OrderID")
    private int orderid;
    
    @Column(name="Quantity")
    private int quantity;
    
    @Column(name="ItemID")
    private int itemID;
    
    @OneToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="itemID",insertable=false,updatable=false)
    private Item item;
}
