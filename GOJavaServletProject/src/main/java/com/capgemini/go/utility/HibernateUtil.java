package com.capgemini.go.utility;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.capgemini.go.entity.AddressEntity;
import com.capgemini.go.entity.OrderCancelEntity;
import com.capgemini.go.entity.OrderProductMapEntity;
import com.capgemini.go.entity.OrderReturnEntity;
import com.capgemini.go.entity.RetailerInventoryEntity;
import com.capgemini.go.entity.WishlistEntity;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://remotemysql.com:3306/iMIuEwHYH8");
				settings.put(Environment.USER, "iMIuEwHYH8");
				settings.put(Environment.PASS, "QXFKvh2Ni9");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
               // settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                // Add all classes that need to be mapped here
                configuration.addAnnotatedClass(RetailerInventoryEntity.class);
                configuration.addAnnotatedClass(AddressEntity.class);

                configuration.addAnnotatedClass(WishlistEntity.class);

                configuration.addAnnotatedClass(OrderCancelEntity.class);
                configuration.addAnnotatedClass(OrderProductMapEntity.class);

                configuration.addAnnotatedClass(OrderReturnEntity.class);



                // end of this
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
