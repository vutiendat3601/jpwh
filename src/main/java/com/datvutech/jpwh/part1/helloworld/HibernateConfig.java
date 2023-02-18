package com.datvutech.jpwh.part1.helloworld;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.datvutech.jpwh.part1.helloworld.entity.Message;

public class HibernateConfig {

    public static void main(String[] args) {
        Message message = new Message("Hello Hibernate");

        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // session.save(message);
        message = session.get(Message.class, 1L);
        message.setText("Hello Java Persistence with Hibernate");

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    private static final SessionFactory SESSION_FACTORY;

    static {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propsStream = classLoader.getResourceAsStream("application.properties");
        Properties appProps = new Properties();
        try {
            appProps.load(propsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Configuration conf = new Configuration();
        conf.setProperties(appProps);
        conf.addAnnotatedClass(Message.class);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        SESSION_FACTORY = conf.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    private HibernateConfig() {
    }

}
