package com.gomes.dataregister.finance.model;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "accounting_details")
public class AccountingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private Double value;

    @OneToOne
    @JoinColumn(name = "accounting_id", referencedColumnName = "id")
    private Accounting accounting;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Accounting getAccounting() {
        return accounting;
    }

    public void setAccounting(Accounting accounting) {
        this.accounting = accounting;
    }
}
