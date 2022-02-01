package com.gomes.dataregister.gitcontrol.model;

import com.gomes.dataregister.core.model.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public",name = "branches")
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @OneToOne
    @JoinColumn(name = "repository_id", referencedColumnName = "id", nullable = false)
    private ProjectRepo repository;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
       this.Id = id;
    }

    public ProjectRepo getRepository() {
        return repository;
    }

    public void setRepository(ProjectRepo repository) {
        this.repository = repository;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
