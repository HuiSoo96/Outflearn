package com.outflearn.Outflearn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.outflearn.Outflearn.dto.ClassCategoryDto;
import com.outflearn.Outflearn.dto.ClassDataDto;
import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.ClassIntroduceDto;
import com.outflearn.Outflearn.dto.ClassReviewDto;
import com.outflearn.Outflearn.dto.MainStreamDto;
import com.outflearn.Outflearn.dto.QADto;
import com.outflearn.Outflearn.dto.SubStreamDto;
import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.model.biz.ClassDataBiz;
import com.outflearn.Outflearn.model.biz.RoadMapBiz;
import com.outflearn.Outflearn.service.Pagination;

@Controller
public class HomeController {

	@Autowired
	public ClassDataBiz biz;

	@Inject
	private RoadMapBiz Rbiz;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/")
	public String home(Model model) {

		model.addAttribute("SubCount", biz.SubCountSelectList());

		return "home";
	}

	@RequestMapping(value = "home")
	public String tohome(Model model) {

		model.addAttribute("SubCount", biz.SubCountSelectList());

		return "home";
	}

	@RequestMapping("basketDelete")
	public int basketDelete(@RequestParam(name = "class_num") int class_num) {
		logger.info("basketDelete");

		return biz.classBasketDelete(class_num);
	}

	// ----------------- LectureList ----------------- ??????

	// ?????? ?????? ??????
	@RequestMapping("LectureList")
	public String CLassSubName(Model model, int sub_num, String txt_search, String page, String searchOption) {
		logger.info("SubCategory");
		logger.info("txt?????????");

		// int totalCount = biz.selectTotalCount(txt_search);
		int totalCount = biz.selectTotalCountStream(txt_search, searchOption, sub_num);
		logger.info("sub num:" + sub_num);
		logger.info("???????????????:" + txt_search);
		logger.info("????????????:" + searchOption);
		logger.info("?????? ?????????:" + totalCount);

		int pageNum = (page == null) ? 1 : Integer.parseInt(page);

		Pagination pagination = new Pagination();

		// get????????? ????????????????????? ??????page??????, ?????? ????????? ??????
		pagination.setPageNo(pageNum);

		// ??? ???????????? ????????? ???????????? ??????
		pagination.setPageSize(9);
		pagination.setTotalCount(totalCount);

		// select????????? ????????? ??????
		pageNum = (pageNum - 1) * pagination.getPageSize();

		List<ClassInfoDto> list = biz.selectListPageStream(pageNum, pagination.getPageSize(), txt_search, searchOption,
				sub_num);

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		logger.info("???????????????:" + subStreamList.size());
		logger.info("???????????????:" + mainStreamList.size());

		model.addAttribute("classinfo", list);
		model.addAttribute("pagination", pagination);
		model.addAttribute("txt_search", txt_search);
		model.addAttribute("searchOption", searchOption);
		model.addAttribute("sub_num", sub_num);

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);
		// model.addAttribute("classinfo",biz.ClassSubName(sub_num));


		return "Class/LectureList";
	}

	// ?????? ???????????? ???????????? ?????? ?????? ????????????
	@RequestMapping("SubCategory")
	public String CLassSubName(Model model, int sub_num) {
		logger.info("SubCategory");

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);
		model.addAttribute("classinfo", biz.ClassSubName(sub_num));

		return "Class/LectureList";
	}

	// ?????? ?????? ??????
	@RequestMapping("/LectureDetail")
	public String LectureDetail(@ModelAttribute ClassInfoDto Dto, int class_num, Model model, HttpSession session, String page, String txt_search) {
		logger.info("/LectureDetail");

		SecurityContext securityContext = SecurityContextHolder.getContext();

		if (securityContext.getAuthentication().getPrincipal() == "anonymousUser") {
			String Unuser_nickname = (String) securityContext.getAuthentication().getPrincipal();

			session.setAttribute("info_num", class_num);
			model.addAttribute("class_num", class_num);

			model.addAttribute("user_nickname", Unuser_nickname);
			// ?????? ??????
			model.addAttribute("classinfo", biz.ClassInfoSelectOne(class_num));

			// ??????
			model.addAttribute("classReview", biz.ClassReviewSelectList(class_num));

			// ?????? ??????
			model.addAttribute("classIntroduce", biz.ClassIntroduceSelectList(class_num));

			// ?????? ?????????
			//model.addAttribute("classQuestion", biz.QASelectList(class_num));
			int totalCount = biz.selectTotalCountQA(txt_search, class_num);
			logger.info("???????????????:" + txt_search);
			logger.info("class_num:"+class_num);
			logger.info("" + totalCount);

			int pageNum = (page == null) ? 1 : Integer.parseInt(page);

			Pagination pagination = new Pagination();

			// get????????? ????????????????????? ??????page??????, ?????? ????????? ??????
			pagination.setPageNo(pageNum);

			// ??? ???????????? ????????? ???????????? ??????
			pagination.setPageSize(5);
			pagination.setTotalCount(totalCount);

			// select????????? ????????? ??????
			pageNum = (pageNum - 1) * pagination.getPageSize();

			
			List<QADto> list = biz.selectListPageQA(pageNum, pagination.getPageSize(), txt_search, class_num);

			model.addAttribute("classQuestion", list);
			model.addAttribute("pagination", pagination);
			model.addAttribute("txt_search", txt_search);
			model.addAttribute("class_num", class_num);
			// ??????, ??????
			List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
			List<SubStreamDto> subStreamList = Rbiz.subStreamList();

			model.addAttribute("mainList", mainStreamList);
			model.addAttribute("subList", subStreamList);

			return "Class/LectureDetail";
		} else {
			System.out.println("??????????????????");
			
			session.setAttribute("info_num", class_num);
			model.addAttribute("class_num", class_num);

			UserInfoDto userdto = (UserInfoDto) securityContext.getAuthentication().getPrincipal();
			String user_nickname = userdto.getUser_nickname();
			int user_num = userdto.getUser_num();
			model.addAttribute("user_nickname", user_nickname);
			model.addAttribute("user_num", user_num);

			// ?????? ??????
			model.addAttribute("classinfo", biz.ClassInfoSelectOne(class_num));

			// ??????
			model.addAttribute("classReview", biz.ClassReviewSelectList(class_num));

			// ?????? ??????
			model.addAttribute("classIntroduce", biz.ClassIntroduceSelectList(class_num));
			
		
			
			// ?????? ?????????
			//model.addAttribute("classQuestion", biz.QASelectList(class_num));
			int totalCount = biz.selectTotalCountQA(txt_search, class_num);

			int pageNum = (page == null) ? 1 : Integer.parseInt(page);

			Pagination pagination = new Pagination();

			// get????????? ????????????????????? ??????page??????, ?????? ????????? ??????
			pagination.setPageNo(pageNum);

			// ??? ???????????? ????????? ???????????? ??????
			pagination.setPageSize(5);
			pagination.setTotalCount(totalCount);

			// select????????? ????????? ??????
			pageNum = (pageNum - 1) * pagination.getPageSize();

			
			List<QADto> list = biz.selectListPageQA(pageNum, pagination.getPageSize(), txt_search, class_num);

			model.addAttribute("classQuestion", list);
			model.addAttribute("pagination", pagination);
			model.addAttribute("txt_search", txt_search);
			model.addAttribute("class_num", class_num);
			// ???????????? ?????? ?????????
			model.addAttribute("ReviewList", biz.ReviewList(class_num));

			// ???????????? ?????? ?????????
			model.addAttribute("QAList", biz.QAList(class_num));

			// ??????, ??????
			List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
			List<SubStreamDto> subStreamList = Rbiz.subStreamList();

			model.addAttribute("mainList", mainStreamList);
			model.addAttribute("subList", subStreamList);

			// ????????? ????????? ????????? ????????????
			int res = biz.ClassBuyAfter(class_num, user_num);
			
			// ?????? ??????
			List <ClassDataDto> Clist = biz.ClassDataSelectOne(class_num);
			model.addAttribute("classdata", Clist);
			
			if (res > 0) {
				model.addAttribute("ClassBuyAfter", res);
				System.out.println(res + "?????????");
			}

			return "Class/LectureDetail";
		}

		
	}

	// ???????????? ??????

	// ----------------- LectureList ----------------- ???

	// ----------------- LectureDetail ----------------- ??????

	// ???????????? ?????? ??????, ???????????? ??????
	@RequestMapping("basket")
	@ResponseBody
	public int basket(@ModelAttribute ClassInfoDto dto, Model model, int class_num, Authentication auth) {
		logger.info("basket");

		// ?????????
		// ?????? ??????
		UserInfoDto uDto = (UserInfoDto) auth.getPrincipal();
		String user_nickname = uDto.getUser_nickname();
		int user_num = uDto.getUser_num();
		model.addAttribute("user_nickname", user_nickname);

		// ?????? ??????
		model.addAttribute("classinfo", biz.ClassInfoSelectOne(class_num));

		// ??????
		model.addAttribute("classReview", biz.ClassReviewSelectList(class_num));

		// ?????? ??????
		model.addAttribute("classIntroduce", biz.ClassIntroduceSelectList(class_num));

		// ?????? ?????????
		model.addAttribute("classQuestion", biz.QASelectList(class_num));

		dto.setUser_num(uDto.getUser_num());
		model.addAttribute("classInfoUser", biz.classInfoSelectListUser(user_num));

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		// ???????????? ??????
		model.addAttribute("classNum", dto.getClass_num());

		return biz.classBasketInsert(dto);
	}

	// ?????? ?????? ???????????? ????????? ????????????.
	@RequestMapping("basketSelect")
	public String basketSelect(@ModelAttribute ClassInfoDto dto, Model model, Authentication auth) {
		logger.info("basket");

		UserInfoDto uDto = (UserInfoDto) auth.getPrincipal();
		int user_num = uDto.getUser_num();

		dto.setUser_num(uDto.getUser_num());
		model.addAttribute("classInfoUser", biz.classInfoSelectListUser(user_num));

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		return "Class/ClassBasket";
	}

	// ----------------- LectureDetail ----------------- ???

	// ----------------- CLass ----------------- ??????
	@RequestMapping("ClassInfoInsertForm")
	public String ClassInfoInsertForm(Authentication auth, Model model) {
		logger.info("ClassInfoInsertForm");

		// ?????? ??????
		UserInfoDto uDto = (UserInfoDto) auth.getPrincipal();
		String user_nickname = uDto.getUser_nickname();
		int user_num = (Integer) uDto.getUser_num();
		model.addAttribute("user_nickname", user_nickname);
		model.addAttribute("user_num", user_num);

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		return "Class/ClassInfoInsertForm";
	}

	// ClassInfoInsertForm.jsp - > ClassIntroduceInsertForm.jsp CLASS_DATA DB ??????
	@RequestMapping("ClassIntroduceInsertForm")
	public String ClassIntroduceInsertForm(MultipartHttpServletRequest mtfRequest, @ModelAttribute ClassInfoDto dto,
			int main_num, int sub_num, Model model) {
		logger.info("ClassIntroduceInsertForm");
		
		// ?????? ?????????
		List<MultipartFile> fileList = mtfRequest.getFiles("file");

		String path = mtfRequest.getSession().getServletContext().getRealPath("resources/uploadImage");
		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); // ?????? ?????? ???
			long fileSize = mf.getSize(); // ?????? ?????????
			String class_img_path = path + "/" + originFileName; // ??????
			String class_img = originFileName; // ?????? ??????
			dto.setClass_img(class_img);
			int res = 0;
			try {
				mf.transferTo(new File(class_img_path)); // ?????? ???????????????

	            res = biz.ClassInfoInsert(dto);
			} catch (IllegalStateException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		// ??????, ??????
		ClassCategoryDto cDto = new ClassCategoryDto();
		int cRes = biz.ClassCategoryInsert(main_num, sub_num);

		return "Class/ClassIntroduceInsertForm";
	}

	// ????????????
	@ResponseBody
	@RequestMapping("ClassCategory")
	public List<SubStreamDto> ClassCategory(int main_num) {
		logger.info("ClassCategory");

		return biz.MainStreamSelectOne(main_num);
	}

	@RequestMapping("DataVideoUploadForm")
	public String DataVideoUploadForm(@ModelAttribute ClassIntroduceDto dto, Model model) {
		logger.info("DataVideoUploadForm");
		

		int res = biz.ClassIntroduceInsert(dto);
		

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);
	
		
		if (res > 0) {
			return "Class/DataVideoUploadForm";
		} else {
			return "Class/DataVideoUploadForm";
		}

	}

	// ???????????? ?????? ?????? DataVideoUploadForm.jsp
	@RequestMapping("DataVideoUpload")
	public String DataVideoUpload(MultipartHttpServletRequest mtfRequest, @ModelAttribute ClassDataDto dto, Model model)
			throws FileNotFoundException {
		logger.info("DataVideoUpload");
		
		
		if (dto.getData_data() == null) {
			List<MultipartFile> fileList = mtfRequest.getFiles("file");
			String path = mtfRequest.getSession().getServletContext().getRealPath("resources/uploadImage");
			File dir = new File(path);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			for (MultipartFile mf : fileList) {
				String originFileName = mf.getOriginalFilename(); // ?????? ?????? ???
				long fileSize = mf.getSize(); // ?????? ?????????
				String class_img_path = path + "/" + originFileName; // ??????
				String class_img = path + originFileName; // ?????? ??????
				dto.setData_data(class_img);
				System.out.println(fileSize);
				System.out.println(class_img_path);
				System.out.println(class_img);

				try {
					mf.transferTo(new File(class_img_path)); // ?????? ???????????????

				} catch (IllegalStateException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		} else {
			if (dto.getData_data().substring(0, 5).equals("https")) {

				String a = dto.getData_data();
				String b = "";
				System.out.println(a);
				if (a.contains("v=")) {
					b = a.split("\\?")[1];
					if (b.contains("&")) {
						b = b.substring(0, b.indexOf("&"));
					}
					System.out.println(b);
				} else if (a.contains("list=")) {
					b = a.split("\\?")[1];
					if (b.contains("&")) {
						b = b.substring(0, b.indexOf("&"));
					}
					System.out.println(b);
				}

				dto.setData_data(b);

			}

		}

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		int res = biz.ClassDataInsert(dto);

		if (res > 0) {
			return "Class/DataVideoUploadFormPlus";
		} else {
			return "Class/DataVideoUploadFormPlus";
		}
	}

	// DataVideoUploadFormPlus - > DataVideoUploadFormPlus ??? ????????? ?????? ??????
	@RequestMapping("DataVideoUploadPlus")
	public String DataVideoUploadPlus(MultipartHttpServletRequest mtfRequest, @ModelAttribute ClassDataDto dto,
			Model model) throws FileNotFoundException {
		logger.info("DataVideoUploadPlus");
	
		
		if (dto.getData_data() == null) {
			List<MultipartFile> fileList = mtfRequest.getFiles("file");

			String path = mtfRequest.getSession().getServletContext().getRealPath("resources/uploadImage");
			File dir = new File(path);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			for (MultipartFile mf : fileList) {
				String originFileName = mf.getOriginalFilename(); // ?????? ?????? ???
				long fileSize = mf.getSize(); // ?????? ?????????
				String data_data_path = path + "/" + originFileName; // ??????
				System.out.println("?????? " + data_data_path);
				String data_data = path + originFileName; // ?????? ??????
				dto.setData_data(data_data);

				try {
					mf.transferTo(new File(data_data_path)); // ?????? ???????????????

				} catch (IllegalStateException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		} 
		
		else {
			if (dto.getData_data().substring(0, 5).equals("https")) {

				String a = dto.getData_data();
				String b = "";

				if (a.contains("v=")) {
					b = a.split("\\?")[1];
					if (b.contains("&")) {
						b = b.substring(0, b.indexOf("&"));
					}
					System.out.println(b);
				} else if (a.contains("list=")) {
					b = a.split("\\?")[1];
					if (b.contains("&")) {
						b = b.substring(0, b.indexOf("&"));
					}
					System.out.println(b);
				}

				dto.setData_data(b);

			}
		}

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		int res = biz.ClassChapterDataInsert(dto);

		if (res > 0) {
			return "Class/DataVideoUploadFormPlus";
		} else {
			return "Class/DataVideoUploadFormPlus";
		}
	}

	// ?????? ?????? ????????? ??????
	@RequestMapping("ClassIntroduceGuideLine")
	public String ClassIntroduceGuideLine() {

		return "Class/ClassIntroduceGuideLine";
	}

	@RequestMapping("DataVideoGuideLine")
	public String DataVideoGuideLine() {

		return "Class//DataVideoGuideLine";
	}

	// ----------------- CLass ----------------- ???

	// ----------------- Basket ----------------- ??????

	// ???????????? ?????? ?????? ??????
	@ResponseBody
	@RequestMapping("basketDeleteOne")
	public int basketDeleteOne(int class_num) {
		logger.info("basketDelete");

		return biz.classBasketDeleteOne(class_num);
	}

	// ----------------- Basket ----------------- ???

	@RequestMapping("DetailDashBoard")
	@ResponseBody
	public String[] DetailDashBoard(Model model, HttpSession session) {

		int info_num = (int) session.getAttribute("info_num");

		List<ClassDataDto> dto = biz.ClassDataSelectOne(info_num);

		String[] array = new String[dto.size()];

		int size = 0;

		for (ClassDataDto temp : dto) {

			array[size++] = temp.getData_data();
			System.out.println(temp.getData_data() + " : controller");

		}

		return array;
	}

	@RequestMapping("Livepage")
	public String Livepage() {

		return "Live/Livepage";
	}

	@RequestMapping("LectureDetailView")
	public String LectureDetailView(String DATA_DATA, Model model, HttpSession session) {

		int info_num = (int)session.getAttribute("info_num");
		
		model.addAttribute("DATA_DATA", DATA_DATA);
		model.addAttribute("classInfo", biz.ClassInfoSelectOne(info_num));

		return "Class/LectureDetailView";
	}
	

	@RequestMapping("LecturePlayList")
	@ResponseBody
	public String[] LecturePlayList(Model model, HttpSession session) {

		int info_num = (int) session.getAttribute("info_num");

		List<ClassDataDto> data_dto = biz.ClassDataSelectOne(info_num);
		model.addAttribute("info_dto", biz.ClassInfoSelectOne(info_num));

		String[] array = new String[data_dto.size()];

		int size = 0;

		for (ClassDataDto temp : data_dto) {
			array[size++] = temp.getData_data();
		}

		System.out.println(array[0]);

		return array;
	}

	@RequestMapping("introOutflearn")
	public String introOutflearn() {
		return "introOutflearn";
	}
	
	// ????????????????????? ?????? ?????? ??????????????? ??????
	@RequestMapping("ClassIntroduceUpdateForm")
	public String ClassIntroduceUpdateForm(int class_num, Model model) {
		
		
		model.addAttribute("class_num", class_num);
		model.addAttribute("class_content", biz.ClassIntroduceSelectOne(class_num));
		
		return "Class/ClassIntroduceInsertFormUpdate";
	}
	
	// ?????? ?????? ?????? ??????
	@RequestMapping("ClassIntroduceUpdate")
	@ResponseBody
	public int ClassIntroduceUpdate(int class_num, String class_content) {
	
		return biz.ClassIntroduceUpdate(class_num, class_content);
	}
	
	// ????????????????????? ?????? ?????? ????????? ??????
	@RequestMapping("ClassDataInsertPlus")
	public String ClassDataInsertPlus(int class_num, Model model) {
		
		model.addAttribute("class_num", class_num);
		
	return "Class/DataVideoUploadChapterPlus";
	}
	
	// ?????? ?????? ?????? ??????????????? ??????
	@RequestMapping("DataVideoChapterInsert")
	public String DataVideoChapterInsert(MultipartHttpServletRequest mtfRequest, @ModelAttribute ClassDataDto dto,
			Model model, int class_num) throws FileNotFoundException {
		logger.info("DataVideoChapterInsert");
		System.out.println("class_num");
		model.addAttribute("class_num", class_num);
		
		if (dto.getData_data() == null) {
			List<MultipartFile> fileList = mtfRequest.getFiles("file");

			String path = mtfRequest.getSession().getServletContext().getRealPath("resources/uploadImage");
			File dir = new File(path);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			for (MultipartFile mf : fileList) {
				String originFileName = mf.getOriginalFilename(); // ?????? ?????? ???
				long fileSize = mf.getSize(); // ?????? ?????????
				String data_data_path = path + "/" + originFileName; // ??????
				System.out.println("?????? " + data_data_path);
				String data_data = path + originFileName; // ?????? ??????
				dto.setData_data(data_data);

				try {
					mf.transferTo(new File(data_data_path));

				} catch (IllegalStateException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		} 
		
		else {
			if (dto.getData_data().substring(0, 5).equals("https")) {

				String a = dto.getData_data();
				String b = "";

				if (a.contains("v=")) {
					b = a.split("\\?")[1];
					if (b.contains("&")) {
						b = b.substring(0, b.indexOf("&"));
					}
					System.out.println(b);
				} else if (a.contains("list=")) {
					b = a.split("\\?")[1];
					if (b.contains("&")) {
						b = b.substring(0, b.indexOf("&"));
					}
					System.out.println(b);
					
				}

				dto.setData_data(b);

			}
		}

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);
		
		
		
		int res = biz.ClassDataInsertPlus(dto);
		
		if (res > 0) {
			return "Class/DataVideoUploadChapterSecondPlus";
		} else {
			return "Class/DataVideoUploadChapterSecondPlus";
		}
	}
	
	@RequestMapping("DataVideoUploadUpdate")
	public String DataVideoUploadUpdate(@ModelAttribute ClassDataDto dto) {
		int res = 0;
		
		res = biz.DataVideoUploadUpdate(dto);
		
		System.out.println("??????");
		
		return "Member/myPage";
	}
	
	// ????????????????????? ?????? ?????? ?????? ???????????? ??????
	@RequestMapping("ClassDataUpdateForm")
		public String ClassDataUpdate(int class_num, Model model) {
			
			model.addAttribute("class_num", class_num);
			model.addAttribute("class_data", biz.ClassDataSelectOne(class_num));
			
			return "Class/DataVideoUploadFormUpdate";
		}
	// ????????????????????? ?????? ???????????? chapter?????? ????????? ??????
	@RequestMapping("DataVideoUploadSecondPlus")
	public String DataVideoUploadSecondPlus(MultipartHttpServletRequest mtfRequest, @ModelAttribute ClassDataDto dto,
			Model model, int class_num) throws FileNotFoundException {
		logger.info("DataVideoChapterInsert");
	
		
		if (dto.getData_data() == null) {
			List<MultipartFile> fileList = mtfRequest.getFiles("file");

			String path = mtfRequest.getSession().getServletContext().getRealPath("resources/uploadImage");
			File dir = new File(path);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			for (MultipartFile mf : fileList) {
				String originFileName = mf.getOriginalFilename(); // ?????? ?????? ???
				long fileSize = mf.getSize(); // ?????? ?????????
				String data_data_path = path + "/" + originFileName; // ??????
				System.out.println("?????? " + data_data_path);
				String data_data = path + originFileName; // ?????? ??????
				dto.setData_data(data_data);

				try {
					mf.transferTo(new File(data_data_path));

				} catch (IllegalStateException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		} 
		
		else {
			if (dto.getData_data().substring(0, 5).equals("https")) {

				String a = dto.getData_data();
				String b = "";

				if (a.contains("v=")) {
					b = a.split("\\?")[1];
					if (b.contains("&")) {
						b = b.substring(0, b.indexOf("&"));
					}
					System.out.println(b);
				} else if (a.contains("list=")) {
					b = a.split("\\?")[1];
					if (b.contains("&")) {
						b = b.substring(0, b.indexOf("&"));
					}
					System.out.println(b);
					
				}

				dto.setData_data(b);

			}
		}

		// ??????, ??????
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);
		
		
		System.out.println(class_num);
		model.addAttribute("class_num", class_num);
		dto.setClass_num(class_num);
		int res = biz.DataVideoSecondInsertPlus(dto);
		

		if (res > 0) {
			return "Class/DataVideoUploadChapterSecondPlus";
		} else {
			return "Class/DataVideoUploadChapterSecondPlus";
		}
	}
	

}