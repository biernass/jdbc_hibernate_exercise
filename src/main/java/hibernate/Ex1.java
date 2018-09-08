package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Ex1 {
    public static void main(String[] args) {


        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory(); // w przypadku gdy klasy mamy podane w cfg.

        //configuration.addAnnotatedClass(Person.class);
       /* SessionFactory sessionFactory = configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
        );*/


        Session session = sessionFactory.openSession();
        session.beginTransaction();


        Person p = new Person(1, "Tomek");
        session.save(p);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
        Root<Person> from = query.from(Person.class);
        query.select(from); // przejrzec co można robić na Criteriaquery, CriteriaBuilder, Root

        System.out.println(session.createQuery(query).getResultList());


        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }
}
