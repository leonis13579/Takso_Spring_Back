package ru.realityfamily.takso.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Cars")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "Name", nullable = false)
    private String CodeName;
    @Column(name = "FullName")
    private String Name;

    @OneToOne
    @JoinColumn(name = "Drivers_id")
    private Drivers driver;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCodeName() {
        return CodeName;
    }

    public void setCodeName(String codeName) {
        CodeName = codeName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Drivers getDriver() {
        return driver;
    }

    public void setDriver(Drivers driver) {
        this.driver = driver;
    }
}
