package com.outflearn.Outflearn.model.biz;

import java.util.List;
import java.util.Map;

import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.QADto;
import com.outflearn.Outflearn.dto.RoadMapCon;
import com.outflearn.Outflearn.dto.RoadMapInfoDto;

public interface myPageBiz {
		
		public List<ClassInfoDto> getPreBasketClass(int user_num);
		public List<ClassInfoDto> getPreSubscribe(int user_num);
		public List<RoadMapCon> getPreSubRoadmap(int user_num);
		public List<QADto> getPreQA(int user_num);
		public List<ClassInfoDto> getPreMyClass(int user_num);
		
		public List<ClassInfoDto> getBasketClass(int user_num);
		public List<ClassInfoDto> getSubscribe(int user_num);
		public List<RoadMapCon> getSubRoadmap(int user_num);
		public List<QADto> getQA(int user_num);
		
		public int reqLecturer(String lecturerNum, String lecturerPhone, String lecturerIntro, String lecturerClass);
		
		public int updateNickname(String nickname, int user_num);
		public int updateEmail(String email, int user_num);
		public boolean checkPw(String encryptPw, int user_num);
		public int updatePw(String afterPw, int user_num);
		
//강사
		
		public int setLiveSchedule(String setClass, String setTitle, String setDate, String setAuthor);
		public List<ClassInfoDto> myClass(int user_num);
		public int deleteClass(String class_num);
		public List<RoadMapInfoDto> myRoadmap(int user_num);
		public int deleteRoadmap(String roadmap_num);
		
//관리자
		
		public List<Map<String, String>> getReqLecturer();
		public List<Map<String, String>> getUserList();
		
		public List<Map<String, String>> getPreReqLecturer();
		public List<Map<String, String>> getPreUserList();
		
		public int acceptReq(String user_num);
		public int deniReq(String user_num);
		
		public int userEnabled(String user_num);
		public int userDisabled(String user_num);
	
}
