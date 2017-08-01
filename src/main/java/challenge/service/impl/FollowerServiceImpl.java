package challenge.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.domain.Follower;
import challenge.domain.FollowersRepository;
import challenge.domain.PeopleConnected;
import challenge.domain.Person;
import challenge.domain.PopularFollower;
import challenge.service.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService {

	@Autowired
	FollowersRepository followerRepo;
	@Override
	public boolean followPerson(int followingPersonId, int followerId) {
		List<Follower> alreadyFollowing= followerRepo.findPeopleFollowingByPerson(followerId);
		HashSet<Integer> set = new HashSet<Integer>();
		for(Follower follow : alreadyFollowing)
		{
			set.add(follow.getFollowerPersonId());
		}
		if(!set.contains(followingPersonId))
		{
		return followerRepo.followPerson(followingPersonId, followerId);
		}
		return true;
		
	}

	@Override
	public boolean unFollowPerson(int unFollowingPersonId, int followerId) {
		return followerRepo.unFollowPerson(unFollowingPersonId, followerId);
	}

	@Override
	public PeopleConnected getPeopleConnected(Person person) {
		PeopleConnected connected = new PeopleConnected();
		List<Person> followers = followerRepo.findFollowersByPerson(person);
		connected.setFollowers(followers);
		List<Person> followingList = followerRepo.findPeopleFollowingByPerson(person);
		connected.setFollowing(followingList);
		return connected;
	}

	@Override
	public List<PopularFollower> getPopularFollowers() {
		return followerRepo.popularAllFollower();
		
	}

}
