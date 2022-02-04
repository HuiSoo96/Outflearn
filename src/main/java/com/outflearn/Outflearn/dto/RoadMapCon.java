package com.outflearn.Outflearn.dto;

public class RoadMapCon {

	private int class_num;
	private int roadmap_num;
	private int roadmap_cue;

	public RoadMapCon() {
		super();
	}

	public RoadMapCon(int class_num, int roadmap_num, int roadmap_cue) {
		super();
		this.class_num = class_num;
		this.roadmap_num = roadmap_num;
		this.roadmap_cue = roadmap_cue;
	}

	public int getClass_num() {
		return class_num;
	}

	public void setClass_num(int class_num) {
		this.class_num = class_num;
	}

	public int getRoadmap_num() {
		return roadmap_num;
	}

	public void setRoadmap_num(int roadmap_num) {
		this.roadmap_num = roadmap_num;
	}

	public int getRoadmap_cue() {
		return roadmap_cue;
	}

	public void setRoadmap_cue(int roadmap_cue) {
		this.roadmap_cue = roadmap_cue;
	}

}
