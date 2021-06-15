package ru.realityfamily.takso.Models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Drivers")
public class Drivers {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "CodeName", nullable = false)
    private String CodeName = "";

    @Column(name = "FirstName", nullable = false)
    private String Name = "";
    @Column(name = "LastName", nullable = false)
    private String LastName = "";
    @Column(name = "Phone", nullable = false)
    private String Phone ="";
    @Column(name = "Email", nullable = false)
    private String Email = "";

    @Column(name = "HomeAddress")
    private String HomeAddress;

    @OneToOne(mappedBy = "driver")
    private Cars SelectedCar;

    @OneToMany(mappedBy = "driver")
    private List<Lines> Lines = new ArrayList<>();
    
    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    private List<Orders> orders;

    @OneToOne
    private AuthData authData;


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

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        HomeAddress = homeAddress;
    }

    public Cars getSelectedCar() { return SelectedCar; }

    public void setSelectedCar(Cars selectedCar) { SelectedCar = selectedCar; }

    @JsonIgnore
    public List<ru.realityfamily.takso.Models.Lines> getLines() {
        return Lines;
    }

    public void setLines(List<ru.realityfamily.takso.Models.Lines> lines) {
        Lines = lines;
    }

    @JsonIgnore
    public List<ru.realityfamily.takso.Models.Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<ru.realityfamily.takso.Models.Orders> orders) {
        orders = orders;
    }

    public AuthData getAuthData() {
        return authData;
    }

    public void setAuthData(AuthData authData) {
        this.authData = authData;
    }
}
