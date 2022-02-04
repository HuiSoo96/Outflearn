package com.outflearn.Outflearn.model.dao;

import java.sql.SQLWarning;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.outflearn.Outflearn.dto.ClassDataDto;
import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.ClassIntroduceDto;
import com.outflearn.Outflearn.dto.LiveDto;
import com.outflearn.Outflearn.dto.connectUserClass;
import com.outflearn.Outflearn.dto.ClassReviewDto;

@Repository
public class LiveDaoImpl implements LiveDao{

	@Autowired
	public SqlSessionTemplate sqlSession;


// --------------------------------------------------- Live

	@Override
	public List<LiveDto> liveCalendar() {

		List<LiveDto> list = sqlSession.selectList(namespace + "liveCalendar");
		return list;
	}


	@Override
	public ClassInfoDto livePopup(int live_num) {

		return sqlSession.selectOne(namespace + "livePopup", live_num);
	}

	@Override
	public List<ClassInfoDto> getMyClass(int user_num) {

		return sqlSession.selectList(namespace + "getMyClass", user_num);
	}

	
	@Override
	public List<ClassInfoDto> liveRooms(String[] liveRooms) {
		
		Map<String, String[]> map =  new HashMap<String, String[]>();
		map.put("liveRooms", liveRooms);
		
		return sqlSession.selectList(namespace + "liveRooms", map);
	}


	@Override
	public ClassInfoDto getClassInfo(String class_num) {
		return sqlSession.selectOne(namespace + "getClassInfo", class_num);
	}
}
