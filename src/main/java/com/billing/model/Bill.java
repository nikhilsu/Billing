package com.billing.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToMany(mappedBy = "bills")
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<BillCategory> billCategories;

    @Column(name = "created_on")
    @NotNull
    private Timestamp createdOn;


    public int getId() {
        return id;
    }

    public Bill setId(int id) {
        this.id = id;
        return this;
    }

    public Patient getPatient() {
        return patient;
    }

    public Bill setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public List<BillCategory> getBillCategories() {
        return billCategories;
    }

    public Bill setBillCategories(List<BillCategory> billCategories) {
        this.billCategories = billCategories;
        return this;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public Bill setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}
