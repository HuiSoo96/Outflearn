package com.outflearn.Outflearn.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.QADto;
import com.outflearn.Outflearn.dto.RoadMapCon;
import com.outflearn.Outflearn.dto.RoadMapInfoDto;
import com.outflearn.Outflearn.dto.UserInfoDto;

@Repository
public class myPageDaoImpl implements myPageDao {

	@Autowired
	public SqlSessionTemplate sqlSession;

	@Override
	public List<ClassInfoDto> getSubscribe(int user_num) {
		return sqlSession.selectList(namespace + "getSubscribe", user_num);
	}

	@Override
	public List<ClassInfoDto> getBasketClass(int user_num) {
		return sqlSession.selectList(namespace + "getBasketClass", user_num);
	}

	@Override
	public List<RoadMapCon> getSubRoadmap(int user_num) {
		return sqlSession.selectList(namespace + "getSubRoadmap", user_num);
	}

	@Override
	public List<QADto> getQA(int user_num) {
		return sqlSession.selectList(namespace + "getQA", user_num);
	}

	@Override
	public List<ClassInfoDto> getPreBasketClass(int user_num) {
		return sqlSession.selectList(namespace + "getPreBasketClass", user_num);
	}

	@Override
	public List<ClassInfoDto> getPreSubscribe(int user_num) {
		return sqlSession.selectList(namespace + "getPreSubscribe", user_num);
	}

	@Override
	public List<RoadMapCon> getPreSubRoadmap(int user_num) {
		return sqlSession.selectList(namespace + "getPreSubRoadmap", user_num);
	}

	@Override
	public List<QADto> getPreQA(int user_num) {
		return sqlSession.selectList(namespace + "getPreQA", user_num);
	}
	
	@Override
	public List<ClassInfoDto> getPreMyClass(int user_num) {
		return sqlSession.selectList(namespace + "getPreMyClass", user_num);
	}
	
	@Override
	public int reqLecturer(Map<String, String> map) {
		return sqlSession.insert(namespace + "reqLecturer", map);
	}
	
	@Override
	public int updateNickname(Map<String, Object> map) {
		
		return sqlSession.update(namespace + "updateNickname", map);
	}
	
	@Override
	public int updateEmail(Map<String, Object> map) {
		return sqlSession.update(namespace +"updateEmail", map);
	}
	
	@Override
	public Map<String, String> checkPw(Map<String, Object> map) {
		return sqlSession.selectOne(namespace + "checkPw", map);
	}
	
	@Override
	public int updatePw(Map<String, Object> map) {
		return sqlSession.update(namespace + "updatePw", map);
	}
	
	
//강사
	
	@Override
	public int setLiveSchedule(Map<String, String> map) {
		return sqlSession.insert(namespace + "setLiveSchedule", map);
	}
	
	@Override
	public List<ClassInfoDto> myClass(int user_num) {
		return sqlSession.selectList(namespace + "myClass", user_num);
	}
	
	@Override
	public int deleteClass(String class_num) {
		return sqlSession.delete(namespace + "deleteClass", class_num);
	}
	
	@Override
	public List<RoadMapInfoDto> myRoadmap(int user_num) {
		return sqlSession.selectList(namespace + "myRoadmap", user_num);
	}
	
	@Override
	public int deleteRoadmap(String roadmap_num) {
		return sqlSession.delete(namespace + "deleteRoadmap", roadmap_num);
	}
	
//관리자	

	@Override
	public List<Map<String, String>> getReqLecturer() {
		return sqlSession.selectList(namespace + "getReqLecturer");
	}

	@Override
	public List<Map<String, String>> getUserList() {
		return sqlSession.selectList(namespace + "getUserList");
	}

	@Override
	public List<Map<String, String>> getPreReqLecturer() {
		return sqlSession.selectList(namespace + "getPreReqLecturer");
	}

	@Override
	public List<Map<String, String>> getPreUserList() {
		return sqlSession.selectList(namespace + "getPreUserList");
	}

	@Override
	public int acceptReq(String user_num) {

		int res = sqlSession.update(namespace + "acceptReq", user_num);
		int result = 0;
		
		if (res > 0) {
			result = sqlSession.delete(namespace + "deniReq", user_num);
		} else {
			return 0;
		}

		return result;
	}

	@Override
	public int deniReq(String user_num) {

		return sqlSession.delete(namespace + "deniReq", user_num);
	}

	@Override
	public int userEnabled(String user_num) {
		return sqlSession.update(namespace + "userEnabled", user_num);
	}

	@Override
	public int userDisabled(String user_num) {
		return sqlSession.update(namespace + "userDisabled", user_num);
	}
	
}
