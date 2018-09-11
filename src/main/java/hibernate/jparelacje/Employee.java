package hibernate.jparelacje;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int salary;
    private int bonus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;



    public Employee() {
    }

    public Employee(String name, int salary, int bonus, Address address) {
        this.name = name;
        this.salary = salary;
        this.bonus = bonus;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", address=" + address +
                '}';
    }
}
