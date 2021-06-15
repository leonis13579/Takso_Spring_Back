package ru.realityfamily.takso.Models;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "FirstName", nullable = false)
    @JsonProperty("name")
    private String name = "";
    @Column(name = "LastName", nullable = false)
    @JsonProperty("lastName")
    private String lastName = "";
    @Column(name = "Phone", nullable = false)
    @JsonProperty("phone")
    private String phone = "";
    @Column(name = "Email", nullable = false)
    @JsonProperty("email")
    private String email = "";

    @Column(name = "HomeAddress")
    @JsonProperty("homeAddress")
    private String homeAddress;
    @Column(name = "WorkAddress")
    @JsonProperty("workAddress")
    private String workAddress;

    @ElementCollection
    @Column(name="FavoriteAddresses")
    @JsonProperty("favoriteAddresses")
    private List<String> favoriteAddresses = new ArrayList<>();

    @JsonProperty("login")
    @OneToOne
    private AuthData authData;

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    @JsonIgnore
    private List<Orders> orders;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public List<String> getFavoriteAddresses() {
        return favoriteAddresses;
    }

    public void setFavoriteAddresses(List<String> favoriteAddresses) {
        this.favoriteAddresses = favoriteAddresses;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public AuthData getAuthData() {
        return authData;
    }

    public void setAuthData(AuthData authData) {
        this.authData = authData;
    }
}
