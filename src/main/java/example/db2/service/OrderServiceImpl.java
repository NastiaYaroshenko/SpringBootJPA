package example.db2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.db2.model.Orders;
import example.db2.repository.OrderRepository;

@Service
@Transactional("orderTransactionManager")
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository repository;
	
	@Override
	public void create(Orders order) {
		repository.save(order);
	}

	@Override
	public List<Orders> findOrders() {
		return (List<Orders>) repository.findAll();
	}

	@Override
	public Orders findById(Integer id) {
		Optional<Orders> order = repository.findById(id);
		return order.get();
	}

	@Override
	public void update(Orders order, Integer id) {
		repository.save(order);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
