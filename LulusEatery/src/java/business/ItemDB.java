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
public class ItemDB {
    public static List<Item> getItems() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        List<Item> items = null;
        try {
            session = sessionFactory.openSession();
            String qS = "from Item order by Category";
            Query q = session.createQuery(qS);
            items = q.list();
        } catch (Exception e) {
            items = null;
        } finally {
            session.close();
        }
        return items;
    }
//    public static Item getItem(int uid) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = null;
//        User u = null;
//        try {
//            session = sessionFactory.openSession();
////            String qs = "from User where UserID = :uid";
////            Query q = session.createQuery(qs);
////            replaced by annotation and following line:
//            Query q = session.getNamedQuery("dbget_User");
//            q.setInteger("uid", uid);
//            u = (User) q.uniqueResult();
//            
//        } catch (Exception e) {
//            u = null;
//        } finally {
//            session.close();
//        }
//        return u;
//    }
}
