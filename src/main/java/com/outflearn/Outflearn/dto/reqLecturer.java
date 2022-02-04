package com.outflearn.Outflearn.dto;

public class reqLecturer {

	private int req_num;
	private int user_num;
	private String req_phone;
	private String req_pr;
	private String req_class;

	public reqLecturer() {
		super();
	}

	public reqLecturer(int req_num, int user_num, String req_phone, String req_pr, String req_class) {
		super();
		this.req_num = req_num;
		this.user_num = user_num;
		this.req_phone = req_phone;
		this.req_pr = req_pr;
		this.req_class = req_class;
	}

	public int getReq_num() {
		return req_num;
	}

	public void setReq_num(int req_num) {
		this.req_num = req_num;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getReq_phone() {
		return req_phone;
	}

	public void setReq_phone(String req_phone) {
		this.req_phone = req_phone;
	}

	public String getReq_pr() {
		return req_pr;
	}

	public void setReq_pr(String req_pr) {
		this.req_pr = req_pr;
	}

	public String getReq_class() {
		return req_class;
	}

	public void setReq_class(String req_class) {
		this.req_class = req_class;
	}

}
