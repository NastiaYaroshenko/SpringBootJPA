package example.db3.repository;

import java.util.List;
import example.db3.model.Equipment;

public interface EquipmentRepository {
	//Create
	public void save(Equipment equipment);
	//Read
	public Equipment getById(Integer id);
	//Update
	public void update(Equipment equipment, Integer id);
	//Delete
	public void delete(Integer id);
	//Get All
	public List<Equipment> getAll();
}
