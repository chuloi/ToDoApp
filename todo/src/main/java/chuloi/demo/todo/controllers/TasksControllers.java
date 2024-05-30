package chuloi.demo.todo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import chuloi.demo.todo.dtos.TaskRequestDTO;
import chuloi.demo.todo.exception.TaskNotFoundException;
import chuloi.demo.todo.model.Tasks;
import chuloi.demo.todo.services.TasksService;
import chuloi.demo.todo.utils.ResponseCode;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController

@Validated

public class TasksControllers {
	@Autowired 
	private  TasksService tasksService;
	
	
	@GetMapping("/v1/tasks")
	public ResponseEntity<?> getAllTasks(Pageable pageable) {
	    try {
	        Page<Tasks> tasksPage = tasksService.getAllTasks(pageable);
	        if (tasksPage != null && !tasksPage.isEmpty()) {
	            return BaseResponseController.success(tasksPage.getContent());
	        } else {
	            throw new TaskNotFoundException();
	        }
	    } catch (TaskNotFoundException e) {
	        return BaseResponseController.fail(ResponseCode.TASK_NOT_FOUND.getCode(), ResponseCode.TASK_NOT_FOUND.getMessage());
	    }
	}

	
	@PostMapping("/v1/task")
	public ResponseEntity<?> addTask(@Valid @RequestBody TaskRequestDTO newTask ) {
		Tasks task = tasksService.addTask(newTask);
		return BaseResponseController.success(task);
	}
	
	@PutMapping("v1/task/{id}")
	public ResponseEntity<?> updateTask(@Valid @PathVariable int id, @RequestBody Tasks task){
		Tasks newtask = tasksService.updateTask(id, task);
		return BaseResponseController.success(newtask);
	}
	
	@DeleteMapping("v1/task/{id}")
	public ResponseEntity<?> deletetask(@PathVariable int id){
		return BaseResponseController.success(tasksService.deletetask(id));
	}
	
	@GetMapping("v1/tasks/sortedByTitle")
    public List<Tasks> getAllTasksSortedByTitle() {
        return tasksService.getAllTasksSortedByTitle();
    }
	
	@GetMapping("v1/tasks/sortedByPriority")
    public List<Tasks> getAllTasksSortedByPriority() {
        return tasksService.getAllTasksSortedByPriority();
    }
	
	@GetMapping("v1/tasks/searchByTitle")
    public List<Tasks> searchTasksByTitle(@RequestParam String title) {
        return tasksService.searchTasksByTitle(title);
    }
	
	
	 @PutMapping("v1/task/{id}/complete")
	    public ResponseEntity<?> markTaskAsComplete(@PathVariable int id) {
	            Tasks completedTask = tasksService.markTaskAsComplete(id);
	            return BaseResponseController.success(completedTask);
	    }

}
