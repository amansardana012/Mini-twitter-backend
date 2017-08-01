package challenge.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MessageRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
    public List<Message> findMessagesByPersonList(List<Integer> personListIds, String searchString) {
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("ids", personListIds);
			if(!searchString.isEmpty() && searchString!= null)
			{
				parameters.addValue("search", "%"+searchString+"%");
			}
			StringBuilder sql = new StringBuilder();
			sql.append("select * from messages as m inner join people as p where m.person_id IN (:ids) and m.person_id=p.id");
			if(!searchString.isEmpty() && searchString!= null)
			{
				sql.append(" and m.content like :search");
			}
			List<Message> messages= this.jdbcTemplate.query(sql.toString(), parameters, new MessageRowMapper());
			return messages;
		
		}
		catch(Exception ex)
		{
			return null;
		}
    }
	
}
