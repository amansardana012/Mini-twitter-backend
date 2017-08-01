package challenge.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MessageRowMapper implements RowMapper<Message> {

	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("id"));
		message.setPersonId(rs.getInt("person_id"));
		message.setContent(rs.getString("content"));
		message.setPersonName(rs.getString("name"));
		message.setPersonHandle(rs.getString("handle"));
		// TODO Auto-generated method stub
		return message;
	}
}
