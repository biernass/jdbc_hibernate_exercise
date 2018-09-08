package hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Ex4 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Osoba os1 = new Osoba("Michał", "Biernacki", null);
        Osoba os2 = new Osoba("Jan", "Paś", null);
        Osoba os3 = new Osoba("Paweł", "Konopacki", null);
        Osoba os4 = new Osoba("Arkadiusz", "Balcerzak", null);
        Osoba os5 = new Osoba("Kamil", "Rydz", null);

        Mieszkanie m1 = new Mieszkanie("Warszawa", "Morelowa", 7, 52);
        Mieszkanie m2 = new Mieszkanie("Kraków", "Kaskadowa", 4, 21);
        Mieszkanie m3 = new Mieszkanie("Wrocław", "Królewska", 5, 82);
        Mieszkanie m4 = new Mieszkanie("Łódź", "Majdańska", 53, 2);

        os1.setMieszkanie(m1);
        os2.setMieszkanie(m2);
        os3.setMieszkanie(m3);
        os4.setMieszkanie(m4);

        entityManager.getTransaction().begin();

        entityManager.persist(os1);
        entityManager.persist(os2);
        entityManager.persist(os3);
        entityManager.persist(os4);
        entityManager.persist(os5);
        entityManager.persist(m1);
        entityManager.persist(m2);
        entityManager.persist(m3);
        entityManager.persist(m4);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();


    }
}
