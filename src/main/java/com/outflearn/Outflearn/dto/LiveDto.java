package com.outflearn.Outflearn.dto;

public class LiveDto {

	private int live_num;
	private int class_num;
	private String live_title;
	private String live_author;
	private String live_time;

	public LiveDto() {
		super();
	}

	public LiveDto(int live_num, int class_num, String live_title, String live_author, String live_time) {
		super();
		this.live_num = live_num;
		this.class_num = class_num;
		this.live_title = live_title;
		this.live_author = live_author;
		this.live_time = live_time;
	}

	public int getLive_num() {
		return live_num;
	}

	public void setLive_num(int live_num) {
		this.live_num = live_num;
	}

	public int getClass_num() {
		return class_num;
	}

	public void setClass_num(int class_num) {
		this.class_num = class_num;
	}

	public String getLive_title() {
		return live_title;
	}

	public void setLive_title(String live_title) {
		this.live_title = live_title;
	}

	public String getLive_author() {
		return live_author;
	}

	public void setLive_author(String live_author) {
		this.live_author = live_author;
	}

	public String getLive_time() {
		return live_time;
	}

	public void setLive_time(String live_time) {
		this.live_time = live_time;
	}

}
