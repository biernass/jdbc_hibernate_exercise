package hibernate.jparelacje;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private int id;

    private String streetName;
    private String numberOfHome;
    private String postCode;
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Address() {
    }


    public Address(String streetName, String numberOfHome, String postCode, String city, Company company, Employee employee) {
        this.streetName = streetName;
        this.numberOfHome = numberOfHome;
        this.postCode = postCode;
        this.city = city;
        this.company = company;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumberOfHome() {
        return numberOfHome;
    }

    public void setNumberOfHome(String numberOfHome) {
        this.numberOfHome = numberOfHome;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", numberOfHome='" + numberOfHome + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
