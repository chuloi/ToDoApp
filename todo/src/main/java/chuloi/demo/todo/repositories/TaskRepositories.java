package chuloi.demo.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chuloi.demo.todo.model.Tasks;

@Repository
public interface TaskRepositories extends JpaRepository<Tasks, Integer>{
	
	List<Tasks> findAllByOrderByTitleAsc();
	List<Tasks> findAllByOrderByPriorityAsc();
	List<Tasks> findByTitleContainingIgnoreCase(String title);
}
