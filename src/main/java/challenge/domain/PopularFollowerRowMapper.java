package challenge.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PopularFollowerRowMapper implements RowMapper<PopularFollower> {

		@Override
		public PopularFollower mapRow(ResultSet rs, int rowNum) throws SQLException {
			PopularFollower person = new PopularFollower();
			person.setId(rs.getInt("id"));
			person.setPersonName(rs.getString("name"));
			person.setPopularFollowerName(rs.getString("popularfollower"));
			// TODO Auto-generated method stub
			return person;
			
		}

}
