package br.ifpr.paranavai.jogo.conexao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory SESSION_FACTORY;
    static {
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Falha ao criar a SessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return SESSION_FACTORY.openSession();
    }

    public static void closeSession() {
        SESSION_FACTORY.close();
    }

}
