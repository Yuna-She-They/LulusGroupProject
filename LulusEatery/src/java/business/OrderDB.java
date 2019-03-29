
package business;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author wmscottsimpsonjr
 */
public class OrderDB {
    public static List<Order> getOrders() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        List<Order> orders = null;
        try {
            session = sessionFactory.openSession();
            String qS = "from Order order by OrderDate";
            Query q = session.createQuery(qS);
            orders = q.list();
        } catch (Exception e) {
            orders = null;
        } finally {
            session.close();
        }
        return orders;
    }
    public static boolean addOrder (Order order){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        boolean dbstat = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(order);
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
