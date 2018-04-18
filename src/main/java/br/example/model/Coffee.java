/**
 *
 */
package br.example.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String type;

    @Column(nullable=false)
    private Double price;

    @Column(nullable = false)
    private boolean disabled;

    public Coffee() { }

    public Coffee(String type, Double price, boolean disabled) {
        this.type = type;
        this.price = price;
        this.disabled = disabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisable(boolean disabled) {
        this.disabled = disabled;
    }
}