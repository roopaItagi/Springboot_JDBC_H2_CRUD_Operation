package com.learning.SpringJdbcH2.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper<CourseInfo> {

	@Override
	public CourseInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		return new CourseInfo(rs.getInt("id"),
				rs.getString("cname"));
	}

}
