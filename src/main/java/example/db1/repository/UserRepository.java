package example.db1.repository;

import org.springframework.data.repository.CrudRepository;

import example.db1.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
