package com.outflearn.Outflearn.model.biz;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.dto.memberRegisterDto;

@Service
public interface LoginBiz {
	
	public int insertUser(Map<String, String> map);
	
	public Map<String, Object> selectUSer(String user_id);
	
	public UserInfoDto loginDo(String id, String pw);
	
	public UserInfoDto loginChk(String id, String pw);
	
	public int register(Map<String, String> map);
	
	public memberRegisterDto idCheck(String id);
	
	public memberRegisterDto nickCheck(String nickname);
	
	public UserInfoDto loginChkSns(String id);
	
	public boolean registChk(String id);
	
	public int registSns(UserInfoDto dto);
	
	public int updateUserInfo(Map<String, String> map);
	
	public int updateUserPw(UserInfoDto dto);
	
	public int memberWithdraw(UserInfoDto dto);
		
	public int changePw(String password, String id);

	public memberRegisterDto emailChk(String user_email);
	
	public String findId(HttpServletResponse response, String user_email) throws IOException;
	
	public int findPw(Map<String, String> map) throws IOException;
	
	public UserInfoDto pwChk(String user_pw);
	
	public UserInfoDto selectOne(String id);

}
