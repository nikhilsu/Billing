package com.billing.model;

import com.billing.helper.Masker;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "bill_bill_category",
            joinColumns = {@JoinColumn(name = "bill_id")},
            inverseJoinColumns = {@JoinColumn(name = "bill_category_id")}
    )
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


    public double totalCost() {
        double total = 0.0;
        for (BillCategory category : billCategories) {
            total += category.getCost();
        }
        return total;
    }

    public String getCreatedOnDateString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(createdOn);
    }

    public String getMaskedId() {
        return Masker.maskDbId(id);
    }
}
