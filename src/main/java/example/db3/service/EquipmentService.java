package example.db3.service;

import java.util.List;

import example.db3.model.Equipment;

public interface EquipmentService {

	public void create(Equipment equipment);
	public Equipment getById(Integer id);
	public void update(Equipment equipment, Integer id);
	public void delete(Integer id);
	public List<Equipment> getAll();
}
