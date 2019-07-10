package example.db1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.db1.model.User;
import example.db1.repository.UserRepository;

@Service
@Transactional("userTransactionManager")
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;

	@Override
	public void create(User user) {
		repository.save(user);
	}

	@Override
	public List<User> findUsers() {
		return (List<User>) repository.findAll();
	}

	@Override
	public User findById(Integer id) {
		Optional<User> user = repository.findById(id);
		return user.get();
	}

	@Override
	public void update(User user, Integer id) {
		repository.save(user);
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	
}
