package ch.mse.itsec.jakarta_jdbc.datasource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerfactory {

    public static EntityManager createEntityManager() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager entitymanager = emfactory.createEntityManager();
        return entitymanager;
    }
}
