package ru.realityfamily.takso.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Lines")
public class Lines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "Drivers_id")
    private Drivers driver;

    @Column(name = "OnLineNow")
    private boolean OnLine;
    @Column(name = "StartOnLine")
    private String StartOnLine;
    @Column(name = "StopOnLine")
    private String EndOnLine;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Drivers getDriver() {
        return driver;
    }

    public void setDriver(Drivers driver) {
        this.driver = driver;
    }

    public boolean isOnLine() {
        return OnLine;
    }

    public void setOnLine(boolean onLine) {
        OnLine = onLine;
    }

    public String getStartOnLine() {
        return StartOnLine;
    }

    public void setStartOnLine(String startOnLine) {
        StartOnLine = startOnLine;
    }

    public String getEndOnLine() {
        return EndOnLine;
    }

    public void setEndOnLine(String endOnLine) {
        EndOnLine = endOnLine;
    }
}
