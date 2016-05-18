package chapter03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PersistManager {

    public static SessionFactory sessionFactory=null;


    static{
        // 根据默认位置的Hibernate配置文件的配置信息，创建一个Configuration实例
        Configuration config = new Configuration();
        //加载Customer类的对象-关系映射文件
        config.addClass(Customer.class);
        // 创建SessionFactory实例 */
        sessionFactory= config.buildSessionFactory();
    }


    public static void main(String[] args) {
        Customer customer=new Customer();
        customer.setName("Tom");
        customer.setEmail("tom@yahoo.com");
        customer.setPassword("1234");
        customer.setPhone(55556666);
        customer.setAddress("Shanghai");
        customer.setSex('M');
        customer.setDescription("I am very honest.");

        saveCustomer(customer);
    }


    public static void saveCustomer(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(customer);
            tx.commit();

        }catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }


    /*
    public static void listAllCustomer(){
        Session session = sessionFactory.openSession(); //创建一个会话
        Transaction tx = null;
        try {
            tx = session.beginTransaction(); //开始一个事务
            Query query=session.createQuery("from Customer as c order by c.name asc");
            List customers=query.list();
            for (Iterator it = customers.iterator(); it.hasNext();) {
                printCustomer(context,out,(Customer) it.next());
            }

            tx.commit(); //提交事务

        }catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
    */
}
