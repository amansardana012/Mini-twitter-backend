package challenge.service;

import org.springframework.stereotype.Component;

import challenge.domain.Person;

@Component
public interface PersonService {

	public Person getUser(String username, String password);
}
