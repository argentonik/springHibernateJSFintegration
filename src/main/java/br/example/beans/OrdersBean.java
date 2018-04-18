package br.example.beans;

import br.example.db.OrderDAO;
import br.example.db.OrderPositionDAO;
import br.example.helpers.OrderWithPosition;
import br.example.model.OrderFull;
import br.example.model.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by hp_laptop on 18.04.18.
 */

@ManagedBean(name="orders")
@ViewScoped
public class OrdersBean extends SpringBeanAutowiringSupport {
    @Autowired
    private OrderDAO orderService;
    @Autowired
    private OrderPositionDAO orderPositionService;
    @Autowired
    private List<OrderWithPosition> orderWithPosition;

    String test = "run";

    @PostConstruct
    public void init() {
        System.out.println("**********************************************************************");
        System.out.println("Start/////////////////////");
        List<OrderPosition> orderPosition = orderPositionService.findAll();
        for(int i = 0; i < orderPosition.size(); i++) {
            OrderWithPosition fullOrder = new OrderWithPosition();
            fullOrder.setOrderPosition(orderPosition.get(i));
            fullOrder.setOrderFull(orderPosition.get(i).getOrderFull());
            orderWithPosition.add(fullOrder);
        }
    }

    public List<OrderWithPosition> getOrderWithPosition() {
        return orderWithPosition;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
