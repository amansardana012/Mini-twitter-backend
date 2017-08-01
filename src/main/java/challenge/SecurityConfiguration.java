package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private PersonAuthenticationProvider authProvider;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authProvider);
        
    }
	
	@Override
 	public void configure(WebSecurity web) throws Exception {
 		web.ignoring()
 		// Spring Security should completely ignore URLs starting with /resources/
 				.antMatchers("/resources/**");
 	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
         .antMatchers("/h2-console/*").permitAll()
         .anyRequest().authenticated();
    	 http.authorizeRequests().antMatchers("/twitter/**").fullyAuthenticated();
    	 http.httpBasic();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
    }
}