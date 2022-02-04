package com.outflearn.Outflearn.dto;

public class ClassCategoryDto {

	private int class_num;
	private int main_num;
	private int sub_num;
	
	public ClassCategoryDto() {
		super();
	}

	public ClassCategoryDto(int class_num, int main_num, int sub_num) {
		super();
		this.class_num = class_num;
		this.main_num = main_num;
		this.sub_num = sub_num;
	}

	public int getClass_num() {
		return class_num;
	}

	public void setClass_num(int class_num) {
		this.class_num = class_num;
	}

	public int getMain_num() {
		return main_num;
	}

	public void setMain_num(int main_num) {
		this.main_num = main_num;
	}

	public int getSub_num() {
		return sub_num;
	}

	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	
	
	
	
	
	
	
}
