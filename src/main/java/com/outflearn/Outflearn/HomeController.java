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

	// ----------------- LectureList ----------------- 시작

	// 모든 강의 보기
	@RequestMapping("LectureList")
	public String CLassSubName(Model model, int sub_num, String txt_search, String page, String searchOption) {
		logger.info("SubCategory");
		logger.info("txt서치전");

		// int totalCount = biz.selectTotalCount(txt_search);
		int totalCount = biz.selectTotalCountStream(txt_search, searchOption, sub_num);
		logger.info("sub num:" + sub_num);
		logger.info("텍스트서치:" + txt_search);
		logger.info("서치옵션:" + searchOption);
		logger.info("찾은 강좌수:" + totalCount);

		int pageNum = (page == null) ? 1 : Integer.parseInt(page);

		Pagination pagination = new Pagination();

		// get방식의 파라미터값으로 받은page변수, 현재 페이지 번호
		pagination.setPageNo(pageNum);

		// 한 페이지에 나오는 게시물의 개수
		pagination.setPageSize(9);
		pagination.setTotalCount(totalCount);

		// select해오는 기준을 구함
		pageNum = (pageNum - 1) * pagination.getPageSize();

		List<ClassInfoDto> list = biz.selectListPageStream(pageNum, pagination.getPageSize(), txt_search, searchOption,
				sub_num);

		// 부류, 주류
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		logger.info("서브리스트:" + subStreamList.size());
		logger.info("메인리스트:" + mainStreamList.size());

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

	// 부류 카테고리 클릭하면 해당 강의 보여주기
	@RequestMapping("SubCategory")
	public String CLassSubName(Model model, int sub_num) {
		logger.info("SubCategory");

		// 부류, 주류
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);
		model.addAttribute("classinfo", biz.ClassSubName(sub_num));

		return "Class/LectureList";
	}

	// 해당 강의 보기
	@RequestMapping("/LectureDetail")
	public String LectureDetail(@ModelAttribute ClassInfoDto Dto, int class_num, Model model, HttpSession session, String page, String txt_search) {
		logger.info("/LectureDetail");

		SecurityContext securityContext = SecurityContextHolder.getContext();

		if (securityContext.getAuthentication().getPrincipal() == "anonymousUser") {
			String Unuser_nickname = (String) securityContext.getAuthentication().getPrincipal();

			session.setAttribute("info_num", class_num);
			model.addAttribute("class_num", class_num);

			model.addAttribute("user_nickname", Unuser_nickname);
			// 강좌 소개
			model.addAttribute("classinfo", biz.ClassInfoSelectOne(class_num));

			// 댓글
			model.addAttribute("classReview", biz.ClassReviewSelectList(class_num));

			// 강의 소개
			model.addAttribute("classIntroduce", biz.ClassIntroduceSelectList(class_num));

			// 질문 리스트
			//model.addAttribute("classQuestion", biz.QASelectList(class_num));
			int totalCount = biz.selectTotalCountQA(txt_search, class_num);
			logger.info("텍스트서치:" + txt_search);
			logger.info("class_num:"+class_num);
			logger.info("" + totalCount);

			int pageNum = (page == null) ? 1 : Integer.parseInt(page);

			Pagination pagination = new Pagination();

			// get방식의 파라미터값으로 받은page변수, 현재 페이지 번호
			pagination.setPageNo(pageNum);

			// 한 페이지에 나오는 게시물의 개수
			pagination.setPageSize(5);
			pagination.setTotalCount(totalCount);

			// select해오는 기준을 구함
			pageNum = (pageNum - 1) * pagination.getPageSize();

			
			List<QADto> list = biz.selectListPageQA(pageNum, pagination.getPageSize(), txt_search, class_num);

			model.addAttribute("classQuestion", list);
			model.addAttribute("pagination", pagination);
			model.addAttribute("txt_search", txt_search);
			model.addAttribute("class_num", class_num);
			// 부류, 주류
			List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
			List<SubStreamDto> subStreamList = Rbiz.subStreamList();

			model.addAttribute("mainList", mainStreamList);
			model.addAttribute("subList", subStreamList);

			return "Class/LectureDetail";
		} else {
			System.out.println("로그인된유저");
			
			session.setAttribute("info_num", class_num);
			model.addAttribute("class_num", class_num);

			UserInfoDto userdto = (UserInfoDto) securityContext.getAuthentication().getPrincipal();
			String user_nickname = userdto.getUser_nickname();
			int user_num = userdto.getUser_num();
			model.addAttribute("user_nickname", user_nickname);
			model.addAttribute("user_num", user_num);

			// 강좌 소개
			model.addAttribute("classinfo", biz.ClassInfoSelectOne(class_num));

			// 댓글
			model.addAttribute("classReview", biz.ClassReviewSelectList(class_num));

			// 강의 소개
			model.addAttribute("classIntroduce", biz.ClassIntroduceSelectList(class_num));
			
		
			
			// 질문 리스트
			//model.addAttribute("classQuestion", biz.QASelectList(class_num));
			int totalCount = biz.selectTotalCountQA(txt_search, class_num);

			int pageNum = (page == null) ? 1 : Integer.parseInt(page);

			Pagination pagination = new Pagination();

			// get방식의 파라미터값으로 받은page변수, 현재 페이지 번호
			pagination.setPageNo(pageNum);

			// 한 페이지에 나오는 게시물의 개수
			pagination.setPageSize(5);
			pagination.setTotalCount(totalCount);

			// select해오는 기준을 구함
			pageNum = (pageNum - 1) * pagination.getPageSize();

			
			List<QADto> list = biz.selectListPageQA(pageNum, pagination.getPageSize(), txt_search, class_num);

			model.addAttribute("classQuestion", list);
			model.addAttribute("pagination", pagination);
			model.addAttribute("txt_search", txt_search);
			model.addAttribute("class_num", class_num);
			// 대쉬보드 리뷰 리스트
			model.addAttribute("ReviewList", biz.ReviewList(class_num));

			// 대쉬보드 질문 리스트
			model.addAttribute("QAList", biz.QAList(class_num));

			// 부류, 주류
			List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
			List<SubStreamDto> subStreamList = Rbiz.subStreamList();

			model.addAttribute("mainList", mainStreamList);
			model.addAttribute("subList", subStreamList);

			// 결제를 했으면 화면이 안나오게
			int res = biz.ClassBuyAfter(class_num, user_num);
			
			// 영상 소개
			List <ClassDataDto> Clist = biz.ClassDataSelectOne(class_num);
			model.addAttribute("classdata", Clist);
			
			if (res > 0) {
				model.addAttribute("ClassBuyAfter", res);
				System.out.println(res + "강선웅");
			}

			return "Class/LectureDetail";
		}

		
	}

	// 장바구니 담기

	// ----------------- LectureList ----------------- 끝

	// ----------------- LectureDetail ----------------- 시작

	// 장바구니 구매 유무, 장바구니 입력
	@RequestMapping("basket")
	@ResponseBody
	public int basket(@ModelAttribute ClassInfoDto dto, Model model, int class_num, Authentication auth) {
		logger.info("basket");

		// 닉네임
		// 회원 정보
		UserInfoDto uDto = (UserInfoDto) auth.getPrincipal();
		String user_nickname = uDto.getUser_nickname();
		int user_num = uDto.getUser_num();
		model.addAttribute("user_nickname", user_nickname);

		// 강좌 소개
		model.addAttribute("classinfo", biz.ClassInfoSelectOne(class_num));

		// 댓글
		model.addAttribute("classReview", biz.ClassReviewSelectList(class_num));

		// 강의 소개
		model.addAttribute("classIntroduce", biz.ClassIntroduceSelectList(class_num));

		// 질문 리스트
		model.addAttribute("classQuestion", biz.QASelectList(class_num));

		dto.setUser_num(uDto.getUser_num());
		model.addAttribute("classInfoUser", biz.classInfoSelectListUser(user_num));

		// 부류, 주류
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		// 장바구니 담기
		model.addAttribute("classNum", dto.getClass_num());

		return biz.classBasketInsert(dto);
	}

	// 해당 유저 장바구니 목록을 보여준다.
	@RequestMapping("basketSelect")
	public String basketSelect(@ModelAttribute ClassInfoDto dto, Model model, Authentication auth) {
		logger.info("basket");

		UserInfoDto uDto = (UserInfoDto) auth.getPrincipal();
		int user_num = uDto.getUser_num();

		dto.setUser_num(uDto.getUser_num());
		model.addAttribute("classInfoUser", biz.classInfoSelectListUser(user_num));

		// 부류, 주류
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		return "Class/ClassBasket";
	}

	// ----------------- LectureDetail ----------------- 끝

	// ----------------- CLass ----------------- 시작
	@RequestMapping("ClassInfoInsertForm")
	public String ClassInfoInsertForm(Authentication auth, Model model) {
		logger.info("ClassInfoInsertForm");

		// 회원 정보
		UserInfoDto uDto = (UserInfoDto) auth.getPrincipal();
		String user_nickname = uDto.getUser_nickname();
		int user_num = (Integer) uDto.getUser_num();
		model.addAttribute("user_nickname", user_nickname);
		model.addAttribute("user_num", user_num);

		// 부류, 주류
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		return "Class/ClassInfoInsertForm";
	}

	// ClassInfoInsertForm.jsp - > ClassIntroduceInsertForm.jsp CLASS_DATA DB 저장
	@RequestMapping("ClassIntroduceInsertForm")
	public String ClassIntroduceInsertForm(MultipartHttpServletRequest mtfRequest, @ModelAttribute ClassInfoDto dto,
			int main_num, int sub_num, Model model) {
		logger.info("ClassIntroduceInsertForm");
		
		// 파일 업로드
		List<MultipartFile> fileList = mtfRequest.getFiles("file");

		String path = mtfRequest.getSession().getServletContext().getRealPath("resources/uploadImage");
		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명
			long fileSize = mf.getSize(); // 파일 사이즈
			String class_img_path = path + "/" + originFileName; // 경로
			String class_img = originFileName; // 파일 이름
			dto.setClass_img(class_img);
			int res = 0;
			try {
				mf.transferTo(new File(class_img_path)); // 파일 집어넣는다

	            res = biz.ClassInfoInsert(dto);
			} catch (IllegalStateException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		// 부류, 주류
		List<MainStreamDto> mainStreamList = Rbiz.mainStreamList();
		List<SubStreamDto> subStreamList = Rbiz.subStreamList();

		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);

		// 주류, 부류
		ClassCategoryDto cDto = new ClassCategoryDto();
		int cRes = biz.ClassCategoryInsert(main_num, sub_num);

		return "Class/ClassIntroduceInsertForm";
	}

	// 카테고리
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
		

		// 부류, 주류
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

	// 영상소개 작성 확인 DataVideoUploadForm.jsp
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
				String originFileName = mf.getOriginalFilename(); // 원본 파일 명
				long fileSize = mf.getSize(); // 파일 사이즈
				String class_img_path = path + "/" + originFileName; // 경로
				String class_img = path + originFileName; // 파일 이름
				dto.setData_data(class_img);
				System.out.println(fileSize);
				System.out.println(class_img_path);
				System.out.println(class_img);

				try {
					mf.transferTo(new File(class_img_path)); // 파일 집어넣는다

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

		// 부류, 주류
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

	// DataVideoUploadFormPlus - > DataVideoUploadFormPlus 한 챕터에 영상 추가
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
				String originFileName = mf.getOriginalFilename(); // 원본 파일 명
				long fileSize = mf.getSize(); // 파일 사이즈
				String data_data_path = path + "/" + originFileName; // 경로
				System.out.println("경로 " + data_data_path);
				String data_data = path + originFileName; // 파일 이름
				dto.setData_data(data_data);

				try {
					mf.transferTo(new File(data_data_path)); // 파일 집어넣는다

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

		// 부류, 주류
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

	// 강의 소개 가이드 라인
	@RequestMapping("ClassIntroduceGuideLine")
	public String ClassIntroduceGuideLine() {

		return "Class/ClassIntroduceGuideLine";
	}

	@RequestMapping("DataVideoGuideLine")
	public String DataVideoGuideLine() {

		return "Class//DataVideoGuideLine";
	}

	// ----------------- CLass ----------------- 끝

	// ----------------- Basket ----------------- 시작

	// 장바구니 해당 강의 삭제
	@ResponseBody
	@RequestMapping("basketDeleteOne")
	public int basketDeleteOne(int class_num) {
		logger.info("basketDelete");

		return biz.classBasketDeleteOne(class_num);
	}

	// ----------------- Basket ----------------- 끝

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
	
	// 마이페이지에서 강의 소개 수정페이지 이동
	@RequestMapping("ClassIntroduceUpdateForm")
	public String ClassIntroduceUpdateForm(int class_num, Model model) {
		
		
		model.addAttribute("class_num", class_num);
		model.addAttribute("class_content", biz.ClassIntroduceSelectOne(class_num));
		
		return "Class/ClassIntroduceInsertFormUpdate";
	}
	
	// 강의 소개 수정 이동
	@RequestMapping("ClassIntroduceUpdate")
	@ResponseBody
	public int ClassIntroduceUpdate(int class_num, String class_content) {
	
		return biz.ClassIntroduceUpdate(class_num, class_content);
	}
	
	// 마이페이지에서 영상 추가 페이지 이동
	@RequestMapping("ClassDataInsertPlus")
	public String ClassDataInsertPlus(int class_num, Model model) {
		
		model.addAttribute("class_num", class_num);
		
	return "Class/DataVideoUploadChapterPlus";
	}
	
	// 챕터 영상 추가 페이지에서 추가
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
				String originFileName = mf.getOriginalFilename(); // 원본 파일 명
				long fileSize = mf.getSize(); // 파일 사이즈
				String data_data_path = path + "/" + originFileName; // 경로
				System.out.println("경로 " + data_data_path);
				String data_data = path + originFileName; // 파일 이름
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

		// 부류, 주류
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
		
		System.out.println("안녕");
		
		return "Member/myPage";
	}
	
	// 마이페이지에서 영상 추가 수정 페이지로 이동
	@RequestMapping("ClassDataUpdateForm")
		public String ClassDataUpdate(int class_num, Model model) {
			
			model.addAttribute("class_num", class_num);
			model.addAttribute("class_data", biz.ClassDataSelectOne(class_num));
			
			return "Class/DataVideoUploadFormUpdate";
		}
	// 마이페이지에서 영상 추가에서 chapter안에 영상들 제작
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
				String originFileName = mf.getOriginalFilename(); // 원본 파일 명
				long fileSize = mf.getSize(); // 파일 사이즈
				String data_data_path = path + "/" + originFileName; // 경로
				System.out.println("경로 " + data_data_path);
				String data_data = path + originFileName; // 파일 이름
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

		// 부류, 주류
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