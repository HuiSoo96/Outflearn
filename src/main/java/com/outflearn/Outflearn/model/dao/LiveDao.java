package com.outflearn.Outflearn.model.dao;

import java.util.List;


import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.LiveDto;

public interface LiveDao {
	
	String namespace = "live.";
	
	// Live
	public List<LiveDto> liveCalendar();
	public ClassInfoDto livePopup(int live_num);
	public List<ClassInfoDto> getMyClass(int user_num);
	public List<ClassInfoDto> liveRooms(String[] liveRooms);
	public ClassInfoDto getClassInfo(String class_num);
	
}
