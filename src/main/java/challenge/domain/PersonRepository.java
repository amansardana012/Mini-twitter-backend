package challenge.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly=true)
    public Person findPersonByUserNameAndPassword(String username , String password) {
		try {
         Person  people = (Person) jdbcTemplate.queryForObject(
            "select * from people where username=? and password=?",
            new Object[]{username, password},  new BeanPropertyRowMapper<Person>(Person.class));
         return people;
		}
		catch(Exception ex)
		{
			return null;
		}
    }
	@Transactional(readOnly=true)
	public Person findPersonById(int userId)
	{
		try{
			Person  people = (Person) jdbcTemplate.queryForObject(
		            "select * from people where id=?",
		            new Object[]{userId}, new BeanPropertyRowMapper<Person>(Person.class));
		         return people;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	
}
