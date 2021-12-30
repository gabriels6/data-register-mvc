package com.gomes.dataregister.admin.model;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "Logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private String date;

    public int getId() {
        return Id;
    }

    public void setId(int Id) { this.Id = Id; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
