package example.controller;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import example.db1.controller.UserController;
import example.db1.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	@Autowired
    private UserController controller;
	@Autowired
	private UserService service;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(service).isNotNull();
    }	
}
