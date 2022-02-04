package com.outflearn.Outflearn.dto;

public class ClassInfoDto {

	private int class_num;
	private int user_num;
	private String class_title;
	private String class_author;
	private String class_intro;
	private String class_img;
	private String class_live;
	private int class_rating;
	private int class_price;
	private int class_subcount;
	private String class_studentlevel;


	public ClassInfoDto() {
		super();
	}

	public ClassInfoDto(int class_num, int user_num, String class_title, String class_author, String class_intro,
			String class_img,  String class_live, int class_rating, int class_price, int class_subcount,
			String class_studentlevel) {
		super();
		this.class_num = class_num;
		this.user_num = user_num;
		this.class_title = class_title;
		this.class_author = class_author;
		this.class_intro = class_intro;
		this.class_img = class_img;
		this.class_live = class_live;
		this.class_rating = class_rating;
		this.class_price = class_price;
		this.class_subcount = class_subcount;
		this.class_studentlevel = class_studentlevel;
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

	public String getClass_title() {
		return class_title;
	}

	public void setClass_title(String class_title) {
		this.class_title = class_title;
	}

	public String getClass_author() {
		return class_author;
	}

	public void setClass_author(String class_author) {
		this.class_author = class_author;
	}

	public String getClass_intro() {
		return class_intro;
	}

	public void setClass_intro(String class_intro) {
		this.class_intro = class_intro;
	}

	public String getClass_img() {
		return class_img;
	}

	public void setClass_img(String class_img) {
		this.class_img = class_img;
	}
	

	public String getClass_live() {
		return class_live;
	}

	public void setClass_live(String class_live) {
		this.class_live = class_live;
	}

	public int getClass_rating() {
		return class_rating;
	}

	public void setClass_rating(int class_rating) {
		this.class_rating = class_rating;
	}

	public int getClass_price() {
		return class_price;
	}

	public void setClass_price(int class_price) {
		this.class_price = class_price;
	}

	public int getClass_subcount() {
		return class_subcount;
	}

	public void setClass_subcount(int class_subcount) {
		this.class_subcount = class_subcount;
	}

	public String getClass_studentlevel() {
		return class_studentlevel;
	}

	public void setClass_studentlevel(String class_studentlevel) {
		this.class_studentlevel = class_studentlevel;
	}



}
