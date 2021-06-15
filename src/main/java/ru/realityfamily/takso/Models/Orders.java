package ru.realityfamily.takso.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "Takso_Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Clients_id")
    private Clients client;

    @ManyToOne
    @JoinColumn(name = "Drivers_id")
    private Drivers driver;

    @Column(name = "AddressFrom", nullable = false)
    private String addressFrom;
    @Column(name = "PointFrom", nullable = false)
    private String pointFrom;
    @Column(name = "AddressTo", nullable = false)
    private String addressTo;
    @Column(name = "PointTo", nullable = false)
    private String pointTo;
    @Column(name = "OrderCreateTime", nullable = false)
    private String createTime;
    @Column(name = "Comment")
    private String comment;
    @Column(name = "Price", nullable = false)
    private double price;

    public enum Statuses {
        Created, Waiting, Performing, Finished, Canceled_by_Client, Canceled_by_Driver
    }
    @Column(name = "Order_Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Statuses OrderStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Drivers getDriver() {
        return driver;
    }

    public void setDriver(Drivers driver) {
        this.driver = driver;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getPrice() {
        return price;
    }

    public String getPointFrom() {
        return pointFrom;
    }

    public void setPointFrom(String pointFrom) {
        this.pointFrom = pointFrom;
    }

    public String getPointTo() {
        return pointTo;
    }

    public void setPointTo(String pointTo) {
        this.pointTo = pointTo;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Statuses getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(Statuses orderStatus) {
        OrderStatus = orderStatus;
    }
}
