package com.outflearn.Outflearn.dto;

public class QADto {

	private int qa_num;
	private int class_num;
	private int user_num;
	private String user_nickname;
	private String qa_title;
	private String qa_content;
	private int qa_group_no;
	private int qa_group_sq;
	
	public QADto() {
	
	}

	public QADto(int qa_num, int class_num, int user_num, String qa_title, String qa_content, int qa_group_no,
			int qa_group_sq, String user_nickname) {
		super();
		this.qa_num = qa_num;
		this.class_num = class_num;
		this.user_nickname = user_nickname;
		this.user_num = user_num;
		this.qa_title = qa_title;
		this.qa_content = qa_content;
		this.qa_group_no = qa_group_no;
		this.qa_group_sq = qa_group_sq;
	}	
	
	public int getQa_num() {
		return qa_num;
	}

	public void setQa_num(int qa_num) {
		this.qa_num = qa_num;
	}

	public int getClass_num() {
		return class_num;
	}

	public void setClass_num(int class_num) {
		this.class_num = class_num;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getQa_title() {
		return qa_title;
	}

	public void setQa_title(String qa_title) {
		this.qa_title = qa_title;
	}

	public String getQa_content() {
		return qa_content;
	}

	public void setQa_content(String qa_content) {
		this.qa_content = qa_content;
	}

	public int getQa_group_no() {
		return qa_group_no;
	}

	public void setQa_group_no(int qa_group_no) {
		this.qa_group_no = qa_group_no;
	}

	public int getQa_group_sq() {
		return qa_group_sq;
	}

	public void setQa_group_sq(int qa_group_sq) {
		this.qa_group_sq = qa_group_sq;
	}
	
}
