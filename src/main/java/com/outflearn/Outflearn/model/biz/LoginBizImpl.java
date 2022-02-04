package com.outflearn.Outflearn.model.biz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.dto.memberRegisterDto;
import com.outflearn.Outflearn.model.dao.LoginDao;

@Service
public class LoginBizImpl implements LoginBiz {
	
	
	@Inject
	private LoginDao dao;

	@Override
	public int insertUser(Map<String, String> map) {
		
		return dao.insertUser(map);
	}

	@Override
	public Map<String, Object> selectUSer(String user_id) {
		
		return dao.selectUSer(user_id);
	}

	@Override
	public UserInfoDto loginDo(String id, String pw) {
		return dao.loginDo(id, pw);
	}
	
	@Override
	public UserInfoDto loginChk(String userId, String userPw) {
		return dao.loginChk(userId, userPw);
	}

	@Override
	public int register(Map<String, String> map) {
		return dao.register(map);
	}

	@Override
	public memberRegisterDto idCheck(String id) {
		
		return dao.idCheck(id);
	}

	@Override
	public memberRegisterDto nickCheck(String nickname) {
		return dao.nickCheck(nickname);
	}

	@Override
	public UserInfoDto loginChkSns(String id) {
		return dao.loginChkSns(id);
	}

	@Override
	public boolean registChk(String id) {
		return dao.registChk(id);
	}

	@Override
	public int registSns(UserInfoDto dto) {
		return dao.registSns(dto);
	}

	@Override
	public int updateUserInfo(Map<String, String> map) {
		return dao.updateUserInfo(map);
	}

	@Override
	public int updateUserPw(UserInfoDto dto) {
		return dao.updateUserPw(dto);
	}

	@Override
	public int memberWithdraw(UserInfoDto dto) {
		return dao.memberWithdraw(dto);
	}

	/*
	  @Override public UserInfoDto findId(String name, String nickname) { return
	  dao.findId(name, nickname); }
	 */
	

	@Override
	public int changePw(String pw, String id) {
		return dao.changePw(pw, id);
	}

	
	@Override
	public memberRegisterDto emailChk(String user_email) {
		return dao.emailChk(user_email);
	}

	@Override
	public String findId(HttpServletResponse response, String user_email) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = dao.findId(user_email);
		
			if(id == null) {
				out.println("<script>");
				out.println("alert('가입된 아이디가 없습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
				return null;
			}else {
				return id;
			}
	}

	@Override
	public int findPw(Map<String, String> map) throws IOException {

		return dao.findPw(map);
	}

	public UserInfoDto pwChk(String user_pw) {
		return dao.pwChk(user_pw);
	}

	public UserInfoDto selectOne(String user_id) {
		return dao.selectOne(user_id);
	}

}
