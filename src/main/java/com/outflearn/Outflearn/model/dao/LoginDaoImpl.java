package com.outflearn.Outflearn.model.dao;

import java.util.HashMap;

import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.dto.memberRegisterDto;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Inject
	SqlSessionTemplate sqlSession;


	@Override
	public int insertUser(Map<String, String> map) {
	
		return sqlSession.insert(NAMESPACE+"insertUser", map);
	}

	@Override
	public Map<String, Object> selectUSer(String user_id) {
		
		return sqlSession.selectOne(NAMESPACE+"selectUser", user_id);
	}

	@Override
	public UserInfoDto loginDo(String id, String pw) {

		UserInfoDto dto = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);

		try {
			dto = sqlSession.selectOne(NAMESPACE + "login", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public UserInfoDto loginChk(String id, String pw) {
		UserInfoDto dto = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", id);
		map.put("userPw", pw);
		
		dto = sqlSession.selectOne(NAMESPACE + "loginChk", map);
		
		return dto;
	}

	@Override
	public int register(Map<String, String> map) {
		int res = 0;
		
		res = sqlSession.insert(NAMESPACE + "memberRegister", map); 
			
	
		return res;
	}

	@Override
	public memberRegisterDto idCheck(String id) {
		
		memberRegisterDto dto = null;
		
		System.out.println("아이디 유효성 검사중");
		
			dto = sqlSession.selectOne(NAMESPACE + "idChk", id);
				
		return dto;
	}

	@Override
	public memberRegisterDto nickCheck(String nickname) {
		memberRegisterDto dto = null;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("nickname", nickname);
		
		System.out.println("닉네임 유효성 검사중");
		
		dto = sqlSession.selectOne(NAMESPACE + "nickChk", map);
		
		return dto;
	}

	
	  @Override public UserInfoDto loginChkSns(String id) {
	 /* 
	  UserInfoDto dto = new UserInfoDto();
	  
	  Map<String, String> map = new HashMap<String, String>(); map.put("userId",
	  id);
	  
	  dto = sqlSession.selectOne(NAMESPACE + "loginChkSns", map);
	 */ 
	  return null; }
	 

	
	  @Override public boolean registChk(String id) { 
		
		  boolean temp = false;
	  /*
	  UserInfoDto dto = new UserInfoDto();
	  
	  Map<String, String> map = new HashMap<String, String>(); map.put("userId",
	  id);
	  
	  dto = sqlSession.selectOne(NAMESPACE + "registChk", map); if(dto == null) {
	  temp = true; } else { temp = false; }
	 */  
	  return temp; }
	

	@Override
	public int registSns(UserInfoDto dto) {
		int res = 0;
		
		res = sqlSession.insert(NAMESPACE + "registSns", dto);
			if(res>0) {
				sqlSession.commit();
			}
		
		return res;
	}

	@Override
	public int updateUserInfo(Map<String, String> map) {
		int res = 0;
		
		res = sqlSession.update(NAMESPACE + "updateUserInfo", map);
	
		return res;
	}

	@Override
	public int updateUserPw(UserInfoDto dto) {
		int res = 0;
		
		res = sqlSession.update(NAMESPACE + "updateUserPw", dto);
					
		return res;
	}

	@Override
	public int memberWithdraw(UserInfoDto dto) {

		return 0;
	}


	@Override
	public int changePw(String pw, String id) {
		int res = 1;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userPw", pw);
		map.put("userId", id);
			
		res = sqlSession.insert(NAMESPACE + "changePw", map);
		
		return res;
	}

	public UserInfoDto userAuth(UserInfoDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public memberRegisterDto emailChk(String user_email) {
					
			memberRegisterDto dto = null;
			
			System.out.println("이메일 유효성 검사중");
			
			dto = sqlSession.selectOne(NAMESPACE + "emailChk", user_email);
					
			return dto;
		
		}

	@Override
	public String findId(String user_email) {
		
		return sqlSession.selectOne(NAMESPACE + "findId", user_email);
	}

	@Override
	public int findPw(Map<String, String> map) {
		int res = 0;
		
		res = sqlSession.update(NAMESPACE + "findPw", map);
		System.out.println("dao왔다");
		return res;
	}

	@Override
	public UserInfoDto pwChk(String user_pw) {
		UserInfoDto dto = null;
		dto = sqlSession.selectOne(NAMESPACE + "currentPwChk", user_pw);
		System.out.println("다오임플 현재비번");
		return dto;
	}

	public UserInfoDto selectOne(String user_id) {
		UserInfoDto dto = null;
		dto = sqlSession.selectOne(NAMESPACE + "selectOne", user_id);
		return dto;
	}


}
