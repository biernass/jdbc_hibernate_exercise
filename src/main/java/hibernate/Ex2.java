package hibernate;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Ex2 {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();

        entityManager.getTransaction().begin();

        Person p = new Person(0, "Tomek");
        entityManager.persist(p);

        entityManager.getTransaction().commit();
        entityManager.getEntityManagerFactory().close();
        entityManager.close();
    }
}
