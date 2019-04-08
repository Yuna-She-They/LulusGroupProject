/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name="customer")
public class Customer {
    @Id
    @Column(name="CustomerID")
    private int customerID;
    
    @Column(name="Fname")
    private String fname;
    
    @Column(name="Lname")
    private String lname;
    
    @Column(name="Phone")
    private String phone;
    
    @Column(name="Email")
    private String email;
    
    @Column(name="CCnumber")
    private String ccnumber;
}
