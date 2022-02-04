package com.outflearn.Outflearn.dto;

public class ClassIntroduceDto {
	
	private int class_num;
	private String class_content;
	
	public ClassIntroduceDto() {
	
	}
	
	public ClassIntroduceDto(int class_num) {
		this.class_num = class_num;
	}

	public ClassIntroduceDto(int class_num, String class_content) {
		this.class_num = class_num;
		this.class_content = class_content;
	}
	public int getClass_num() {
		return class_num;
	}
	public void setClass_num(int class_num) {
		this.class_num = class_num;
	}
	public String getClass_content() {
		return class_content;
	}
	public void setClass_content(String class_content) {
		this.class_content = class_content;
	}
	
	
}
