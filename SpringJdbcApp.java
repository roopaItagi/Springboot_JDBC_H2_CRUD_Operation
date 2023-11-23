package com.learning.SpringJdbcH2.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJdbcApp {
	
	private static final String SQL_INSERT_QUERY = "insert into course(id,cname) values (?,?) ";
	private static final String SQL_DELETE_QUERY = "delete from course where id=?";
	private static final String SQL_SELECT_BY_ID_QUERY = "select * from course where id=?";
	private static final String SQL_SELECT_ALL_QUERY = "select * from course";
	
	private static final String SQL_UPDATE_QUERY = "update course set cname=? where id=? ";
	
	
	@Autowired
	private JdbcTemplate springJdbc;

	public void insert(CourseInfo course) {
		springJdbc.update(SQL_INSERT_QUERY,course.getId(),course.getCname());
		
	}

	public void deleteById(int id) {

		int rows =springJdbc.update(SQL_DELETE_QUERY,id);
		if(rows>0) {
			System.out.println("deletion successful");
			
		}
		else {
			System.out.println("deletion failed");
		}
		
	}
	
	public CourseInfo findById(long id) {
		return springJdbc.queryForObject(SQL_SELECT_BY_ID_QUERY, new BeanPropertyRowMapper<>(CourseInfo.class),id);
		
	}
	
	public List<CourseInfo> fetchAll() {
		return springJdbc.query(SQL_SELECT_ALL_QUERY,new CourseRowMapper());
		
	}
	public void updateById(CourseInfo course) {

		int rows=springJdbc.update(SQL_UPDATE_QUERY,course.getCname(),course.getId());
		if(rows>0) {
			System.out.println("Update successful");
			
		}
		else {
			System.out.println("update failed");
		}
		
	}
	
}
