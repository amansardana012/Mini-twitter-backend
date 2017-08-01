package challenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Person {

	private int id;
	
	private String handle;
	
	private String name;
	
	@JsonIgnore
	private String username;
	
	@JsonIgnore
	private  String password;

	public int getId() {
		return id;
	}

	public String getHandle() {
		return handle;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
