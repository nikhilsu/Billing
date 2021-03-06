package com.billing.model;

import com.billing.helper.hibernate.PostgresEnumType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bill_category")
@TypeDef(name = "pg_sql_enum", typeClass = PostgresEnumType.class)
public class BillCategory {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private double cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", columnDefinition = "bill_category_type")
    @Type(type = "pg_sql_enum")
    private CategoryType categoryType;

    @ManyToMany(mappedBy = "billCategories")
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Bill> bills;

    public int getId() {
        return id;
    }

    public BillCategory setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BillCategory setName(String name) {
        this.name = name;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public BillCategory setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public BillCategory setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
        return this;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public BillCategory setBills(List<Bill> bills) {
        this.bills = bills;
        return this;
    }
}
