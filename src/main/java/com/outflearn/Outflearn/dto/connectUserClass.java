package com.outflearn.Outflearn.dto;

public class connectUserClass {

	private int user_num;
	private int class_num;

	public connectUserClass() {
		super();
	}

	public connectUserClass(int user_num, int class_num) {
		super();
		this.user_num = user_num;
		this.class_num = class_num;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public int getClass_num() {
		return class_num;
	}

	public void setClass_num(int class_num) {
		this.class_num = class_num;
	}

}
