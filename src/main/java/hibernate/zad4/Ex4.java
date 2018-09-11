package hibernate.zad4;

import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Scanner;

public class Ex4 {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();


    public static void main(String[] args) {

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

        insertNewPerson();

        entityManager.getTransaction().commit();

        System.out.println(getUsers());
        System.out.println(getUsersByTown("Warszawa"));
        System.out.println(getUserById());


        entityManager.close();
        entityManagerFactory.close();


    }

    public static List<Osoba> getUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Osoba> query = criteriaBuilder.createQuery(Osoba.class);
        Root<Osoba> from = query.from(Osoba.class);

        query.select(from);

        return entityManager.createQuery(query).getResultList();
    }

    public static List<Osoba> getUsersByTown(String town) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Osoba> query = criteriaBuilder.createQuery(Osoba.class);
        Root<Osoba> from = query.from(Osoba.class);
        Join<Osoba, Mieszkanie> osobaMieszkanieJoin = from.join("mieszkanie");

        query
                .select(from)
                .where(criteriaBuilder.equal(osobaMieszkanieJoin.get("miasto"), town));

        query.select(from);

        return entityManager.createQuery(query).getResultList();
    }

    public static Osoba getUserById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id użytkownika: ");
        String Id = scanner.nextLine();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Osoba> query = criteriaBuilder.createQuery(Osoba.class);
        Root<Osoba> from = query.from(Osoba.class);

        query
                .select(from)
                .where(criteriaBuilder.equal(from.get("id"), Id));

        return entityManager.createQuery(query).getSingleResult();

    }

    public static void insertNewPerson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------Wprowadzanie nowego użytkownika------");
        System.out.print("Podaj imię: ");
        String name = scanner.nextLine();
        System.out.print("Podaj Nazwisko: ");
        String surname = scanner.nextLine();
        System.out.print("Podaj miasto: ");
        String city = scanner.nextLine();
        System.out.print("Podaj ulicę: ");
        String street = scanner.nextLine();
        System.out.print("Podaj numer domu: ");
        int homeNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Podaj numer mieszkania: ");
        int localNumber = Integer.parseInt(scanner.nextLine());


        Osoba osoba = new Osoba(name, surname, null);

        Mieszkanie mieszkanie = new Mieszkanie(city, street, homeNumber, localNumber);

        osoba.setMieszkanie(mieszkanie);
        entityManager.persist(osoba);




    }


}
