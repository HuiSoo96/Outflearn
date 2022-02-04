package com.outflearn.Outflearn.service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;





public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(UserLoginSuccessHandler.class);
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	private String loginidname;
	private String defaultUrl;
	
	
	
	public String getLoginidname() {
		return loginidname;
	}



	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}



	public String getDefaultUrl() {
		return defaultUrl;
	}



	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}



	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		System.out.println("UserLoginSuccessHandler:::::::::::: 2");
		
		
		//에러세션지우기
		clearAuthenticationAttributes(request);
		
		//redirect URL 작업: 로그인 전단계 페이지로 리다이렉트 
		resultRedirectStrategy(request, response, authentication);

		
	}



	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session==null) {
			System.out.println("세션없음::::::");
			return;
		}
		System.out.println("세션삭제:::::");
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		//AUTHENTICATION_EXCEPTION 이름 값으로 정의된 세션을 지운다 
	}



	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//RequestCache 객체를 통해 savedRequest객체를 가져온다
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		//세션에 이동 할 URL정보가 담겨져 있들 때 , 즉 인증 권한이 필요한 페이지에 접근했을 때 
		//saveRequest객체를 통해 getRedirectUrl(로그인 화면을 보기 전에 갔던 URL)을 가져온다
		//else: 직접 로그인 화면으로 갔을 경우
		if(savedRequest !=null) {
			
			logger.debug("권한이 필요한 페이지에 접근했을 경우");
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStratgy.sendRedirect(request, response, targetUrl);
			
		}else {
			logger.debug("직접 로그인 URL로 이동했을경우");
			redirectStratgy.sendRedirect(request, response, defaultUrl);
		}
		
		
		
	}
	
	
	
	

}
