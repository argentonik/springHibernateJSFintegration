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
}
