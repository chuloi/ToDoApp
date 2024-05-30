package chuloi.demo.todo.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TaskRequestDTO {
	@NotBlank(message="title must be not blank")
	private String title;
	@NotBlank(message="description must be not blank")
	private String description;
	@NotBlank(message="status must be not blank")
	private String status;
	@Min(value = 1, message = "priority must be greater than 0")
	private Integer priority;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
