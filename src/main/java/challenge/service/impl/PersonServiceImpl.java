package challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.domain.Person;
import challenge.domain.PersonRepository;
import challenge.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repo;
	@Override
	public Person getUser(String username, String password) {
		
		Person people = repo.findPersonByUserNameAndPassword(username, password);
		return people;
	}

}
