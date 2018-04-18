/**
 *
 */
package br.example.model;

import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderPosition {

    @Column(nullable=false)
    private Integer numOfCups;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="idtype")
    private Coffee coffee;

    @ManyToOne
    @JoinColumn(name="idorderfull")
    private OrderFull orderFull;

    public OrderPosition() { }

    public OrderPosition(Integer numOfCaps, Coffee coffee, OrderFull orderFull) {
        this.id = null;
        this.numOfCups = numOfCaps;
        this.coffee = coffee;
        this.orderFull = orderFull;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumOfCups() {
        return numOfCups;
    }

    public void setNumOfCups(Integer numOfCups) {
        this.numOfCups = numOfCups;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
}