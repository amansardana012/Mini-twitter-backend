package challenge;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import challenge.domain.Person;
import challenge.domain.PersonRepository;
import challenge.service.PersonService;

@Component
public class PersonAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	PersonService service;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		  String username = String.valueOf(auth.getPrincipal());
		  String password = String.valueOf(auth.getCredentials());
        Person people= service.getUser(username, password);
        if(people == null)
        {
        	throw new BadCredentialsException("Bad Credentials");
        }
        	List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new  UsernamePasswordAuthenticationToken(people, null, grantedAuths);
          
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
