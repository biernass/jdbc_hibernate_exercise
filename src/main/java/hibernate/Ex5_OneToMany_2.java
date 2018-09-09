package hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Ex5_OneToMany_2 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Osoba3 os1 = new Osoba3("Michał", "Biernacki", null);
        Osoba3 os2 = new Osoba3("Jan", "Paś", null);

        Mieszkanie3 m1 = new Mieszkanie3("Warszawa", "Morelowa", 7, 52,os1);
        Mieszkanie3 m2 = new Mieszkanie3("Kraków", "Kaskadowa", 4, 21,os2);
        Mieszkanie3 m3 = new Mieszkanie3("Wrocław", "Królewska", 5, 82, os1);
        Mieszkanie3 m4 = new Mieszkanie3("Łódź", "Majdańska", 53, 2,os1);

        List<Mieszkanie3> mieszkanie3ListOs1 = new ArrayList<>();
        mieszkanie3ListOs1.add(m1);
        mieszkanie3ListOs1.add(m3);
        mieszkanie3ListOs1.add(m4);

        List<Mieszkanie3>mieszkanie3ListOs2 = new ArrayList<>();
        mieszkanie3ListOs2.add(m2);


        os1.setMieszkanieList(mieszkanie3ListOs1);
        os2.setMieszkanieList(mieszkanie3ListOs2);


        entityManager.getTransaction().begin();
        entityManager.persist(os1);
        entityManager.persist(os2);


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();


    }
}
