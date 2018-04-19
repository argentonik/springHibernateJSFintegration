/**
 *
 */
package br.example.db;

import java.util.List;

import br.example.model.OrderPosition;

public interface OrderPositionDAO {
    public OrderPosition create(OrderPosition orderPosition);
    public OrderPosition findById(Long id);
    public List<OrderPosition> findAll();

    public int getNumberOfOrders();
    public List<OrderPosition> getAllByPage(final int pageSize, final int pageNumber);
}
