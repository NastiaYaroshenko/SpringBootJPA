package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import example.model.User;
import example.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	//http://localhost:8080/create?name=<name>&age=<age>
		@GetMapping("/create")
		@ResponseBody
		public String createUser(@RequestParam String name, @RequestParam int age) {

			final User user = new User();
			user.setName(name);
			user.setAge(age);
			userRepository.save(user);

			return "User Created Successfully!";
		}

		//http://localhost:8080/read
		@GetMapping("/read")
		@ResponseBody
		public Iterable<User> readAllUsers() {

			return userRepository.findAll();
		}

		//http://localhost:8080/update?id=<existingID>&name=<newName>&age=<newAge>
		@GetMapping("/update")
		@ResponseBody
		public String updateUser(@RequestParam int id, @RequestParam String name,
				@RequestParam int age) {

			final User user = userRepository.findById(id);
			user.setName(name);
			user.setAge(age);
			userRepository.save(user);

			return "User Updated Successfully!";
		}

		//http://localhost:8080/delete?id=<existingID>
		@GetMapping("/delete")
		@ResponseBody
		public String deleteUser(@RequestParam int id) {

			userRepository.deleteById(id);

			return "User Deleted Successfully!";
		}

		//http://localhost:8080/findById?id=<existingId>
		@GetMapping("/findById")
		@ResponseBody
		public User findById(@RequestParam int id) {

			final User user = userRepository.findById(id);
			return user;
		}

}
