package com.outflearn.Outflearn;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.model.biz.LoginBiz;
import com.outflearn.Outflearn.service.UserAuthenticationService;

@Controller
public class UserAuthController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);
	

	
	@Inject
	LoginBiz loginbiz;	
		
	@Inject
	BCryptPasswordEncoder passwordEncoder; //비밀번호 암호화 객체
	
	@Inject
	protected UserAuthenticationService userAuthenticationService;
	
	@RequestMapping("test01")
	public String test012(@RequestParam String test) {
		

		System.out.println(test);
				
		return "Auth/login";
	}
	
	@RequestMapping("test.do")
	public String testpage(Authentication auth ) {

		logger.info("test.do 입장~!");		
		
		System.out.println("auth test 1 : " + auth);
		System.out.println("아이디: "+auth.getName());
		System.out.println("등급: "+auth.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		System.out.println(securityContext);
		
		
		System.out.println("========================================");
		//나머지 정보
		UserInfoDto dto = (UserInfoDto)auth.getPrincipal();
		String email =  dto.getUser_email();
		System.out.println(email);
		System.out.println(dto.getAuthorities());
		
		System.out.println(dto.isEnabled()+"=============================================");
		
		System.out.println("비번"+dto.getPassword());		

		return "test01";
	}
	
	
//	//권한이 없는 사용자에게 안내 페이지 출력
//	@RequestMapping("denied")
//	public String denied(Model model, AuthenticateAction auth, HttpServletRequest req ) {
//		AccessDeniedException ade = (AccessDeniedException)req.getAttribute(WebAttributes.ACCESS_DENIED_403);
//		
//		model.addAttribute("errMsg", ade);
//		return "denied";
//	}
	
//	@RequestMapping("logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:loginform";
//	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("sulkiki")
	public String Sulkiki() {
		
		
		
		return"test02";
	}
	
	//권한 두개이상: @Secured({"ROLE_ADMIN", "ROLE_USER"})
	//권한 하나: @Secured("ROLE_ADMIN")
	
	@RequestMapping("tutorgogo")
	public String tutorGOGO() {
		return "test03";
	}
	
	//카카오 네이버 로그인
	@RequestMapping(value="snsLogin", method=RequestMethod.POST)
	public String login(HttpServletRequest request,@RequestParam String id, @RequestParam boolean idCheck) {
				
		//boolean idChk = idCheck;
		
		System.out.println("sns로그인 들어옴:::::: ");	
		
		if(idCheck == true) { 
			
			System.out.println("비회원 가입 시작");
			int res = 0;
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_id", id);
			System.out.println("암호화 전 비번" + id);
			String encryptPassword = passwordEncoder.encode(id);
			System.out.println("암호화 후 비번" + encryptPassword);
			map.put("user_pw", encryptPassword);
			map.put("user_nickname", id);
			map.put("user_email", id);
			
			res = loginbiz.insertUser(map);	
			
			if(res>0) {
				System.out.println("비회원 가입 후 로그인");
				String loginId = id;
				
				getAuth(request, loginId);
			}else {
				System.out.println("회원가입에 실패했습니다");
			}

			
			
		}else {
			
			System.out.println("일반회원 로그인");
			String loginId = id;
			
			getAuth(request, loginId);
			
		}

				
		return "redirect:/";
	}
	

	//네이버 로그인 콜백페이지로 이동 
	@RequestMapping("naverCallback")
	public String naverCallback() {
		
		
		return "Auth/callback";
		
	}
	
	
	//sns로그인 권한 강제 부여 
	//강제부여한는거라서 성공/실패 핸들러는 안타는 것 같당 
	public void getAuth(HttpServletRequest request, String loginId) {
		
		UserInfoDto userInfoDto = (UserInfoDto) userAuthenticationService.loadUserByUsername(loginId);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userInfoDto, userInfoDto.getPassword(), userInfoDto.getAuthorities());

		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		
	}
	
	
	

	
	

	
	
	

}
