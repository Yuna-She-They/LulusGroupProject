
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
    @JoinColumn (name="FoodID",insertable=false,updatable=false)
    private Item item;
    
    public ItemList() {
        id=0;
        orderid=0;
        itemID=0;
        quantity=0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
