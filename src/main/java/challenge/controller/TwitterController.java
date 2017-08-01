package challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import challenge.domain.Message;
import challenge.domain.PeopleConnected;
import challenge.domain.Person;
import challenge.domain.PopularFollower;
import challenge.domain.Response;
import challenge.service.FollowerService;
import challenge.service.MessageService;

@RestController
@RequestMapping("/twitter") 
public class TwitterController {
	
	private final String successResp = "CODE_SUCCESS";
	private final String failureResp = "CODE_FAILURE";
	@Autowired
	MessageService messageService;
	
	@Autowired
	FollowerService followerService;
	
	 @RequestMapping(value = "/messages", method = RequestMethod.GET)
	    public ResponseEntity<List<Message>> listAllMessages(@RequestParam(value="search", required=false, defaultValue="") final String searchString) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        Person person = (Person) auth.getPrincipal();
	        List<Message> messages = messageService.getUserMessages(person, searchString);
	        if (messages.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/follow/{followingId}", method = RequestMethod.POST)
	    public ResponseEntity<Response> followPerson(@PathVariable("followingId") int followingPersonId) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        Person person = (Person) auth.getPrincipal();
	        if(person== null)
	        {
	        	 return new ResponseEntity<Response>(HttpStatus.FORBIDDEN);
	        }
	        Response resp = new Response();
	        if(followerService.followPerson(followingPersonId, person.getId()))
	        {
	        	resp.setCode(successResp);
	        	return new ResponseEntity<Response>(resp,HttpStatus.OK);
	        }
	        else
	        {
	        	resp.setCode(failureResp);
	        	return new ResponseEntity<Response>(resp,HttpStatus.BAD_REQUEST);
	        }
	 }
	 
	 @RequestMapping(value = "/unfollow/{unfollowingId}", method = RequestMethod.POST)
	    public ResponseEntity<Response> unFollowPerson(@PathVariable("unfollowingId") int unfollowingPersonId) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        Person person = (Person) auth.getPrincipal();
	        if(person== null)
	        {
	        	 return new ResponseEntity<Response>(HttpStatus.FORBIDDEN);
	        }
	        Response resp = new Response();
	        if(followerService.unFollowPerson(unfollowingPersonId, person.getId()))
	        {
	        	resp.setCode(successResp);
	        	return new ResponseEntity<Response>(resp,HttpStatus.OK);
	        }
	        else
	        {
	        	resp.setCode(failureResp);
	        	return new ResponseEntity<Response>(resp,HttpStatus.BAD_REQUEST);
	        }
	 }
	 
	 @RequestMapping(value = "/connected", method = RequestMethod.GET)
	    public ResponseEntity<PeopleConnected> getPeopleConnected() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        Person person = (Person) auth.getPrincipal();
	        if(person== null)
	        {
	        	 return new ResponseEntity(HttpStatus.FORBIDDEN);
	        }
	         
	        PeopleConnected connected= followerService.getPeopleConnected(person);
	        return new ResponseEntity<PeopleConnected>(connected, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/popularFollower", method = RequestMethod.GET)
	    public ResponseEntity<List<PopularFollower>> getPopularFollowers() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        Person person = (Person) auth.getPrincipal();
	        if(person== null)
	        {
	        	 return new ResponseEntity(HttpStatus.FORBIDDEN);
	        }
	         
	        List<PopularFollower> followers= followerService.getPopularFollowers();
	        return new ResponseEntity<List<PopularFollower>>(followers, HttpStatus.OK);
	 }
}
