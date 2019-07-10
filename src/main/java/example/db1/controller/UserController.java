package example.db1.controller;

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

import example.db1.model.User;
import example.db1.service.UserServiceImpl;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	UserServiceImpl service;
	
	@GetMapping(value = "/{id}")
    public User getById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

	@PostMapping(value="/create")
    public void create(@RequestBody User user){
        service.create(user);
    }
	
	@GetMapping(value="/get")
    public List<User> getAllUser() {
        return service.findUsers();
    }
	
	@PutMapping(value="/update")
    public void update(@RequestBody User currentUser){
        service.update(currentUser, currentUser.getId());
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }
}
