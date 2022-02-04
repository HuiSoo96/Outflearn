package com.outflearn.Outflearn.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;



public class UserLoginFailureHandler implements AuthenticationFailureHandler {

	private String idname; //로그인 id값이 들어오는 input태그 name
	private String passwdname; //로그인 pw값이 들어오는 input태그 name
	private String errormagname; // 로그인페이지에서 에러를 보여줄 때 사용할 변수이름 
	private String defaultFailureUrl; //로그인 실패시 갈 화면 url
	


	public String getIdname() {
		return idname;
	}

	public void setIdname(String idname) {
		this.idname = idname;
	}

	public String getPasswdname() {
		return passwdname;
	}

	public void setPasswdname(String passwdname) {
		this.passwdname = passwdname;
	}

	public String getErrormagname() {
		return errormagname;
	}

	public void setErrormagname(String errormagname) {
		this.errormagname = errormagname;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println("UserLoginFailureHandler:::::::::::: 3");		
		
		String username = request.getParameter(idname);
		String password = request.getParameter(passwdname);
		String errormsg = exception.getMessage();
		
		System.out.println(exception.getLocalizedMessage()+":::::::::::");
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(errormsg);

		
		
		request.setAttribute(idname, username);
		request.setAttribute(passwdname, password);
		request.setAttribute(errormagname, errormsg);		
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
		
	
	}



	
	
	

}