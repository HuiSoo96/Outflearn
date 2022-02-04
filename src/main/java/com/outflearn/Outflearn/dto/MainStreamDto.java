package com.outflearn.Outflearn.dto;

public class MainStreamDto {
	
	private int main_num;
	private String main_name;
	
	
	public MainStreamDto() {
		
	}

	public MainStreamDto(int main_num, String main_name) {
		this.main_num = main_num;
		this.main_name = main_name;
	}
	
	public int getMain_num() {
		return main_num;
	}
	public void setMain_num(int main_num) {
		this.main_num = main_num;
	}
	public String getMain_name() {
		return main_name;
	}
	public void setMain_name(String main_name) {
		this.main_name = main_name;
	}
	
	
}
