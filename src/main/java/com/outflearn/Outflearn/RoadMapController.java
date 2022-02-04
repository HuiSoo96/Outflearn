package com.outflearn.Outflearn;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.CommentDto;
import com.outflearn.Outflearn.dto.MainStreamDto;
import com.outflearn.Outflearn.dto.RoadMapInfoDto;
import com.outflearn.Outflearn.dto.RoadUserCombineDto;
import com.outflearn.Outflearn.dto.SubStreamDto;
import com.outflearn.Outflearn.dto.UserInfoDto;
import com.outflearn.Outflearn.model.biz.RoadMapBiz;
import com.outflearn.Outflearn.service.Pagination;

@Controller
public class RoadMapController {
	
	private static final Logger logger  = LoggerFactory.getLogger(RoadMapController.class);
	
	@Inject
	private RoadMapBiz biz;	
	
	//로드맵 보기

	@RequestMapping("/RoadMap")
	public String roadMapPage(Model model, String txt_search, String page, int main_num, String searchOption) {
			logger.info("txt서치전");
			
			
			int totalCount = biz.selectTotalCountRoadMap(txt_search, searchOption, main_num);
			logger.info("검색어:"+txt_search);
			logger.info("검색옵션:"+searchOption);
			logger.info("컨트롤러 메인넘버:" + main_num);
			logger.info("찾은강좌:"+totalCount);
			
			int pageNum = (page==null)? 1:Integer.parseInt(page);
			
			Pagination pagination = new Pagination();
			
			//get방식의 파라미터값으로 받은page변수, 현재 페이지 번호
			pagination.setPageNo(pageNum);
			
			//한 페이지에 나오는 게시물의 개수 
			pagination.setPageSize(9);
			pagination.setTotalCount(totalCount);
			
			//select해오는 기준을 구함
			pageNum = (pageNum -1) * pagination.getPageSize();
			
			List<MainStreamDto> mainStreamList = biz.mainStreamList();
			List<RoadUserCombineDto> comList = biz.roadMapComList(pageNum, pagination.getPageSize(), txt_search, searchOption, main_num);
			

			model.addAttribute("mainList", mainStreamList);
			model.addAttribute("comList", comList);
			model.addAttribute("pagination", pagination);
			model.addAttribute("txt_search", txt_search);
			model.addAttribute("main_num", main_num);
			model.addAttribute("searchOption", searchOption);
		
						
		return"RoadMap/RoadMapList";
	}
	
	//로드맵 쓰기 페이지1
	@RequestMapping("/RoadMapWriteP1")
	public String roadMapWriteP1() {
		return "RoadMap/RoadMapWrite_p1";
	}
	
	//로드맵2 다이렉트
	@RequestMapping("/RoadMapWriteP2")
	public String RoadMapWriteP2() {
		return"RoadMap/RoadMapWrite_p2";
	}
	
	//로드맵 2p에서 1페이지수정버튼 누름 
	@RequestMapping("/RoadMapUpdate_p1")
	public String RoadMapUpdate_p1(@RequestParam String roadNum,Model model) {
		System.out.println("로드맵번호"+roadNum);
		//현재 시퀀스 번호가 아닌 roadnum번호와 roadInfoDto를 갖고 넘어가야함
		RoadMapInfoDto dto = biz.selectOneRoadMap(roadNum);	
		
		boolean Chk = true;
		
		model.addAttribute("Chk", Chk);
		model.addAttribute("dto", dto);

		
		return"RoadMap/RoadMapUpdate_p1";
	}
	
	//로드맵 1p수정 다이렉트
	@RequestMapping("/Modify_P1")
	public String Modify_P1(@RequestParam String roadNum,Model model) {
		System.out.println("로드맵번호=="+roadNum);
		
		RoadMapInfoDto dto = biz.selectOneRoadMap(roadNum);	
		
		boolean Chk = false;
		
		model.addAttribute("Chk", Chk);
		model.addAttribute("dto", dto);
		return"RoadMap/RoadMapUpdate_p1";

	}	
	
	//로드맵 2p수정 다이렉트
	@RequestMapping("/Modify_P2")
	public String Modify_P2(@RequestParam String roadNum,Model model) {
		System.out.println("로드맵 번호=="+roadNum);
		
		//roandmap_con에서 roadmap_num으로 classnum List 받아옴 
		List<Integer> classNumList = biz.RoadMapConList(roadNum);
		System.out.println("classNumList+++"+classNumList);
		System.out.println("classNumListSize=="+classNumList.size());
		
		//class_Num으로 class_infoList받아옴
		List<ClassInfoDto> resList = biz.RoadClassInfoList(classNumList);
		System.out.println("classInfoSize+++"+resList.size());
		
		
		model.addAttribute("roadSeq", roadNum);
		model.addAttribute("resList", resList);
		return "RoadMap/RoadMapUpdate_p2";
	}
	
	
	
	
	//로드맵 1p 수정 후 업데이트하고 2p가기거나 홈으로 가기	
	@RequestMapping("/roadUpdate")
	public String roadUpdate(@ModelAttribute RoadMapInfoDto dto, Model model, @RequestParam String chkVal) {
		int res = biz.roadMapUpdate(dto);
			
		
		if(res>0) {
			System.out.println("수정성공!!!");
		}		
		
		System.out.println("======"+chkVal);	
		
			
		if(chkVal.equals("submit")) { //로드맵 작성하다가 1p수정 눌렀을 때 리턴값
			int seq = dto.getRoadmap_num();
			model.addAttribute("roadSeq", seq);	
			return"RoadMap/RoadMapWrite_p2";
		}else{ 
			return"redirect:myRoadmap"; //로드맵1p 수정하기 다이렉트로 왔을 떄 (마이페이지에서 왔을 떄 리턴값)
		}
		
	}
	//roadmap2p수정
	@RequestMapping("/roadNclassUpdate")
	public String roadNclassUpdate(@RequestParam String[] class_num, @RequestParam String seq,@RequestParam String chkVal) {
		
		System.out.println("roadNclassUpdate 입장:::::::");
		System.out.println("roadmap 번호"+seq);	
		System.out.println("chkVal==="+chkVal);
		
		for(String res : class_num) {
			System.out.println("들어온classNum"+res);
		}	
		
		if(chkVal.equals("modify")) { //이미 값이 있었고 수정했을 때 : delete 후 insert하기
			
			int Delres = biz.DeleteroadConBeforeUpdate(seq);
			
			if(Delres>0) {
				System.out.println("삭제 성공");
				int res = biz.insertroadNclass(class_num, seq);
				
				if(res>0) {
					System.out.println("인서트 성공!");	
				}
			}
		}else {//처음 등록할 때 : insert만
			
			int res = biz.insertroadNclass(class_num, seq);
			
			if(res>0) {
				System.out.println("인서트 성공!");	
			}
		}
		
		
		return "redirect:myRoadmap";
	}
	
	
	
	
	//로드맵 1페이지 작성 후 insert
	@RequestMapping("/roadInsert")
	public String roadMapInsert(@ModelAttribute RoadMapInfoDto dto, Model model) {
		
		int res = 0;
		System.out.println(dto.getRoadmap_content());
		System.out.println(dto.getRoadmap_title());
		System.out.println(dto.getUser_num());
		
		res = biz.insertRoadMap(dto);	
		
		int seq = biz.selectSQ() ;
		
		System.out.println("시퀀스번호"+seq);
		
		//넘어갈때 시퀀스 번호까지 같이 넘어감
		model.addAttribute("roadSeq", seq);
		return"RoadMap/RoadMapWrite_p2";
	}
	
	//로드맵 2페이지 작성 후 insert
	@RequestMapping("/roadNclass")
	public String roadNclass(@RequestParam String[] class_num, @RequestParam String seq, Model model)  {
		
		System.out.println("roadNclass 입장:::::::");
		System.out.println("roadmap 번호"+seq);	
		
		for(String res : class_num) {
			System.out.println("들어온classNum"+res);
		}		
		
		int res = biz.insertroadNclass(class_num, seq);
		
		if(res>0) {
			System.out.println("인서트 성공!");	
		}
		
		model.addAttribute("roadNum", seq);
		return"redirect:roadMapDetail";
	}
	
	//검색창 띄우기
	@RequestMapping("/searchWingogo")
	public String searchWingogo(Model model, @RequestParam String btnIdVal, @RequestParam String[] numArray ) {
		
		System.out.println("searchWingogo컨트롤러!!");		
		
		String array = "";
		for(int i=0; i<numArray.length-1; i++) {
			array += numArray[i]+",";		
		}
		
		System.out.println("array==="+array);
		
		
		
		
		List<MainStreamDto> mainStreamList = biz.mainStreamList();		
 		List<SubStreamDto> subStreamList = biz.subStreamList();	

		//선택된 강의 리스트도 같이 넘어감
		//부모창으로 값 전달을 위해 부모창 번호와 같이 넘어옴(클릭한 버튼의 id값)		
		model.addAttribute("array", array);
		model.addAttribute("btnIdVal", btnIdVal);
		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);
		return"RoadMap/SearchForm";
	}
	
	//검색창에서 검색 후 값과 리턴
	@RequestMapping("/searchFilter")
	public String searchFilter(Model model, @RequestParam String[] subFilter, @RequestParam String please, @RequestParam String array) {
		
		System.out.println("들어온 필터 갯수"+subFilter.length);
		
		List<ClassInfoDto> resList = new ArrayList<ClassInfoDto>();
		resList = biz.classInfoList(subFilter);
		System.out.println("컨트롤러로 다시 컴백 :"+ resList.size());
		

		
		List<MainStreamDto> mainStreamList = biz.mainStreamList();		
		List<SubStreamDto> subStreamList = biz.subStreamList();	
		
		model.addAttribute("array", array);
		model.addAttribute("btnIdVal", please);
		model.addAttribute("mainList", mainStreamList);
		model.addAttribute("subList", subStreamList);
		model.addAttribute("resList", resList);		
		return"RoadMap/SearchForm";
	}
	
	//로드맵 보기	
	@RequestMapping("/roadMapDetail")
	public String roadMapDetail(@RequestParam String roadNum, Model model, ServletRequest request ) {
		System.out.println("로드맵번호 번호: "+ roadNum );
		//로드맵 번호로 로드맵 인포를 받아옴	
		RoadUserCombineDto dto = biz.roadMapComSelectOne(roadNum);		
		
		//roandmap_con에서 roadmap_num으로 classnum List 받아옴 
		List<Integer> classNumList = biz.RoadMapConList(roadNum);
		System.out.println("classNumList+++"+classNumList);
		
		List<ClassInfoDto> resList = new ArrayList<ClassInfoDto>();
		
		//강의가 있다면 classNumList로  info받아오기
		if(classNumList.size() > 0) {
			resList = biz.RoadClassInfoList(classNumList);
		}

		System.out.println("classInfoSize+++"+resList.size());
		
		//현재 주소 받기
		HttpServletRequest req = (HttpServletRequest)request;
		String Http =StringUtils.defaultString(req.getScheme());
		int serverPort = req.getServerPort();
		String serverName = StringUtils.defaultString(req.getServerName());
		String url = StringUtils.defaultString(req.getRequestURI().toString()); 
		String queryString = StringUtils.defaultString(req.getQueryString());   
		
		String URL = Http+"://"+serverName+":"+serverPort+url+"?"+queryString;
		System.out.println("로드맵페이지URL"+URL);			

		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		//로그인 구독 여부,장바구니여부 
		//1.로드맵 구독여부 판별(기본값 false)
		boolean roadChk = false;
		
	
		//2.장바구니
		//===> 1)outerJoin으로 basket과 subscribe의 리스트값을 받아오기
		//     2)classNum에서 위의 값 제거
		//     3)남은 번호의 classInfo 받아와서 넘기기
		List<ClassInfoDto> basketList = new ArrayList<ClassInfoDto>();
				
		if(securityContext.getAuthentication().getPrincipal() == "anonymousUser") { //로그인 안된 유저라면
			System.out.println("로그인안된유저");
			System.out.println(securityContext);
			System.out.println("로그인 안된 유저입니다 flase인가요?"+roadChk);
			
		}else {//로그인 된 유저라면 
			System.out.println("로그인된유저");
			UserInfoDto userdto = (UserInfoDto) securityContext.getAuthentication().getPrincipal();
			int userNum = userdto.getUser_num();
			//1.로드맵 구독여부 판별	
			int res = biz.roadJoinChk(roadNum, String.valueOf(userNum));
			if(res>0) {
				roadChk = true;
				System.out.println("구독유저입니다true인가요?"+roadChk);
				
				//1.user_num으로 subscribe의 class_num과 basket의 class_num을 가져옴->outerJoin사용
				List<Integer> SubBaList = new ArrayList<Integer>();
				SubBaList = biz.SubBaList(String.valueOf(userNum));
				System.out.println("SubBaList==="+SubBaList);
											
				//2.classNumlist에서 subBaList와 겹치는 부분을 삭제
				classNumList.removeAll(SubBaList);
				System.out.println("겹치는 부분 삭제하고 남은 classNumList==="+classNumList);
				
				//3.classNumList에   subBaList값을 제거하고도 남은 classNum이 있다면 그것의 정보를 가져온다				
				if(classNumList.size()>0) {
					basketList = biz.RoadClassInfoList(classNumList);
					System.out.println("basketList의 사이즈: "+basketList.size());
					
					//4.가격이0원이면 제외한다
					for(Iterator<ClassInfoDto> it = basketList.iterator(); it.hasNext(); ) {
						ClassInfoDto test = it.next();
						if(test.getClass_price()==0) {
							it.remove();
						}
					}

				}
				
			}else {

				System.out.println("구독하지않은 유저입니다 false인가요?"+roadChk);
			}
		}
		
		System.out.println("최종확인 roadChk==="+roadChk);
		System.out.println("최종확인 basketList==="+basketList);
		model.addAttribute("basketList", basketList); //장바구니 리스트
		model.addAttribute("roadChk", roadChk); //로드맵 구독 여부
		model.addAttribute("URL", URL); //현재 주소
		model.addAttribute("resList", resList);//classInfo
		model.addAttribute("roadMap", dto); //roadMapInfo
		return"RoadMap/RoadMapDetail";
	}
	

	@RequestMapping("/roadJoinChk")
	@ResponseBody
	public Map<String,Boolean> roadJoinChk(@RequestParam String roadnum, @RequestParam String usernum) {
		System.out.println("roadJoinChk+++");
			
		boolean roadChk = false;
		Map<String, Boolean> map = new HashMap<String,Boolean>();
				
		int res = biz.roadJoinChk(roadnum, usernum);
		System.out.println("res는!!!"+res);
		
		if(res>0) {
			roadChk = true;
			map.put("roadChk", roadChk);
		}else {
			roadChk = false;
			map.put("roadChk", roadChk);
		}
		
		System.out.println(roadChk);
		return map;
	}
	
	//로드맵 구독
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/roadMapJoin")	
	@ResponseBody
	public Map<String, Boolean> roadMapJoin(@RequestParam String roadnum, @RequestParam String usernum) {
		
	System.out.println("로드맵 구독 들어옴");
		
	boolean res = false;
	
	System.out.println("res+++"+res);
				
	int subRes = biz.roadMapSubscribeInsert(roadnum, usernum); //roadmap Subcribe테이블에 insert
	int roadRes= biz.updateRoadSubscribe(roadnum); //roadmap subscribe +1하기
	
	Map<String,Boolean> map = new HashMap<String,Boolean>();
		
	if(subRes>0 && roadRes>0) {
		System.out.println("로드맵 구독 insert & roadmap subscribe update성공 ");
		res = true;
		map.put("res",res);
	}else {
		System.out.println("로드맵 구독 insert or roadmap subscribe update 실패");		
		res = false;
		map.put("res",res);
		
	}	
	
	System.out.println("res+++"+res);
	
		return map;
	}
	

	@RequestMapping("/roadUnsubscribe")
	@ResponseBody
	public Map<String, Boolean> roadUnsubscribe(@RequestParam String roadnum, @RequestParam String usernum){
		
		boolean res = false;
		Map<String, Boolean> map = new HashMap<String,Boolean>();
		
		int subRes = biz.roadMapSubscribeDelete(roadnum, usernum); //roadmap Subcribe 테이블에서 delete
		int roadRes = biz.deleteRoadSubscribe(roadnum); //roadmapInfo에서 subcribe -1 하기
		
		if(subRes>0 && roadRes>0) {
			System.out.println("구독취소 성공");
			res = true;
			map.put("res",res);
			
		}else {
			System.out.println("구독취소 실패");
			res = false;
			map.put("res",res);
		}
		
		System.out.println("+++++"+res);
		return map;
	}
	//카카오링크 콜백== 더해야함 //공인IP만 지원 ! 콜백 포기!!
	@RequestMapping("/link/callback")
	public Boolean kakaocallBack(@RequestParam String key) {
		
		System.out.println("콜백 들어옴!!");		

		return null;
	}
	
	//로드맵 구독할 때 로그인 안한사용자 로그인시키고 roadmapDetail로 리다이렉트시킴
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/beforeRoadSub")
	public String beforRoadSub(@RequestParam String roadnum,RedirectAttributes redirectAttributes) {	
				
		redirectAttributes.addAttribute("roadNum", roadnum);
		return"redirect:roadMapDetail";
	}
	
	//로드맵에서 장바구니 넣기
	@RequestMapping("/AddToCart")
	@ResponseBody
	public Map<String, Boolean>  AddToCart(@RequestParam(value="array[]")List<String> cartArray, @RequestParam String usernum ) {
		System.out.println("장바구니 들어온 class_num=="+cartArray);
		System.out.println("장바구니 들어온 user_num=="+usernum);
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		boolean cartRes = false;
		
		int res = biz.AddToCart(cartArray, usernum);
		if(res>0) {
			System.out.println("장바구니에 잘 들어감");
			cartRes= true;
			map.put("cartRes", cartRes);
		}else {
			System.out.println("장바구니에 안들어감");
			map.put("cartRes", cartRes);
		}		
		
		return map;
	}
	//코멘트 등록
	@RequestMapping("/addComment")
	@ResponseBody
	public Map<String, Boolean> addComment(@ModelAttribute CommentDto dto) {
		
		System.out.println(dto.getComment_content());
		System.out.println(dto.getRoadmap_num());
		System.out.println(dto.getUser_num());
		
		Map<String, Boolean> map = new HashMap<String,Boolean>();
		
		boolean resChk = false;
		
		int res = biz.addComment(dto);
		if(res>0) {
			System.out.println("댓글등록 성공");
			resChk = true;
			map.put("resChk",resChk);
		}else {
			System.out.println("댓글등록 실패");
			map.put("resChk",resChk);
		}
		
		return map;
	}
	
	//코멘트 리스트보기
	@RequestMapping("/commentList")
	@ResponseBody
	public List<CommentDto> commentList(@RequestParam String roadnum, Model model ) {
		System.out.println("commentList 들어옴");
		System.out.println("로드맵번호: "+ roadnum);
		
		List<CommentDto> list = new ArrayList<CommentDto>();
		list = biz.commentList(roadnum);
		
		System.out.println("댓글 갯수"+list.size());

		return list;
	}
	//코멘트 수정
	@RequestMapping("/commentUpdate")
	@ResponseBody
	public Map<String, Boolean> commentUpdate(@RequestParam String content, @RequestParam String ComNum) {
		
		System.out.println("댓글수정들어옴");
		System.out.println("번호"+ComNum + "내용" + content);
		int cnt = biz.commentUpdate(content,ComNum);
		
		boolean res = false; 
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		
		if(cnt>0) {
			System.out.println("댓글수정성공");
			res = true;
			map.put("res", res);
			
		}else {
			System.out.println("댓글수정실패");
			map.put("res", res);
			
		}
		
		System.out.println("res==="+res);
		return map;
	}
	
	@RequestMapping("/deleteComment")
	@ResponseBody
	public Map<String, Boolean> deleteComment(@RequestParam String comNum){
		
		
		Map<String, Boolean> map = new HashMap<String,Boolean>();
		int res = biz.deleteComment(comNum);
		boolean Chk = false;
		
		if(res>0) {
			System.out.println("삭제성공");
			Chk = true;
			map.put("Chk", Chk);
		}else {
			System.out.println("삭제실패");
			map.put("Chk", Chk);
		}		
		
		return map;
	}
	
	@RequestMapping("/addReComment")
	@ResponseBody
	public Boolean addReComment(@ModelAttribute CommentDto dto){
		
		System.out.println("대댓추가 들어옴");
		System.out.println(dto.getComment_content()+"//"+dto.getComment_num()+"//"+dto.getUser_num()+"//"+dto.getRoadmap_num());
		
		int update = biz.reCommentSqUpdate(dto);
		int insert = biz.reCommentAdd(dto);
		
		System.out.println("updaet=== "+update);
		System.out.println("insert==="+insert);

		boolean Chk = true;		
		
		
		return Chk;
	}
	
	
	

}
