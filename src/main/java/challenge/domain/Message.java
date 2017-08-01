package challenge.domain;

public class Message {

	private int id;
	
	private int personId;
	
	private String content;
	
	private String personName;
	
	private String personHandle;

	
	public int getId() {
		return id;
	}

	public int getPersonId() {
		return personId;
	}

	public String getContent() {
		return content;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getPersonName() {
		return personName;
	}

	public String getPersonHandle() {
		return personHandle;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setPersonHandle(String personHandle) {
		this.personHandle = personHandle;
	}

}
