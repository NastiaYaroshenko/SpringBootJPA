package example.db1.service;

import java.util.List;

import example.db1.model.User;

public interface UserService {

	public void create(User user);
    public List<User> findUsers();
    public User findById(Integer id);
    public void update(User user, Integer id);
    public void delete(Integer id);
}
