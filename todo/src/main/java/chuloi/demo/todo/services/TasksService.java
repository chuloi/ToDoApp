package chuloi.demo.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import chuloi.demo.todo.dtos.TaskRequestDTO;
import chuloi.demo.todo.exception.TaskNotFoundException;
import chuloi.demo.todo.model.Tasks;
import chuloi.demo.todo.repositories.TaskRepositories;

import java.util.List;
import java.util.Optional;


@Service
public class TasksService {

	@Autowired
	private TaskRepositories taskRepositories;
	
	public Page<Tasks> getAllTasks(Pageable pageable) {
	    return taskRepositories.findAll(pageable);
	}

	
	public Tasks addTask(TaskRequestDTO task) {
		Tasks newTask = new Tasks();
		newTask.setTitle(task.getTitle());
		newTask.setDescription(task.getDescription());
		newTask.setStatus(task.getStatus());
		newTask.setPriority(task.getPriority());
		return taskRepositories.save(newTask);
	}
	
	
	public Tasks updateTask(int id, Tasks task) {
	    Optional<Tasks> optionalTask = taskRepositories.findById(id);
	    if (optionalTask.isPresent()) {
	        Tasks existTask = optionalTask.get();
	        existTask.setTitle(task.getTitle());
	        existTask.setDescription(task.getDescription());
	        existTask.setPriority(task.getPriority());
	        existTask.setStatus(task.getStatus());
	        return taskRepositories.save(existTask);
	    }
		return null; 
	}

	public boolean deletetask(int id) {
	    Optional<Tasks> optionalTask = taskRepositories.findById(id);
	    if (optionalTask.isPresent()) {
	         taskRepositories.deleteById(id);
	    }
		return true;
	}
	
	public List<Tasks> getAllTasksSortedByTitle() {
        return taskRepositories.findAllByOrderByTitleAsc();
    }
	
	public List<Tasks> getAllTasksSortedByPriority() {
        return taskRepositories.findAllByOrderByPriorityAsc();
    }
	
	 public List<Tasks> searchTasksByTitle(String title) {
	        return taskRepositories.findByTitleContainingIgnoreCase(title);
	    }
	 public Tasks markTaskAsComplete(int taskId) {
	        Optional<Tasks> optionalTask = taskRepositories.findById(taskId);
	        if (optionalTask.isPresent()) {
	            Tasks task = optionalTask.get();
	            task.setCompleted(true);
	            return taskRepositories.save(task);
	        }
			return null; 
	    }
}
