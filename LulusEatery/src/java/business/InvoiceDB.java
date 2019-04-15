
package business;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author wmscottsimpsonjr
 */
public class InvoiceDB {
    public static List<Invoice> getInvoices() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        List<Invoice> invoices = null;
        try {
            session = sessionFactory.openSession();
            String qS = "from Invoice order by InvoiceDate";
            Query q = session.createQuery(qS);
            invoices = q.list();
        } catch (Exception e) {
            invoices = null;
        } finally {
            session.close();
        }
        return invoices;
    }
    public static boolean addInvoice (Invoice invoice){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        boolean dbstat = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(invoice);
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
