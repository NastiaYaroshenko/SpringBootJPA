package example.db3.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import example.db3.model.Equipment;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository{

	@Autowired
    JdbcTemplate template;
	
	@Override
	public void save (Equipment equipment) {
		template.update("INSERT INTO equipment (id, name, price) VALUES (?, ?, ?)",
				equipment.getId(), equipment.getName(), equipment.getPrice());
	}

	@Override
	public Equipment getById(Integer id) {
		Equipment equipment = template.queryForObject("SELECT * FROM equipment where id = ? ",
	            new Object[] {id}, new BeanPropertyRowMapper<Equipment>(Equipment.class));
	        return equipment;
	}

	@Override
	public void update(Equipment equipment, Integer id) {
		template.update	("UPDATE equipment SET name = ? , price = ? WHERE id = ? ",
		equipment.getName(), equipment.getPrice(), id);
	}

	@Override
	public void delete(Integer id) {
		template.update("DELETE from equipment WHERE id = ? ", id);
	}

	@Override
	public List<Equipment> getAll() {
		List <Equipment> equipment = template.query("SELECT * FROM equipment", 
				new BeanPropertyRowMapper<Equipment>(Equipment.class));
        return equipment;
	}
	
}
