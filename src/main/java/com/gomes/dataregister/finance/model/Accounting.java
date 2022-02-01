package com.gomes.dataregister.finance.model;

import com.gomes.dataregister.core.model.User;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "accountings")
public class Accounting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "date", nullable = false, length = 16)
    private String date;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private AccountingType accountingType;

    @OneToOne
    @JoinColumn(name = "period_id", referencedColumnName = "id", nullable = false)
    private Period period;

    @OneToMany
    @JoinColumn(name = "accounting_id", referencedColumnName = "id", updatable = false, insertable = false)
    @OrderColumn(name = "id")
    private AccountingDetail[] accountingDetails;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountingType getAccountingType() {
        return accountingType;
    }

    public AccountingDetail[] getAccountingDetails() {
        return accountingDetails;
    }

    public void setAccountingDetails(AccountingDetail[] accountingDetails) {
        this.accountingDetails = accountingDetails;
    }

    public void setAccountingType(AccountingType accountingType) {
        this.accountingType = accountingType;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
