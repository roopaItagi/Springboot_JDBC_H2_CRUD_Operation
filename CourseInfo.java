package com.learning.SpringJdbcH2.jdbc;

public class CourseInfo {
	
	private int id;
	private String cname;
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "CourseInfo [id=" + id + ", cname=" + cname + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public CourseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCname() {
		return cname;
	}
	public CourseInfo(int id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
	

}
