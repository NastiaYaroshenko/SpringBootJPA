package example.db3.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.db3.model.Equipment;
import example.db3.repository.EquipmentRepositoryImpl;

@Transactional("equipmentTransactionManager")
@Service
public class EquipmentServiceImpl implements EquipmentService{

	@Autowired
	EquipmentRepositoryImpl repository;
	
	@Override
	public void create(Equipment equipment) {
		repository.save(equipment);
	}

	@Override
	public Equipment getById(Integer id) {
		Equipment equipment = repository.getById(id);
		return equipment;
	}

	@Override
	public void update(Equipment equipment, Integer id) {
		repository.update(equipment, id);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public List<Equipment> getAll() {
		return (List<Equipment>) repository.getAll();
	}

}
