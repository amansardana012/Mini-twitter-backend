package challenge.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import challenge.domain.Message;
import challenge.domain.Person;

@Service
public interface MessageService {

	public List<Message> getUserMessages(Person people, String searchString);
}
