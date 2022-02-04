package com.outflearn.Outflearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.LiveDto;
import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.model.biz.LiveBiz;

@Controller
public class LiveController {

	@Autowired
	public LiveBiz biz;

	private static final Logger logger = LoggerFactory.getLogger(LiveController.class);

// Live
	@RequestMapping("liveCalendar")
	@ResponseBody
	public List<LiveDto> liveCalendar() {

		return biz.liveCalendar();
	}

	@RequestMapping("livePopup")
	@ResponseBody
	public ClassInfoDto livePopup(int live_num) {
		return biz.livePopup(live_num);
	}

	@RequestMapping("casterRoom")
	public String casterRoom(Authentication auth, Model model, String room) {
		
		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("userInfo", dto);
		model.addAttribute("room", biz.getClassInfo(room));
		
		return "Live/casterRoom";
	}

	@RequestMapping("getMyClass")
	@ResponseBody
	public List<ClassInfoDto> getMyClass(Authentication auth) {
		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return biz.getMyClass(dto.getUser_num());
	}
	
	@RequestMapping("liveRooms")
	@ResponseBody
	public List<ClassInfoDto> liveRooms(String[] liveRooms){
		
		return biz.liveRooms(liveRooms);
	}
	
	@RequestMapping("joinLive")
	public String joinLive(Model model, String room, Authentication auth) {
		
		UserInfoDto dto = (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ClassInfoDto liveClass = biz.getClassInfo(room);
		
		model.addAttribute("userInfo", dto);
		model.addAttribute("room", liveClass);
		model.addAttribute("casterClassList", biz.getMyClass(liveClass.getUser_num()));
		
		
		return "Live/showLive";
	}

}
