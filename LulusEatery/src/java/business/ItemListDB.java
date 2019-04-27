
package business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author wmscottsimpsonjr
 */
public class ItemListDB {
        public static boolean addItemList (ItemList itemlist){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        boolean dbstat = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(itemlist);
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
