
package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author wmscottsimpsonjr
 */

@Entity
@Table(name="foods")
public class Item {
    
    @Id
    @Column(name="FoodID")
    private int itemID;
    
    @Column(name="FoodName")
    private String name;
    
    @Column(name="Price")
    private double price;
    
    @Column(name="Category")
    private String category;
    
    public Item() {
    this.itemID=0;
    this.name="";
    this.price=0;
    this.category="";
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
}
