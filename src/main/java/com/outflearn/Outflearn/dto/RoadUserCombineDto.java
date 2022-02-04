package com.outflearn.Outflearn.dto;

public class RoadUserCombineDto {
	private int roadmap_num;
	private int user_num;
	private String roadmap_title;
	private String roadmap_content;
	private int roadmap_subscribe;
	private int roadmap_like;
	private int roadmap_share;
	private int main_num;
	private String user_nickname;

	public RoadUserCombineDto() {
	}

	public RoadUserCombineDto(int roadmap_num, int user_num, String roadmap_title, String roadmap_content,
			int roadmap_subscribe, int roadmap_like, int roadmap_share, int main_num, String user_nickname) {

		this.roadmap_num = roadmap_num;
		this.user_num = user_num;
		this.roadmap_title = roadmap_title;
		this.roadmap_content = roadmap_content;
		this.roadmap_subscribe = roadmap_subscribe;
		this.roadmap_like = roadmap_like;
		this.roadmap_share = roadmap_share;
		this.main_num = main_num;
		this.user_nickname = user_nickname;
	}

	public int getRoadmap_num() {
		return roadmap_num;
	}

	public void setRoadmap_num(int roadmap_num) {
		this.roadmap_num = roadmap_num;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getRoadmap_title() {
		return roadmap_title;
	}

	public void setRoadmap_title(String roadmap_title) {
		this.roadmap_title = roadmap_title;
	}

	public String getRoadmap_content() {
		return roadmap_content;
	}

	public void setRoadmap_content(String roadmap_content) {
		this.roadmap_content = roadmap_content;
	}

	public int getRoadmap_subscribe() {
		return roadmap_subscribe;
	}

	public void setRoadmap_subscribe(int roadmap_subscribe) {
		this.roadmap_subscribe = roadmap_subscribe;
	}

	public int getRoadmap_like() {
		return roadmap_like;
	}

	public void setRoadmap_like(int roadmap_like) {
		this.roadmap_like = roadmap_like;
	}

	public int getRoadmap_share() {
		return roadmap_share;
	}

	public void setRoadmap_share(int roadmap_share) {
		this.roadmap_share = roadmap_share;
	}

	public int getMain_num() {
		return main_num;
	}

	public void setMain_num(int main_num) {
		this.main_num = main_num;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
}
