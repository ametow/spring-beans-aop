package edu.miu.cs.cs544.najeeb.midterm.entity;

import jakarta.persistence.*;

@Entity
@Cacheable(value = false)
public class Faculty extends Person {
    private String department;

    public Faculty() {
    }

    public Faculty(String name, String department, Address address) {
        super(name);
        this.department = department;
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "department='" + department + '\'' +
                ", address=" + address +
                '}';
    }
}
