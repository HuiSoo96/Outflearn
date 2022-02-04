package com.outflearn.Outflearn.model.dao;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.CommentDto;
import com.outflearn.Outflearn.dto.MainStreamDto;
import com.outflearn.Outflearn.dto.RoadMapCon;
import com.outflearn.Outflearn.dto.RoadMapInfoDto;
import com.outflearn.Outflearn.dto.RoadUserCombineDto;
import com.outflearn.Outflearn.dto.SubStreamDto;

@Repository
public class RoadMapDaoImpl implements RoadMapDao {

	@Inject
	SqlSessionTemplate session;

	// roadmap paege1 작성
	@Override
	public int insertRoadMap(RoadMapInfoDto dto) {

		int res = 0;

		try {
			session.insert(NAMESPACE + "roadmapInsert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	// 현재 시퀀스 가져오기
	@Override
	public int selectSQ() {
		int seq = 0;
		System.out.println("selectSQ 들어옴");

		try {
			seq = session.selectOne(NAMESPACE + "selectSQ");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return seq;
	}

	// roadmap paege2 작성
	@Override
	public int insertroadNclass(String[] class_num, String seq) {

		System.out.println("insert roadNClassDao입장:::");

		for (String res : class_num) {
			System.out.println(res);
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("seq", seq);

		List<String> staffList = new ArrayList<>();

		for (int i = 0; i < class_num.length; i++) {

			staffList.add(class_num[i]);
		}

		System.out.println("staffLsit" + staffList.size());

		paramMap.put("staffList", staffList);

		return session.insert(NAMESPACE + "roadNclass", paramMap);
	}

	// 주류리스트 뽑기
	@Override
	public List<MainStreamDto> mainStreamList() {

		List<MainStreamDto> list = new ArrayList<MainStreamDto>();

		try {
			list = session.selectList(NAMESPACE + "mainStreamList");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 부류리스트 뽑기
	@Override
	public List<SubStreamDto> subStreamList() {

		List<SubStreamDto> list = new ArrayList<SubStreamDto>();

		try {
			list = session.selectList(NAMESPACE + "subStramList");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 로드맵 에서 부류로 검색된 class리스트리턴
	// subNum으로 검색
	@Override
	public List<ClassInfoDto> classInfoList(String[] subFilter) {

		// 리턴되는 리스트
		List<ClassInfoDto> list = new ArrayList<ClassInfoDto>();

		// 필터 번호 담을 리스트
		List<String> itemList = new ArrayList<String>();

		for (int i = 0; i < subFilter.length; i++) {
			itemList.add(subFilter[i]);
		}

		// 리스트를 맵에 담아서 넘기기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemList", itemList);

		System.out.println("map" + map);

		try {
			list = session.selectList(NAMESPACE + "classInfoList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 로드맵 리스트
	@Override
	public List<RoadMapInfoDto> roadMapList() {

		List<RoadMapInfoDto> list = new ArrayList<RoadMapInfoDto>();

		try {
			list = session.selectList(NAMESPACE + "roadMapList");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// roandMap selectOne
	@Override
	public RoadMapInfoDto selectOneRoadMap(String roadNum) {

		RoadMapInfoDto dto = new RoadMapInfoDto();

		try {
			dto = session.selectOne(NAMESPACE + "selectOneRoadMap", roadNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	// 로드맵번호로 로드맵con에서 classNum들 받아오기
	@Override
	public List<Integer> RoadMapConList(String roadNum) {

		List<Integer> list = new ArrayList<Integer>();

		try {
			list = session.selectList(NAMESPACE + "roadMapConlist", roadNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 클래스 번호들로 클래스 info리스트 받아오기
	@Override
	public List<ClassInfoDto> RoadClassInfoList(List<Integer> list) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roadClassList", list);

		List<ClassInfoDto> resList = new ArrayList<ClassInfoDto>();

		try {
			resList = session.selectList(NAMESPACE + "RoadClassInfoList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resList;
	}

	// 로드맵구독테이블에 추가
	@Override
	public int roadMapSubscribeInsert(String roadNum, String userNum) {

		int res = 0;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userNum", userNum);
		map.put("roadNum", roadNum);

		try {
			res = session.insert(NAMESPACE + "roadMapSubscribeInsert", map);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return res;
	}

	// 로드맵info에서 구독자수+1
	@Override
	public int updateRoadSubscribe(String roandNum) {

		int res = 0;

		try {
			res = session.update(NAMESPACE + "updateSubscribe", roandNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	// 로드맵 구독여부 체크
	@Override
	public int roadJoinChk(String roadNum, String userNum) {
		int res = 0;

		Map<String, String> map = new HashMap<String, String>();
		map.put("userNum", userNum);
		map.put("roadNum", roadNum);

		try {
			res = session.selectOne(NAMESPACE + "roadJoinChk", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	// 페이징, 검색
	@Override
	public List<RoadMapInfoDto> selectListPage(int firstIndex, int recordCountPerPage, String txt_search,
			String searchOption) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("firstIndex", String.valueOf(firstIndex));
		map.put("recordCountPerPage", String.valueOf(recordCountPerPage));
		map.put("txt_search", txt_search);
		map.put("searchOption", searchOption);

		List<RoadMapInfoDto> list = session.selectList(NAMESPACE + "selectListPageRoadMap", map);

		return list;
	}

	@Override
	public int selectTotalCountRoadMap(String txt_search, String searchOption, int main_num) {

		int res = 0;

		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("txt_search", txt_search);
		map.put("main_num", String.valueOf(main_num));
		res = session.selectOne(NAMESPACE + "selectTotalCountRoadMap", map);

		return res;
	}

	// 로드맵구독 테이블에서 구독자 삭제
	@Override
	public int roadMapSubscribeDelete(String roadNum, String userNum) {
		int res = 0;

		Map<String, String> map = new HashMap<String, String>();
		map.put("userNum", userNum);
		map.put("roadNum", roadNum);

		try {
			res = session.delete(NAMESPACE + "roadMapSubscribeDelete", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	// 로드맵info테이블에서 구독자수 -1
	@Override
	public int deleteRoadSubscribe(String roadNum) {
		int res = 0;

		try {
			res = session.update(NAMESPACE + "deleteSubScribe", roadNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	// 로드맵인포+닉네임 리스트
	@Override
	public List<RoadUserCombineDto> roadMapComList() {

		List<RoadUserCombineDto> list = new ArrayList<RoadUserCombineDto>();
		try {
			list = session.selectList(NAMESPACE + "roadComList");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 로드맵인포+닉네임 selectOne
	@Override
	public RoadUserCombineDto roadMapComSelectOne(String roadNum) {

		RoadUserCombineDto dto = new RoadUserCombineDto();

		try {
			dto = session.selectOne(NAMESPACE + "roadComOne", roadNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	// ourterJoin으로 구독+장바구니강좌 리스트 가져오기
	@Override
	public List<Integer> SubBaList(String userNum) {

		List<Integer> list = new ArrayList<Integer>();

		try {
			list = session.selectList(NAMESPACE + "SubBaList", userNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int AddToCart(List<String> cartArray, String userNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNum", userNum);
		map.put("cartArray", cartArray);

		int res = 0;

		try {
			res = session.insert(NAMESPACE + "AddToCart", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public List<RoadUserCombineDto> roadMapComList(int firstIndex, int recordCountPerPage, String txt_search,
			String searchOption, int main_num) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("firstIndex", String.valueOf(firstIndex));
		map.put("recordCountPerPage", String.valueOf(recordCountPerPage));
		if (txt_search.equals("") || txt_search == null) {
			map.put("txt_search", null);
		} else {
			map.put("txt_search", txt_search);
		}
		map.put("searchOption", searchOption);

		if (main_num == 0) {
			map.put("main_num", String.valueOf(0));
		} else {
			map.put("main_num", String.valueOf(main_num));
		}
		System.out.println("다오임플 main_num: " + main_num);

		List<RoadUserCombineDto> list = session.selectList(NAMESPACE + "roadComList", map);

		return list;

	}

	// 로드맵 1p수정(update)
	@Override
	public int roadMapUpdate(RoadMapInfoDto dto) {

		int res = 0;

		try {
			res = session.update(NAMESPACE + "roadMapUpdate", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// 로드맵2P 수정전에 삭제
	@Override
	public int DeleteroadConBeforeUpdate(String roadNum) {

		int res = 0;

		try {
			res = session.delete(NAMESPACE + "DeleteroadConBeforeUpdate", roadNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
	
	//댓글등록
	@Override
	public int addComment(CommentDto dto) {
		int res = 0;
		
		try {
			res = session.insert(NAMESPACE+"addComment", dto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	//댓글리스트 가져오기
	@Override
	public List<CommentDto> commentList(String roadNum) {
		
		List<CommentDto> list = new ArrayList<CommentDto>();
		
		try {
			list = session.selectList(NAMESPACE+"commentList", roadNum);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	//댓글수정하기
	@Override
	public int commentUpdate(String content, String ComNum) {
		int res = 0;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", content);
		map.put("ComNum", ComNum);
		
		try {
			res = session.update(NAMESPACE+"commentUpdate", map);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	//댓글삭제하기
	@Override
	public int deleteComment(String comNum) {
		int res = 0;
		
		try {
		res = session.update(NAMESPACE+"deleteComment", comNum);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	//대댓 groupsq 업뎃
	@Override
	public int reCommentSqUpdate(CommentDto dto) {
		
		int res = 0;
		
		try {
			res = session.update(NAMESPACE+"reCommentSqUpdate", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return res;
	}
	
	//대댓 입력
	@Override
	public int reCommentAdd(CommentDto dto) {
		int res = 0;
		
		try {
			res = session.insert(NAMESPACE+"reCommentAdd", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return res;
	}

	@Override
	public List<Integer> getSubnumList(List<Integer> list) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("classlist",list);
		List<Integer> getList = new ArrayList<Integer>();
		
		try {
			getList = session.selectList(NAMESPACE+"getSubnumList", map);
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return getList;
	}

	@Override
	public List<String> getsubnumName(List<Integer> list) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("subnumNameList",list);
		List<String> getList = new ArrayList<String>();
		
		try {
			getList = session.selectList(NAMESPACE+"getsubnumNameList", map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return getList;
	}
	
	
	
	

	


}
