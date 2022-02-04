package com.outflearn.Outflearn.dto;

public class ClassReviewDto {
	
	private int review_num;
	private int class_num;
	private int user_num;
	private String review_content;
	private int user_star;
	private int review_groupno;
	private int review_groupsq;
	private int review_titletab;
	private String user_nickname;
	
	public ClassReviewDto() {
		super();
	}



	public ClassReviewDto(int review_num, int class_num, int user_num, String review_content, int user_star,
			int review_groupno, int review_groupsq, int review_titletab, String user_nickname) {
		super();
		this.review_num = review_num;
		this.class_num = class_num;
		this.user_num = user_num;
		this.review_content = review_content;
		this.user_star = user_star;
		this.review_groupno = review_groupno;
		this.review_groupsq = review_groupsq;
		this.review_titletab = review_titletab;
		this.user_nickname = user_nickname;
	}

	public int getReview_num() {
		return review_num;
	}



	public void setReview_num(int review_num) {
		this.review_num = review_num;
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



	public String getReview_content() {
		return review_content;
	}



	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}



	public int getUser_star() {
		return user_star;
	}



	public void setUser_star(int user_star) {
		this.user_star = user_star;
	}



	public int getReview_groupno() {
		return review_groupno;
	}



	public void setReview_groupno(int review_groupno) {
		this.review_groupno = review_groupno;
	}



	public int getReview_groupsq() {
		return review_groupsq;
	}



	public void setReview_groupsq(int review_groupsq) {
		this.review_groupsq = review_groupsq;
	}



	public int getReview_titletab() {
		return review_titletab;
	}



	public void setReview_titletab(int review_titletab) {
		this.review_titletab = review_titletab;
	}

	public String getUser_nickname() {
		return user_nickname;
	}
	
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
}