package hibernate.jparelacje;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private int id;

    private String nameOfCompany;
    private int initialCapital;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Employee> employeeList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    public Company() {
    }

    public Company(String nameOfCompany, int initialCapital, Address address, List<Employee> employeeList) {
        this.nameOfCompany = nameOfCompany;
        this.initialCapital = initialCapital;
        this.address = address;
        this.employeeList = employeeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public int getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(int initialCapital) {
        this.initialCapital = initialCapital;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", nameOfCompany='" + nameOfCompany + '\'' +
                ", initialCapital=" + initialCapital +
                ", employeeList=" + employeeList +
                ", address=" + address +
                '}';
    }
}
