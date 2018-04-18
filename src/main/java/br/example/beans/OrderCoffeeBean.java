/**
 *
 */
package br.example.beans;

import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.example.model.Coffee;
import br.example.model.OrderPosition;
import br.example.model.OrderFull;
import br.example.db.CoffeeDAO;
import br.example.db.OrderPositionDAO;
import br.example.db.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;



@ManagedBean
@SessionScoped
public class OrderCoffeeBean extends SpringBeanAutowiringSupport {
    @Value("${priceLogic.freeCup}") Integer propertyFreeCup;
    @Value("${priceLogic.freeDelivery}") Double propertyFreeDelivery;
    @Value("${priceLogic.costDelivery}") Double propertyCostDelivery;

    @Autowired
    private CoffeeDAO coffeeService;
    @Autowired
    private OrderPositionDAO orderPositionService;
    @Autowired
    private OrderDAO orderService;
    // map<idtype, cups>
    private Map<Long, Integer> selectedItems = new HashMap<Long, Integer>();
    // map<idorder>
    private ArrayList<OrderPosition> ordersCoffee = new ArrayList<OrderPosition>();
    // map<coffee, cups>
    private Map<Coffee, Integer> orderedCoffee = new HashMap<Coffee, Integer>();
    private OrderFull order;


    private Integer enteredCups;
    private Double totalPrice;
    private Double totalPriceWithDelivery;
    private String nameClient;
    private String address;


    public Map<Coffee, Integer> getOrderedCoffee() {
        return orderedCoffee;
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getAddress() {
        return address;
    }

    public Integer getEnteredCups() {
        return enteredCups;
    }

    public void setEnteredCups(Integer enteredCups) {
        this.enteredCups = enteredCups;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double getCostDelivery() { return propertyCostDelivery; }

    public Double getTotalPriceWithDelivery() { return totalPriceWithDelivery; }



    public void addCups(Long id, Integer cups) {
        if(cups > 0) {
            selectedItems.put(id, cups);
        } else if (cups == 0) {
            selectedItems.remove(id);
        }
    }

    public String submitOrder() {

        //write to order bd
        OrderFull orderFull = new OrderFull(new Date(), nameClient, address, totalPrice);
        orderFull = orderService.create(orderFull);

        //write to orderposition bd
        for (Map.Entry<Coffee, Integer> order : orderedCoffee.entrySet())
        {
            OrderPosition orderPosition = new OrderPosition(order.getValue(), order.getKey(), orderFull);
            ordersCoffee.add(orderPositionService.create(orderPosition));
        }

        nameClient = "";
        address = "";
        ordersCoffee.clear();
        orderedCoffee.clear();
        return "/confirm.xhtml?faces-redirect=true&";
    }

    public String createPositionOrder() {
        totalPrice = 0.0;
        totalPriceWithDelivery = 0.0;
        orderedCoffee.clear();
        Coffee coffee;

        if(selectedItems.isEmpty())
            return "";

        for (Map.Entry<Long, Integer> order : selectedItems.entrySet())
        {
            coffee = coffeeService.findById(order.getKey());
            orderedCoffee.put(coffee, order.getValue());

            Double discount = order.getValue() / propertyFreeCup * coffee.getPrice();
            if (order.getValue() >= propertyFreeCup) {
                totalPrice += (coffee.getPrice() * order.getValue()) - discount;
            } else {
                totalPrice += coffee.getPrice() * order.getValue();
            }

        }

        if(totalPrice <= propertyFreeDelivery) {
            totalPriceWithDelivery = totalPrice + propertyCostDelivery;
        } else {
            totalPriceWithDelivery = totalPrice;
        }

        selectedItems.clear();
        enteredCups = null;
        return "/new.xhtml?faces-redirect=true&";
    }

}
