package example.db2.service;

import java.util.List;

import example.db2.model.Orders;

public interface OrderService {
	public void create(Orders order);
    public List<Orders> findOrders();
    public Orders findById(Integer id);
    public void update(Orders order, Integer id);
    public void delete(Integer id);
}
