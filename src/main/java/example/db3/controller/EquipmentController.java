package example.db3.controller;

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

import example.db3.model.Equipment;
import example.db3.service.EquipmentServiceImpl;

@RestController
@RequestMapping(path = "/equipment")
public class EquipmentController {
	
	@Autowired
	EquipmentServiceImpl service;
	
	@GetMapping(value = "/{id}")
    public Equipment getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }
	
	@PostMapping(value="/create")
    public void create(@RequestBody Equipment equipment){
        service.create(equipment);
    }
	
	@GetMapping(value="/get")
    public List<Equipment> getAllquipment() {
        return service.getAll();
    }
	
	@PutMapping(value="/update")
    public void update(@RequestBody Equipment currentEquipment){
        service.update(currentEquipment, currentEquipment.getId());
    }

    @DeleteMapping(value="/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }
}
