package com.gomes.dataregister.gitcontrol.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(schema = "public", name = "status")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
