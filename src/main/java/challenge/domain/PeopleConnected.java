package challenge.domain;

import java.util.List;

public class PeopleConnected {

	private List<Person> followers;
	
	private List<Person> following;

	public List<Person> getFollowers() {
		return followers;
	}

	public List<Person> getFollowing() {
		return following;
	}

	public void setFollowers(List<Person> followers) {
		this.followers = followers;
	}

	public void setFollowing(List<Person> following) {
		this.following = following;
	}
	
}
