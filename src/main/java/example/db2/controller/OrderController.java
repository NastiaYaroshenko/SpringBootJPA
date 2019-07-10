package example.db2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.db2.model.Orders;
import example.db2.service.OrderServiceImpl;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl service;
	
	@GetMapping(value = "/{id}")
    public Orders getById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

	@PostMapping(value="/create")
    public void create(@RequestBody Orders order){
        service.create(order);
    }
	
	@GetMapping(value="/get")
    public List<Orders> getAllOrder() {
        return service.findOrders();
    }
	
	@PutMapping(value="/update")
    public void update(@RequestBody Orders currentOrder){
        service.update(currentOrder, currentOrder.getId());
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }
}
