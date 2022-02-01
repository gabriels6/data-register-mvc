package com.gomes.dataregister.finance.model;

import com.gomes.dataregister.core.model.User;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "limit_value", nullable = false)
    private Double limit;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private AccountingType accountingType;

    @OneToOne
    @JoinColumn(name = "period_id", referencedColumnName = "id", nullable = false)
    private Period period;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
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
