/**
 *
 */
package br.example.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class OrderFull {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    private String nameClient;

    @Column(nullable=false)
    private String address;

    @Column
    private Double price;

    @OneToMany(mappedBy = "orderFull")
    private Set<OrderPosition> orderPositions = new HashSet<OrderPosition>();

    public OrderFull() {}

    public OrderFull(Date date, String nameClient, String address, Double price) {
        this.date = date;
        this.nameClient = nameClient;
        this.address = address;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
}