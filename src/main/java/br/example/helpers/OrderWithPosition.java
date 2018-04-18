package br.example.helpers;

import br.example.model.OrderFull;
import br.example.model.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Created by hp_laptop on 18.04.18.
 */

public class OrderWithPosition {
    @Autowired
    private OrderFull orderFull;
    @Autowired
    private OrderPosition orderPosition;

    public void setOrderFull(OrderFull orderFull) {
        this.orderFull = orderFull;
    }

    public void setOrderPosition(OrderPosition orderPosition) {
        this.orderPosition = orderPosition;
    }

    public OrderFull getOrderFull() {
        return orderFull;
    }

    public OrderPosition getOrderPosition() {
        return orderPosition;
    }
}
