package example.repository;

import org.springframework.data.repository.CrudRepository;

import example.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findById(int id);

}
