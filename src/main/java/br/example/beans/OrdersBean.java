package br.example.beans;

import br.example.db.OrderPositionDAO;
import br.example.model.OrderPosition;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.Map;

/**
 * Created by hp_laptop on 18.04.18.
 */

@ManagedBean(name="orders")
@ViewScoped
public class OrdersBean extends SpringBeanAutowiringSupport {
    @Autowired
    private OrderPositionDAO orderPositionService;


    LazyDataModel<OrderPosition> orderPositions = new LazyDataModel<OrderPosition>() {
        @Override
        public List<OrderPosition> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
            return orderPositionService.getAllByPage(pageSize, first);
        }

        @Override
        public int getRowCount() {
            return orderPositionService.getNumberOfOrders();
        }
    };

    public LazyDataModel<OrderPosition> getOrderPositions() {
        return orderPositions;
    }
}
