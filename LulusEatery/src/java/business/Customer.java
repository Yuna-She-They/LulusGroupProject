/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    
    public Customer(){
        this.customerID=0;
        this.fname="";
        this.lname="";
        this.phone="";
        this.email="";
        this.ccnumber="";
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCcnumber() {
        return ccnumber;
    }

    public void setCcnumber(String ccnumber) {
        this.ccnumber = ccnumber;
    }
}
