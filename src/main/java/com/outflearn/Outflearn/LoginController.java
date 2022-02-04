package com.outflearn.Outflearn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.model.biz.LoginBiz;
import com.outflearn.Outflearn.model.biz.myPageBiz;

@Controller

public class LoginController {


	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	BCryptPasswordEncoder passwordEncoder; //비밀번호 암호화 객체
	
	@Inject
	private LoginBiz biz;
	
	@Inject
	private myPageBiz mBiz;
	
	private String randompassword;
	
	//로그인 페이지로 이동
	@RequestMapping("loginform")
	public String loginform() {
		return "Auth/login";
	}
	
	//회원가입페이지로 이동 
	@RequestMapping("registerform.do")
	public String joinform() {
		return "Auth/MemberRegister";
	}
	
		
	//회원가입
	@RequestMapping("register.do") 
	public String userInsert(@RequestParam String user_id, @RequestParam String user_pw,
					@RequestParam String user_nickname, @RequestParam String user_email	) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		logger.info("암호화 전 비번" + user_pw);
		String encryptPassword = passwordEncoder.encode(user_pw);
		logger.info("암호화 후 비번" + encryptPassword);
		map.put("user_pw", encryptPassword);
		map.put("user_nickname", user_nickname);
		map.put("user_email", user_email);
		
		biz.insertUser(map);	
		
		return "Auth/login";
	}
	
	
	@RequestMapping("kakaoUserinsert")
	@ResponseBody
	public void kakao_userInsert(@RequestParam String user_id, @RequestParam String user_pw,
			@RequestParam String user_nickname, @RequestParam String user_email	, BindingResult result) {
		
		int res = 0;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		System.out.println("암호화 전 비번" + user_pw);
		String encryptPassword = passwordEncoder.encode(user_pw);
		System.out.println("암호화 후 비번" + encryptPassword);
		map.put("user_pw", encryptPassword);
		map.put("user_nickname", user_nickname);
		map.put("user_email", user_email);
		
		biz.insertUser(map);	
		
	}
	
	@RequestMapping("idChk.do")
	@ResponseBody
	public Map<String, Boolean> idChk(String id) {
		logger.info("아이디 중복체크");
		
		logger.info(id);
		boolean idChk = false;
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		
		if(biz.idCheck(id)== null) {
			idChk = true;
			map.put("idChk", idChk);
		}else {
			map.put("idChk", idChk);
		}
		
		return map;
	}

	@RequestMapping(value="nickChk.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> nickChk(String nickname){
		logger.info("닉네임 중복체크");
		logger.info(nickname);
		boolean nickChk = false;
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if(biz.nickCheck(nickname) ==null) {
			nickChk = true;
			map.put("nickChk", nickChk);
		}else {
			map.put("nickChk", nickChk);
		}
		return map;
		
	}
	//이메일 인증번호
	@RequestMapping(value="sendEmail.do", method=RequestMethod.POST)
	@ResponseBody
	public void sendEmail(String email) throws UnsupportedEncodingException, MessagingException {
		randompassword = MakeRandom.GetRandomPassword();
		logger.info(randompassword);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPassword("dkdntvmfjs1");
		mailSender.setPort(587);
		mailSender.setUsername("outflearn@gmail.com");
		if(mailSender.getPort()==587) {
			Properties javaMailProperties = new Properties();
			javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
			mailSender.setJavaMailProperties(javaMailProperties);
		}
		
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
		helper.setFrom(new InternetAddress("outflearn@gmail.com", "아웃프런"));
		helper.setTo(new InternetAddress(email,""));
		logger.info("email:" + email);
		helper.setSubject("[아웃프런]인증번호 test");
		helper.setText("<a><b style='color:blue;'>인증번호 : " +randompassword+"<a>", true);
		
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			logger.error("메일발송 실패", ex);
		}
		
	}
		
	//이메일 인증번호체크
	@RequestMapping(value="emailNumCheck.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> email_check(String ranNumPass) {
		
		
		boolean ranChk = false;
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		
		logger.info(ranNumPass);
		logger.info(randompassword);
		
		if(randompassword.equals(ranNumPass)) {
			ranChk = true;
			map.put("ranChk", ranChk);
		}else {
			map.put("ranchk", ranChk);
		}
		
		return map;
	}
	//이메일 중복체크
	@RequestMapping("emailChk.do")
	@ResponseBody
	public Map<String, Boolean> emailChk(String user_email){
		logger.info("이메일 중복체크");
		logger.info(user_email);
		boolean emailChk = false;
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		
		if(biz.emailChk(user_email)== null) {
			emailChk = true;
			map.put("emailChk", emailChk);
		}else {
			map.put("emailChk", emailChk);
		}
		
		return map;
	}
	//아이디 찾기 폼
	@RequestMapping("findIdForm.do")
	public String findIdForm() {
		return "Auth/FindIdForm";
	}
	//아이디찾기
	@RequestMapping("/findId.do")
	public String findId(HttpServletResponse response, @RequestParam("user_email") String user_email, Model md) throws IOException {
			md.addAttribute("id", biz.findId(response, user_email));
	return "Auth/FindId";
	
	}
	//비밀번호 찾기폼 
	@RequestMapping("/findPwForm.do")
	public String findPwForm() {
		return "Auth/FindPw";
	}
	//비밀번호 찾기
	@RequestMapping("/findPw.do")
	public String findPw() {
			return "Auth/login";
		
	}
	
	//임시비밀번호 보내기
	@RequestMapping(value="sendEmailPw.do", method=RequestMethod.POST)
	@ResponseBody
	public String sendEmailPw(String userEmail, String userId) throws MessagingException, IOException {
		randompassword = MakeRandom.GetRandomPassword();
		logger.info(randompassword);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPassword("dkdntvmfjs1");
		mailSender.setPort(587);
		mailSender.setUsername("outflearn@gmail.com");
		if(mailSender.getPort()==587) {
			Properties javaMailProperties = new Properties();
			javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
			mailSender.setJavaMailProperties(javaMailProperties);
		}
		
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
		helper.setFrom(new InternetAddress("outflearn@gmail.com", "아웃프런"));
		helper.setTo(new InternetAddress(userEmail,""));
		logger.info("email:" + userEmail);
		helper.setSubject("[아웃프런]임시 비밀번호 test");
		helper.setText("<a><b style='color:blue;'>임시 비밀번호 : " +randompassword+"<a>", true);
		
		String encryptPassword = passwordEncoder.encode(randompassword);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", userId);
		map.put("user_email", userEmail);
		map.put("newPw", encryptPassword);
		System.out.println(map);
		biz.findPw(map);
		
		try {
			mailSender.send(msg);
		} catch (MailException ex) {
			logger.error("메일발송 실패", ex);
		}
		return "Auth/login";
	}
	
	@RequestMapping("MemberInfoUpdateForm.do")
	public String memberInfoUpdateForm(Authentication auth, Model model) {
		UserInfoDto dto = (UserInfoDto) auth.getPrincipal();
		String email = dto.getUser_email();
		int idx = email.indexOf("@");
		String email1 = email.substring(0, idx);
		String email2 = email.substring(idx+1);
		model.addAttribute("email1", email1);
		model.addAttribute("email2", email2);
		logger.info(email1);
		logger.info(email2);
		return "Auth/MemberInfoUpdate";
	}

	@RequestMapping("memberInfoUpdate.do")
	public String memberInfoUpdate(String user_id, String user_nickname, @RequestParam("new_pw") String user_pw, @RequestParam("email1") String user_email1, @RequestParam("email2") String user_email2, Model model) throws UnsupportedEncodingException {
		String encryptPassword = passwordEncoder.encode(user_pw);
		Map<String, String> map = new HashMap<String, String>();
			
		map.put("user_id", user_id);
		map.put("user_nickname", user_nickname);
		map.put("user_email", user_email1+"@"+user_email2);
		map.put("user_pw", encryptPassword);
		System.out.println(map);
		
	
		
		int res = biz.updateUserInfo(map);
		if(res>0) {
						
			return "Auth/home";
		} else {
			
			return "Auth/MemberInfoUpdate";
		}
		
	}
	@RequestMapping("currentPwChk.do")
	@ResponseBody
	public Map<String, Boolean> pwChk(String user_pw){
		logger.info("현재 비번 체크");
		logger.info(user_pw);
		boolean pwChk = false;
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		String encryptPassword = passwordEncoder.encode(user_pw);
		logger.info("암호화 후 비번" + encryptPassword);
		
		if(biz.pwChk(encryptPassword)== null) {
			pwChk = true;
			map.put("pwChk", pwChk);
		}else {
			map.put("pwChk", pwChk);
		}
		
		return map;
	}
	
}
