package challenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import challenge.domain.PeopleConnected;
import challenge.domain.Person;
import challenge.domain.PopularFollower;

@Service
public interface FollowerService {

	public boolean followPerson(int followingPersonId, int followerId);
	
	public boolean unFollowPerson(int unFollowingPersonId, int followerId);
	
	public PeopleConnected getPeopleConnected(Person person);
	
	public List<PopularFollower> getPopularFollowers();
}
