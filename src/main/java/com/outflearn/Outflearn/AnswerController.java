package com.outflearn.Outflearn;

import java.util.HashMap;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.outflearn.Outflearn.dto.ClassReviewDto;
import com.outflearn.Outflearn.dto.QADto;
import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.model.biz.ClassDataBiz;

@Controller
public class AnswerController {
	
	@Autowired
	public ClassDataBiz biz;
	

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//  댓글 입력
	@RequestMapping("LectureDetailAnswer")
	   public String LectureDetailAnswer(@ModelAttribute ClassReviewDto dto, int class_num, Model model, Authentication auth) {
	      logger.info("LectureDetailAnswer");

	      // 회원 정보 - 닉네임, 유저 번호
	      UserInfoDto uDto = (UserInfoDto) auth.getPrincipal();
	      String user_nickname = uDto.getUser_nickname();
	      int user_num = (Integer) uDto.getUser_num();
	      model.addAttribute("user_nickname", user_nickname);
	      model.addAttribute("user_num", user_num);
	      
	      int res = biz.ClassReviewInsert(dto);

	      if (res > 0) {
	         return "redirect:LectureDetail?class_num=" + class_num;
	      } else {
	         return "redirect:LectureDetail?class_num=" + class_num;
	      }

	   }
	
	
	@RequestMapping("ReviewDelete")
	public String ReviewDelete(@ModelAttribute ClassReviewDto dto) {
		logger.info("ReviewDelete");
		
		int res =  biz.ClassReviewDelete(dto);
		
		if (res > 0) {
			return "redirect:LectureDetail?class_num=" + dto.getClass_num();
		} else {
			return "redirect:LectureDetail?class_num=" + dto.getClass_num();
		}
	}
	
	@RequestMapping("AnswerUpdate")
	public String AnswerUpdate(@ModelAttribute ClassReviewDto dto) {
		
		int res = biz.ClassReviewUpdate(dto);
		
		if (res > 0) {
			return "redirect:LectureDetail?class_num=" + dto.getClass_num();
		}else {
			return "redirect:LectureDetail?class_num=" + dto.getClass_num();
		}
	}
	
//	대댓글
	@RequestMapping("Reply")
	public String Reply(@ModelAttribute ClassReviewDto dto , Model model) {
		logger.info("Reply");
		
		model.addAttribute("ReviewReply", biz.ClassReviewInsertAnswer(dto));
		
		return "redirect: LectureDetail?class_num=" + dto.getClass_num();
	}
	
	@RequestMapping("ReviewReplyUpdate")
	public String ReviewReplyUpdate(@ModelAttribute ClassReviewDto dto) {
		
		int res = biz.ClassReviewReplyUpdate(dto);
		
		if (res > 0) {
			return "redirect:LectureDetail?class_num=" + dto.getClass_num();
		}else {
			return "redirect:LectureDetail?class_num=" + dto.getClass_num();
		}
	}
	
	@RequestMapping("ReviewReplyDelete")
	public String ReviewReplyDelete(int review_num, int class_num) {
		
		int res = biz.ClassReviewReplyDelete(review_num);
		
		if (res > 0) {
			return "redirect:LectureDetail?class_num=" + class_num;
		}else {
			return "redirect:LectureDetail?class_num=" + class_num;
		}
	}
	
	@RequestMapping("QuestionInsert")
	public String QuestionInsert(@ModelAttribute QADto dto, Model model) {
		
		logger.info("QuestionInsert");
		
		int res = biz.QAInsert(dto);
		
		if(res > 0) {
			return "redirect: LectureDetail?class_num=" + dto.getClass_num();
		} else {
			return "redirect: LectureDetail?class_num=" + dto.getClass_num();
		}
		
	}
	
	@RequestMapping("QASelectOne")
	public String QASelectOne(int qa_num, int qa_group_no, Model model, Authentication auth) {
		
		model.addAttribute("classQuestion", biz.QASelectOne(qa_num));
		model.addAttribute("classQuestionReply", biz.QAReply(qa_group_no));
		
		return "Class/QASelectOne";
	}
	
	@RequestMapping("QAReplyInsert")
	public String QAReplyInsert(@ModelAttribute QADto dto, int class_num, Model model) {
		
		int res = biz.QAReplyInsert(dto);
		
		if(res > 0) {
			return "redirect: QASelectOne?qa_num=" + dto.getQa_num() + "&qa_group_no=" + dto.getQa_group_no();
		} else {
			return "redirect: QASelectOne?qa_num=" + dto.getQa_num() + "&qa_group_no=" + dto.getQa_group_no();
		}
		
	}
	
	@RequestMapping("QAReplyUpdate")
	public String QAReplyUpdate(@ModelAttribute QADto dto) {
		
		System.out.println(dto.getQa_content() + " : controller");
		System.out.println(dto.getQa_group_no() + " : qano");
		System.out.println(dto.getQa_num() + " : qanum");
		
		
		int res = biz.QAReplyUpdate(dto);
		
		if(res > 0) {
			return "redirect: QASelectOne?qa_num=" + dto.getQa_num() + "&qa_group_no=" + dto.getQa_group_no();
		} else {
			return "redirect: QASelectOne?qa_num=" + dto.getQa_num() + "&qa_group_no=" + dto.getQa_group_no();
		}
	}
	
	@RequestMapping("QAReplyDelete")
	public String QAReplyDelete(@ModelAttribute QADto dto) {
		
		int res = biz.QAReplyDelete(dto);
		
		if(res > 0) {
			return "redirect: QASelectOne?qa_num=" + dto.getQa_num() + "&qa_group_no=" + dto.getQa_group_no();
		} else {
			return "redirect: QASelectOne?qa_num=" + dto.getQa_num() + "&qa_group_no=" + dto.getQa_group_no();
		}
	}
	
}
