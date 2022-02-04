package com.outflearn.Outflearn.dto;



public class memberRegisterDto {
	
	private String userid;
	private String user_nickName;
	private String user_email;
	
	
	public memberRegisterDto() {}


	public memberRegisterDto(String userid, String user_nickName, String user_email) {

		this.userid = userid;
		this.user_nickName = user_nickName;
		this.user_email = user_email;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUser_nickName() {
		return user_nickName;
	}


	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
	
	
	
	
	
	
	

}
