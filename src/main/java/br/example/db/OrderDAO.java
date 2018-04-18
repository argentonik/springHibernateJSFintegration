package br.example.db;

import br.example.model.OrderFull;

import java.util.List;

public interface OrderDAO {
    public OrderFull create(OrderFull order);
    public OrderFull findById(Long id);
    public List<OrderFull> findAll();
}
