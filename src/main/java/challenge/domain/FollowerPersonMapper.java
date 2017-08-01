package challenge.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FollowerPersonMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("follower_person_id"));
		person.setName(rs.getString("name"));
		person.setHandle(rs.getString("handle"));
		// TODO Auto-generated method stub
		return person;
		
	}

}
