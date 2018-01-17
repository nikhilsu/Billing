package com.billing.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "phone_number")
    @Size(min = 10, max = 12)
    private int phone;

    @Email
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "patient")
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Bill> bills;

    public List<Bill> getBills() {
        return bills;
    }

    public Patient setBills(List<Bill> bills) {
        this.bills = bills;
        return this;
    }

    public int getId() {
        return id;
    }

    public Patient setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Patient setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Patient setAge(int age) {
        this.age = age;
        return this;
    }

    public int getPhone() {
        return phone;
    }

    public Patient setPhone(int phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Patient setEmail(String email) {
        this.email = email;
        return this;
    }
}
