package hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Ex3 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person2 p = new Person2("Tomek", 20, null);
        Address a = new Address("królewska", "10011", p);
        p.setAddress(a);


        entityManager.getTransaction().begin();

        entityManager.persist(a);
        entityManager.persist(p);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }
}
