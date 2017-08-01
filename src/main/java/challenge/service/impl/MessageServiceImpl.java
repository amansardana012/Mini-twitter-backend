package challenge.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.domain.Follower;
import challenge.domain.FollowersRepository;
import challenge.domain.Message;
import challenge.domain.MessageRepository;
import challenge.domain.Person;
import challenge.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private FollowersRepository followerRepo;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Override
	public List<Message> getUserMessages(Person person, String searchString) {
		List<Integer> personsList = new ArrayList<Integer>();
		
		List<Follower> personsFollowing=followerRepo.findPeopleFollowingByPerson(person.getId());
		personsList.add(person.getId());
		for(Follower following : personsFollowing)
		{
			personsList.add(following.getPersonId());
		}
		List<Message> messages= messageRepo.findMessagesByPersonList(personsList, searchString);
		
		return messages;
	}

}
