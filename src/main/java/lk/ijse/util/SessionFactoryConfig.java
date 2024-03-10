package lk.ijse.util;

import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowDetails;
import lk.ijse.entity.Branch;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;

    private SessionFactory sessionFactory;

    private SessionFactoryConfig(){
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
            Configuration configuration = new Configuration().setProperties(properties);
            sessionFactory=configuration
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Branch.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(BorrowDetails.class)
                    .buildSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SessionFactoryConfig getInstance(){
        return factoryConfig==null ? factoryConfig=new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
