package example.db2.repository;

import org.springframework.data.repository.CrudRepository;

import example.db2.model.Orders;

public interface OrderRepository extends CrudRepository<Orders, Integer>{

}
