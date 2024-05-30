package chuloi.demo.todo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
	SUCCESS("success", "success"), 
	USER_ALREADY_EXISTS("user.exists", "user already exists"),
	VALIDATION_EXCEPTION("validation", null),
	TASK_NOT_FOUND("task.notfound", "tasks not found");
	private String code;
	private String message;

	private ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
