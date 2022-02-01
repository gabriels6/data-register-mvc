package com.gomes.dataregister.finance.model;

import com.gomes.dataregister.core.utils.DateUtils;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "periods")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "year", nullable = false)
    private int year;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthSymbol() {
        try {
            return new DateUtils().getMonthByNumber(month);
        } catch (Exception ex) {
            return ex.getMessage();
        }

    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
