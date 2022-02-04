package com.outflearn.Outflearn.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.outflearn.Outflearn.dto.ClassCategoryDto;
import com.outflearn.Outflearn.dto.ClassDataDto;
import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.ClassIntroduceDto;
import com.outflearn.Outflearn.dto.ClassReviewDto;
import com.outflearn.Outflearn.dto.QADto;
import com.outflearn.Outflearn.dto.SubStreamDto;

@Repository
public class ClassDataDaoImpl implements ClassDataDao {

	@Autowired
	public SqlSessionTemplate sqlSession;

//	--------------------------------------------------- 강좌 정보(CLASS_INFO)
	@Override
	public List<ClassInfoDto> ClassInfoSelectList() {

		List<ClassInfoDto> list = new ArrayList<ClassInfoDto>();

		try {
			list = sqlSession.selectList(namespace + "ClassInfoSelectlist");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ClassInfoDto ClassInfoSelectOne(int class_num) {

		ClassInfoDto dto = new ClassInfoDto();

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("class_num", class_num);

		dto = sqlSession.selectOne(namespace + "ClassInfoSelectone", map);

		return dto;
	}
	
	@Override
	public List<ClassInfoDto> classInfoSelectListUser(int user_num) {
		
		List<ClassInfoDto> list = new ArrayList<ClassInfoDto>();
		
		
		try {
			list = sqlSession.selectList(namespace + "classInfoSelectListUser", user_num);
		} catch (Exception e) {
			System.out.println("오냐");
			e.printStackTrace();
		} 
		
		return list;
	}

	@Override
	public int ClassInfoInsert(ClassInfoDto dto) {
		int res = 0;

		res = sqlSession.insert(namespace + "ClassInfoDtoInsert", dto);

		return res;
	}
	
	@Override
	public List<ClassInfoDto> ClassSubName(int sub_num) {
		System.out.println("왜 안와??" + sub_num);
		
		
		List<ClassInfoDto> list = sqlSession.selectList(namespace + "ClassSubName", sub_num); 

		
		System.out.println("여기오니??");
		return list;
	}
	
	@Override
	public int ClassInfoUpdateSub(int class_num) {
		
		int res = sqlSession.update(namespace + "ClassInfoUpdateSub" , class_num); 
		
		return res;
	}	


// --------------------------------------------------- 강좌 내용(CLASS_INTRODUCE)
	@Override
	public ClassIntroduceDto ClassIntroduceSelectList(int class_num) {

		ClassIntroduceDto dto = sqlSession.selectOne(namespace + "classIntroduceSelectlist", class_num);

		return dto;
	}

	@Override
	public int ClassIntroduceInsert(ClassIntroduceDto dto) {

		int res = 0;

		res = sqlSession.insert(namespace + "ClassIntroduceInsert", dto);

		return res;
	}

	@Override
	public int ClassIntroduceUpdate(int class_num, String class_content) {
		
		 HashMap<String, String> map = new HashMap<String, String>();
		  map.put("class_num", class_num + "");
		  map.put("class_content", class_content);
		      
		  int list = sqlSession.update(namespace + "ClassIntroduceUpdate", map); 
		      
		 return list;
	}

//	--------------------------------------------------- 강좌 데이터(CLASS_DATA)
	@Override
	public List<ClassDataDto> ClassDataSelectList() {

		List<ClassDataDto> list = new ArrayList<ClassDataDto>();

		try {
			list = sqlSession.selectList(namespace + "ClassDateSelectlist");
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<ClassInfoDto> CategorySelectList(String class_category) {

		List<ClassInfoDto> list = new ArrayList<ClassInfoDto>();

		Map<String, String> map = new HashMap<String, String>();
		map.put("class_category", class_category);

		try {
			list = sqlSession.selectList(namespace + "CategorySelectList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<ClassDataDto> ClassDataSelectOne(int class_num) {

		List<ClassDataDto> list = new ArrayList<ClassDataDto>();

		list = sqlSession.selectList(namespace + "classdataselectone", class_num);

		return list;
	}

//  유튜브 영상
	@Override
	public int ClassDataInsert(ClassDataDto dto) {
		int res = 0;

		res = sqlSession.insert(namespace + "ClassDataInsert", dto);

		return res;
	}

//	챕터 추가
	@Override
	public int ClassChapterDataInsert(ClassDataDto dto) {
		int res = 0;

		res = sqlSession.insert(namespace + "ClassChapterDataInsert", dto);

		return res;
	}
	
	// 영상 추가
	@Override
	public int ClassDataInsertPlus(ClassDataDto dto) {
		int res = 0;

		res = sqlSession.insert(namespace + "ClassDataInsertPlus", dto);

		return res;
	}

	
	@Override
	public int DataVideoUploadUpdate(ClassDataDto dto) {
		int res = 0;
		
		res = sqlSession.update(namespace + "DataVideoUploadUpdate", dto); 
		
		return res;
	}
	
	@Override
	public int DataVideoSecondInsertPlus(ClassDataDto dto) {
		int res = 0;
		
		res = sqlSession.insert(namespace + "DataVideoSecondInsertPlus", dto); 
		
		return res;
	}
	
	@Override
	public ClassDataDto videoDataOne(ClassDataDto dto) {
		
		ClassDataDto Cdto = sqlSession.selectOne(namespace + "videoDataOne", dto);
		
		return Cdto;
	}

	

//	--------------------------------------------------- 장바구니(BASKET)
	@Override
	public int classBasketInsert(ClassInfoDto dto) {
		
		int res = 0;
		
		res = sqlSession.insert(namespace + "classBasketInsert", dto); 
		
		return res;
	}

	@Override
	public int classBasketDelete(int user_num) {
		
		int res = 0;
		
		res = sqlSession.delete(namespace + "classBasketDelete", user_num); 
		
		return res;
	}
	
	@Override
	public int classBasketDeleteOne(int class_num) {

		int res = sqlSession.delete(namespace + "classBasketDeleteOne", class_num); 
		
		return res;
	}

	@Override
	public ClassIntroduceDto ClassIntroduceSelectOne(int class_num) {
		
		ClassIntroduceDto dto = sqlSession.selectOne(namespace + "classIntroduceselectone", class_num);

		return dto;
	}


	// --------------------------------------------------- 댓글
	@Override
	public List<ClassReviewDto> ClassReviewSelectList(int class_num) {

		List<ClassReviewDto> list = new ArrayList<ClassReviewDto>();

		try {
			list = sqlSession.selectList(namespace + "classReviewSelectlist", class_num);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ClassReviewDto ClassReviewSelectOne(int review_num) {

		ClassReviewDto dto = sqlSession.selectOne(namespace + "classReviewSelectOne", review_num);

		return dto;
	}

	@Override
	public int ClassReviewInsert(ClassReviewDto dto) {

		int res = 0;

		res = sqlSession.insert(namespace + "classReviewInsert", dto);

		return res;
	}

	@Override
	public int ClassReviewUpdate(ClassReviewDto dto) {

		int res = 0;

		res = sqlSession.update(namespace + "classReviewUpdate", dto);

		return res;
	}

	@Override
	public int ClassReviewDelete(ClassReviewDto dto) {

		int res = 0;

		res = sqlSession.delete(namespace + "classReviewDelete", dto);

		return res;
	}

	//페이징
			
	@Override
	public int selectTotalCount(String txt_search) {
		
		int res = 0;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("txt_search", txt_search);
		res = sqlSession.selectOne(namespace + "selectTotalCount", map);
		
		return res;
	}
	
	@Override
	public List<ClassInfoDto> selectListPage(int firstIndex, int recordCountPerPage, String txt_search) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("firstIndex", String.valueOf(firstIndex));
		map.put("recordCountPerPage", String.valueOf(recordCountPerPage));
		map.put("txt_search", txt_search);

		
		List<ClassInfoDto> list = sqlSession.selectList(namespace + "selectListPage", map);
		return list;
	}
	
	@Override
	public List<ClassInfoDto> selectListPageTwo(int firstIndex, int recordCountPerPage, String txt_search,
			String searchOption) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("firstIndex", String.valueOf(firstIndex));
		map.put("recordCountPerPage", String.valueOf(recordCountPerPage));
		map.put("txt_search", txt_search );
		map.put("searchOption", searchOption);
		
		List<ClassInfoDto> list = sqlSession.selectList(namespace +"selectListPageTwo", map);
		System.out.println(list);
		return list;
	}
	
	
	@Override
	public int selectTotalCountTwo(String txt_search, String searchOption) {
		
		int res = 0;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("txt_search", txt_search);
		res = sqlSession.selectOne(namespace + "selectTotalCountTwo", map);
		System.out.println(txt_search+"다오임플투");
		System.out.println(searchOption);
		System.out.println(res);
		return res;
	}
	
	@Override
	public List<ClassInfoDto> selectListPageStream(int firstIndex, int recordCountPerPage, String txt_search,
			String searchOption, int sub_num) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("firstIndex", String.valueOf(firstIndex));
		map.put("recordCountPerPage", String.valueOf(recordCountPerPage));
		if(txt_search.equals("") || txt_search == null) {
			map.put("txt_search", null);
		} else {
			map.put("txt_search", txt_search);			
		}
		map.put("searchOption", searchOption);
		
		if(sub_num == 0) {
			map.put("sub_num", String.valueOf(0));
		}else {
			map.put("sub_num", String.valueOf(sub_num));
		}
		List<ClassInfoDto> list = sqlSession.selectList(namespace + "selectListPageStream", map);
		
		return list;
	}

	@Override
	public int selectTotalCountStream(String txt_search, String searchOption, int sub_num) {
		
		int res = 0;
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		if(txt_search.equals("") || txt_search == null) {
			map.put("txt_search", null);
		} else {
			map.put("txt_search", txt_search);			
		}
				
			map.put("sub_num", String.valueOf(sub_num));
		
		
		res = sqlSession.selectOne(namespace + "selectTotalCountStream", map);
		System.out.println("sub_num dao:"+sub_num);
		System.out.println("다오임플카운트스트림");
		System.out.println("dao res:"+res);
		return res;
	}

	
	@Override
	public int selectTotalCountQA(String txt_search, int class_num) {
		
		int res = 0;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("txt_search", txt_search);
		map.put("class_num", String.valueOf(class_num));
		res = sqlSession.selectOne(namespace + "selectTotalCountQA", map);
		
		return res;
	}

	@Override
	public List<QADto> selectListPageQA(int firstIndex, int recordCountPerPage, String txt_search, int class_num) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("firstIndex", String.valueOf(firstIndex));
		map.put("recordCountPerPage", String.valueOf(recordCountPerPage));
		map.put("txt_search", txt_search);
		map.put("class_num", String.valueOf(class_num));

		
		List<QADto> list = sqlSession.selectList(namespace + "selectListPageQA", map);
		return list;
	}
	
	@Override
	public int ClassReviewReplyUpdate(ClassReviewDto dto) {

		int res = sqlSession.update(namespace + "classReviewUpdateAnswer", dto);

		return res;
	}

	@Override
	public int ClassReviewInsertAnswer(ClassReviewDto dto) {
		
		int res = 0;
		
		res = sqlSession.insert(namespace + "classReviewInsertAnswer", dto);
		
		return res;
	}
	
	@Override
	public int ClassReviewReplyDelete(int review_num) {
		
		int res = sqlSession.delete(namespace + "ReviewReplyDelete", review_num);
		
		return res;
	}
	
	
	@Override
	public List<SubStreamDto> MainStreamSelectOne(int main_num) {
		
		List<SubStreamDto> list = new ArrayList<SubStreamDto>();

		try {
			list = sqlSession.selectList(namespace + "MainStreamSelectOne", main_num);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	
	@Override
	public int ClassCategoryInsert(int main_num, int sub_num) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("main_num", main_num);
		map.put("sub_num", sub_num);
		
		int list = sqlSession.insert(namespace + "ClassCategoryInsert", map); 
		
		return list;
	}	
	
/*		
=======
>>>>>>> origin/syh
	@Override
	public int mainStreamInsert(MainStreamDto dto) {
		
		int res = sqlSession.insert(namespace + "mainStreamInsert", dto);
		
		return res;
	}

	@Override
	public int subStreamInsert(SubStreamDto dto) {
		
		int res = sqlSession.insert(namespace + "subStreamInsert", dto);
		
		return res;
	}

*/
	
	@Override
	public List<QADto> QASelectList(int class_num) {
		
		List<QADto> list = new ArrayList<QADto>();
		
		try {
			list = sqlSession.selectList(namespace + "QASelectList", class_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public QADto QASelectOne(int qa_num) {
		
		QADto dto = sqlSession.selectOne(namespace + "QASelectOne", qa_num);
		
		return dto;
	}
	
	

	@Override
	public List<QADto> QAReply(int qa_group_no) {
		
		List<QADto> list = new ArrayList<QADto>();
		
		try {
			list = sqlSession.selectList(namespace + "QAReply", qa_group_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public int QAInsert(QADto dto) {
		
		int res = 0;
		
		res = sqlSession.insert(namespace + "QAInsert", dto);
		
		return res;
	}

	
	@Override
	public int QAReplyInsert(QADto dto) {
		
		int res = 0;
		
		res = sqlSession.insert(namespace + "QAReplyInsert", dto);
		
		return res;
	}
	
	@Override
	public int QAReplyUpdate(QADto dto) {
		
		int res = 0;
		System.out.println(dto.getQa_content() + " : daoImpl");
		System.out.println(dto.getQa_num() + " : daoimpl qanum");
		System.out.println(dto.getQa_group_no() + " : daoimpl groupnoS");
		System.out.println(dto.getQa_group_sq() + " : daoimpl groupsq");
		
		res = sqlSession.update(namespace + "QAReplyUpdate", dto);
		
		return res;
	}

	@Override
	public List<ClassInfoDto> SubCountSelectList() {
		
		List<ClassInfoDto> list = new ArrayList<ClassInfoDto>();
		
		list = sqlSession.selectList(namespace + "SubCountSelectList", list);
		
		return list;
	}
	
	@Override
	public List<ClassInfoDto> PopularReviewSelectList() {
		
		List<ClassInfoDto> list = new ArrayList<ClassInfoDto>();
		
		list = sqlSession.selectList(namespace + "PopularReviewSelectList", list);
		
		return list;
	}
	
	// 결제 후 강의 장바구니 삭제
	@Override
	public int ClassBuyAfter(int class_num, int user_num) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("user_num", user_num);
		map.put("class_num", class_num);
		
		int list = sqlSession.selectOne(namespace + "ClassBuyAfter", map); 
		
		return list;
		
	}

	@Override
	public int QADelete(int qa_group_no) {
		
		int res = sqlSession.delete(namespace + "QADelete", qa_group_no);
		
		return res;
	}

	@Override
	public int QAReplyDelete(QADto dto) {
		
		int res = sqlSession.delete(namespace + "QAReplyDelete", dto);
		
		return res;
	}

	@Override
	public List<ClassReviewDto> ReviewList(int class_num) {
		
		List<ClassReviewDto> list = new ArrayList<ClassReviewDto>();
		
		try {
			list = sqlSession.selectList(namespace + "ReviewList", class_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<QADto> QAList(int class_num) {
		
		List<QADto> list = new ArrayList<QADto>();
		
		try {
			list = sqlSession.selectList(namespace + "QAList", class_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int classInsertSubscribe(int user_num, int class_num) {
	      HashMap<String, Integer> map = new HashMap<String, Integer>();
	      map.put("user_num", user_num);
	      map.put("class_num", class_num);
	      
	      int list = sqlSession.insert(namespace + "classInsertSubscribe", map); 
	      
	      return list;
	}



	


	




}
