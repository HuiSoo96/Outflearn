package com.outflearn.Outflearn.dto;

public class SubStreamDto {
	
	private int sub_num;
	private int main_num;
	private String sub_name;
	
	
	public SubStreamDto() {
		
	}

	public SubStreamDto(int sub_num, int main_num, String sub_name) {
		this.sub_num = sub_num;
		this.main_num = main_num;
		this.sub_name = sub_name;
	}
	public int getSub_num() {
		return sub_num;
	}
	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	public int getMain_num() {
		return main_num;
	}
	public void setMain_num(int main_num) {
		this.main_num = main_num;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
}
