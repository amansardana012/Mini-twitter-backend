package challenge.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FollowerRowMapper implements RowMapper<Follower> {

	@Override
	public Follower mapRow(ResultSet rs, int rowNum) throws SQLException {
		Follower follower = new Follower();
		follower.setPersonId(rs.getInt("person_id"));
		follower.setFollowerPersonId(rs.getInt("follower_person_id"));
		// TODO Auto-generated method stub
		return follower;
	}

	
}
