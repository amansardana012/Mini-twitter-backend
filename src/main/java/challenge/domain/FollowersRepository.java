package challenge.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class FollowersRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
    public List<Follower> findPeopleFollowingByPerson(int personId) {
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", personId);
			String sql ="select * from followers where follower_person_id =:person_id";
			
			List<Follower> peopleFollowing= this.jdbcTemplate.query(sql, namedParameters, new FollowerRowMapper());
			return peopleFollowing;
		
		}
		catch(Exception ex)
		{
			return null;
		}
    }
	
	@Transactional(readOnly=true)
    public boolean followPerson(int followingPersonId, int followerId) {
		try {
			Map<String,Integer> namedParameters = new HashMap<String,Integer>();
			namedParameters.put("followingPersonId", followingPersonId);
			namedParameters.put("followerId", followerId);
			String sql ="insert into followers (person_id, follower_person_id) VALUES (:followingPersonId, :followerId)";
			
			 this.jdbcTemplate.update(sql, namedParameters);
			 return true;
		}
		catch(Exception ex)
		{
			return false;
		}
    }
	
	@Transactional(readOnly=true)
    public boolean unFollowPerson(int unFollowingPersonId, int followerId) {
		try {
			Map<String,Integer> namedParameters = new HashMap<String,Integer>();
			namedParameters.put("unFollowingPersonId", unFollowingPersonId);
			namedParameters.put("followerId", followerId);
			String sql ="Delete from followers where person_id=:unFollowingPersonId and follower_person_id=:followerId";
			
			 this.jdbcTemplate.update(sql, namedParameters);
			 return true;
		}
		catch(Exception ex)
		{
			return false;
		}
    }
	
	@Transactional(readOnly=true)
    public List<Person> findPeopleFollowingByPerson(Person person){
		try
		{
			SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", person.getId());
			String sql ="SELECT * FROM FOLLOWERS AS f INNER JOIN PEOPLE AS p where f.follower_person_id=:person_id and f.person_id= p.id";
			
			List<Person> peopleFollowing = this.jdbcTemplate.query(sql, namedParameters, new FollowingPersonMapper());
			return peopleFollowing;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	@Transactional(readOnly=true)
    public List<Person> findFollowersByPerson(Person person){
		try
		{
			SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", person.getId());
			String sql ="SELECT * FROM FOLLOWERS AS f INNER JOIN PEOPLE AS p where f.person_id=:person_id and f.follower_person_id= p.id";
			
			List<Person> followers = this.jdbcTemplate.query(sql, namedParameters, new FollowerPersonMapper());
			return followers;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public List<PopularFollower> popularAllFollower()
	{
		try
		{
			String sql =" select t.id, t.name, (select name from people  where id = (select  p.person_id  from followers as f Inner join followers as p where f.follower_person_id = p.person_id and f.person_id =t.id Group by p.person_id ORDER BY COUNT(p.person_id) DESC limit 1)) as popularFollower from people as t";
			
			List<PopularFollower> followers = this.jdbcTemplate.query(sql, new PopularFollowerRowMapper());
			return followers;
		}
		catch(Exception ex)
		{
			return null;
		}
		 
	}
}
