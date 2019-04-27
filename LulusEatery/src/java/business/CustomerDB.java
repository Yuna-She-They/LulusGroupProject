/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author wmscottsimpsonjr
 */
public class CustomerDB {
    public static List<Customer> getCustomers() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        List<Customer> customers = null;
        try {
            session = sessionFactory.openSession();
            String qS = "from Customer order by Lname";
            Query q = session.createQuery(qS);
            customers = q.list();
        } catch (Exception e) {
            customers = null;
        } finally {
            session.close();
        }
        return customers;
    }
    public static boolean addCustomer (Customer customer){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        boolean dbstat = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
            
            dbstat = true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return dbstat;
    }
}
