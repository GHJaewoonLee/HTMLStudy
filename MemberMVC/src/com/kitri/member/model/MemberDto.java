package com.kitri.member.model;

public class MemberDto {

	private String id;
	private String name;
	private String pass;
	private String emailId;
	private String emailDomain;
	private String joindate;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getEmailid() {
		return emailId;
	}
	
	public void setEmailid(String emailid) {
		this.emailId = emailid;
	}
	
	public String getEmaildomain() {
		return emailDomain;
	}
	
	public void setEmaildomain(String emaildomain) {
		this.emailDomain = emaildomain;
	}
	
	public String getJoindate() {
		return joindate;
	}
	
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
}
