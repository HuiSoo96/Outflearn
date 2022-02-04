package com.outflearn.Outflearn;

import javax.inject.Inject;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.model.biz.myPageBiz;

@Controller
public class myPageController {

	@Autowired
	public myPageBiz biz;

	private static final Logger logger = LoggerFactory.getLogger(myPageController.class);

// myPage
	@RequestMapping("myPage")
	public String myPage(Model model, Authentication auth) {

		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("userInfo", dto);
		model.addAttribute("basketClass", biz.getPreBasketClass(dto.getUser_num()));
		model.addAttribute("subClass", biz.getPreSubscribe(dto.getUser_num()));
		model.addAttribute("subRoadmap", biz.getPreSubRoadmap(dto.getUser_num()));
		model.addAttribute("preQA", biz.getPreQA(dto.getUser_num()));

		if((auth.getAuthorities()+"").equals("[ROLE_TUTOR]")){
			model.addAttribute("myClass", biz.getPreMyClass(dto.getUser_num()));
		}
		
		return "Member/myPage";
	}
	
	@RequestMapping("listenClass")
	public String listenClass(Model model) {
		
		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("subClass", biz.getSubscribe(dto.getUser_num()));
		return "Member/listenClass";
	}
	
	@RequestMapping("subRoadmap")
	public String subRoadmap(Model model) {
		
		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("subRoadmap", biz.getSubRoadmap(dto.getUser_num()));
		
		return "Member/subRoadmap";
	}
	
	@RequestMapping("myQuestion")
	public String myQuestion(Model model) {
		
		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("myQA", biz.getQA(dto.getUser_num()));
		
		return "Member/myQuestion";
	}
	
	@RequestMapping("configProfile")
	public String configProfile(Model model) {
		
		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userInfo", dto);
		
		return "Member/configProfile";
	}
	
	@RequestMapping("reqLecturer")
	public String reqLecturer(Model model) {
		
		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userInfo", dto);
		
		return "Member/reqLecturer";
	}
	
	@RequestMapping("sendLecturerEmail")
	public String sendLecturerEmail(String lecturerNum, String lecturerPhone, String lecturerIntro, String lecturerClass) {
		
		int res = biz.reqLecturer(lecturerNum, lecturerPhone, lecturerIntro, lecturerClass);
		
		if(res>0) return "redirect:myPage";
		return "redirect:reqLecturer";
		
	}
	
	@RequestMapping("myRoadmap")
	public String myRoadmap(Authentication auth, Model model) {
		
		UserInfoDto userInfo = (UserInfoDto) auth.getPrincipal();
		model.addAttribute("myRoadmap", biz.myRoadmap(userInfo.getUser_num()));
		
		return "Member/myRoadmap";
	}
	
	@RequestMapping(value="updateNickname")
	@ResponseBody
	public boolean updateNickname(String nickname, Authentication auth) {
		
		UserInfoDto userInfo = (UserInfoDto) auth.getPrincipal();
		
		int res = biz.updateNickname(nickname, userInfo.getUser_num());
		
		if(res > 0) return true;
		return false;
	}
	
	@RequestMapping(value = "updateEmail", method=RequestMethod.POST)
	@ResponseBody
	public boolean updateEmail(String emailBefore, String emailAter, Authentication auth) {
		
		String email = emailBefore + "@" + emailAter;
		UserInfoDto userInfo = (UserInfoDto) auth.getPrincipal();
		
		int res = biz.updateEmail(email, userInfo.getUser_num());
		
		if(res > 0) return true;
		return false;
	}
	
	@RequestMapping(value = "beforePwChk", method=RequestMethod.POST)
	@ResponseBody
	public boolean beforePwChk(String beforePw, Authentication auth) {
		
		UserInfoDto userInfo = (UserInfoDto) auth.getPrincipal();
		
		return biz.checkPw(beforePw, userInfo.getUser_num());
	}
	
	@RequestMapping(value = "updatePw", method=RequestMethod.POST)
	@ResponseBody
	public boolean updatePw(String afterPw, Authentication auth) {
		
		UserInfoDto userInfo = (UserInfoDto) auth.getPrincipal();
		
		int res = biz.updatePw(afterPw, userInfo.getUser_num());
		
		if(res > 0) return true;
		return false;
	}
	
// 강사
	
	@RequestMapping("setLiveSchedule")
	@ResponseBody
	public boolean setLiveSchedule(String setClass, String setTitle, String setDate, Authentication auth) {
		
		UserInfoDto userInfo = (UserInfoDto) auth.getPrincipal();
		
		int res = biz.setLiveSchedule(setClass, setTitle, setDate, userInfo.getUser_nickname());
		
		if(res > 0) return true;
		return false;
	}
	
	@RequestMapping("myClass")
	public String myClass(Authentication auth, Model model) {
		
		UserInfoDto userInfo = (UserInfoDto) auth.getPrincipal();
		
		model.addAttribute("myClass", biz.myClass(userInfo.getUser_num()));
		
		return "Member/myClass";
	}
	
	@RequestMapping("deleteClass")
	@ResponseBody
	public boolean deleteClass(String class_num) {
		
		int res = biz.deleteClass(class_num);
		
		if(res > 0) return true;
		return false;
	}
	
	@RequestMapping("deleteRoadmap")
	@ResponseBody
	public boolean deleteRoadmap(String roadmap_num) {
		
		int res = biz.deleteRoadmap(roadmap_num);
		
		if(res > 0) return true;
		return false;
	}
	
// 관리자	
	
	@RequestMapping("adminPage")
	public String adminPage(Model model) {
		
		model.addAttribute("userList", biz.getPreUserList());
		model.addAttribute("reqList", biz.getPreReqLecturer());
		return "Member/adminPage";
	}
	
	@RequestMapping("adminUserList")
	public String adminUserList(Model model) {
		
		model.addAttribute("userList", biz.getUserList());
		return "Member/adminUserList";
	}
	
	@RequestMapping("adminReqLecturer")
	public String adminReqLecturer(Model model) {
		
		model.addAttribute("reqList", biz.getReqLecturer());
		return "Member/adminReqLecturer";
	}
	
	@RequestMapping("acceptReq")
	@ResponseBody
	public boolean acceptReq(String user_num) {
		
		int res = biz.acceptReq(user_num);
		
		if(res>0) return true;
		return false;
	}
	
	@RequestMapping("deniReq")
	@ResponseBody
	public boolean deniReq(String user_num) {
		
		int res = biz.deniReq(user_num);
		
		if(res>0) return true;
		return false;
	}
	
	@RequestMapping("userEnabled")
	@ResponseBody
	public boolean userEnabled(String user_num) {
		
		int res = biz.userEnabled(user_num);
		
		if(res>0) return true;
		return false;
	}
	
	@RequestMapping("userDisabled")
	@ResponseBody
	public boolean userDisabled(String user_num) {
		
		int res = biz.userDisabled(user_num);
		
		if(res>0) return true;
		return false;
	}

}