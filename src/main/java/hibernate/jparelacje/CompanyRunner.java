package hibernate.jparelacje;

import hibernate.Mieszkanie3;
import hibernate.Osoba3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CompanyRunner {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Company company1 = new Company("Firma1", 235321, null, null);
        Company company2 = new Company("Firma2", 2351, null, null);

        Address address1 = new Address("Książęca", "21", "01-212", "Warszawa",null,null);
        Address address2 = new Address("Dziwna", "1", "01-232", "Kraków",null,null);
        Address address3 = new Address("Wojska Polskiego", "10", "01-132", "Wrocław",null,null);
        Address address4 = new Address("Wojska Polskiego", "10", "01-132", "Wrocław",null,null);
        Address address5 = new Address("Wojska Polskiego", "10", "01-132", "Wrocław",null,null);
        Address address6 = new Address("Wojska Polskiego", "10", "01-132", "Wrocław",null,null);


        Employee employee1 = new Employee("Jan", 12400, 1200, null);
        Employee employee2 = new Employee("Anna", 22000, 100, null);
        Employee employee3 = new Employee("Bronek", 2000, 50, null);
        Employee employee4 = new Employee("Alicja", 2200, 10, null);

        address2.setCompany(company1);
        address1.setEmployee(employee1);
        address3.setEmployee(employee2);
        employee1.setAddress(address1);
        employee2.setAddress(address3);

        address6.setCompany(company2);
        address4.setEmployee(employee3);
        address5.setEmployee(employee4);
        employee3.setAddress(address4);
        employee4.setAddress(address5);

        List<Employee> employeeList1 = new ArrayList<>();
        employeeList1.add(employee1);
        employeeList1.add(employee2);

        List<Employee> employeeList2 = new ArrayList<>();
        employeeList2.add(employee3);
        employeeList2.add(employee4);

        company1.setAddress(address2);
        company1.setEmployeeList(employeeList1);

        company2.setAddress(address6);
        company2.setEmployeeList(employeeList2);

        entityManager.getTransaction().begin();

        entityManager.persist(company1);
        entityManager.persist(company2);

        entityManager.getTransaction().commit();

        Company company = entityManager.find(Company.class, 1);

        System.out.println(company);

        entityManager.close();
        entityManagerFactory.close();


    }

}
