package hibernate;

import javafx.scene.effect.MotionBlurBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Ex5_OneToMany {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Mieszkanie2 m1 = new Mieszkanie2("Warszawa", "Morelowa", 7, 52);
        Mieszkanie2 m2 = new Mieszkanie2("Kraków", "Kaskadowa", 4, 21);
        Mieszkanie2 m3 = new Mieszkanie2("Wrocław", "Królewska", 5, 82);
        Mieszkanie2 m4 = new Mieszkanie2("Łódź", "Majdańska", 53, 2);

        List<Mieszkanie2> mieszkanie2ListOs1 = new ArrayList<>();
        mieszkanie2ListOs1.add(m1);
        mieszkanie2ListOs1.add(m2);

        List<Mieszkanie2>mieszkanie2ListOs2 = new ArrayList<>();
        mieszkanie2ListOs2.add(m3);
        mieszkanie2ListOs2.add(m4);


        Osoba2 os1 = new Osoba2("Michał", "Biernacki", mieszkanie2ListOs1);
        Osoba2 os2 = new Osoba2("Jan", "Paś", mieszkanie2ListOs2);

        entityManager.getTransaction().begin();
        entityManager.persist(os1);
        entityManager.persist(os2);


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();


    }
}
