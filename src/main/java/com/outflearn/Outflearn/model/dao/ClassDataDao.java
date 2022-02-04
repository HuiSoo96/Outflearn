package com.outflearn.Outflearn.model.dao;

import java.util.List;

import com.outflearn.Outflearn.dto.ClassCategoryDto;
import com.outflearn.Outflearn.dto.ClassDataDto;
import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.ClassIntroduceDto;
import com.outflearn.Outflearn.dto.QADto;
import com.outflearn.Outflearn.dto.LiveDto;
import com.outflearn.Outflearn.dto.MainStreamDto;
import com.outflearn.Outflearn.dto.SubStreamDto;
import com.outflearn.Outflearn.dto.connectUserClass;
import com.outflearn.Outflearn.dto.ClassReviewDto;

public interface ClassDataDao {
	
	String namespace = "data.";
	
		// 강좌 정보  - CLASS_INFO 테이블
		public List <ClassInfoDto> ClassInfoSelectList();
		public List<ClassInfoDto> CategorySelectList(String class_category);
		public ClassInfoDto ClassInfoSelectOne(int class_num);
		public List<ClassInfoDto>  classInfoSelectListUser(int user_num);
		public int ClassInfoInsert(ClassInfoDto dto);
		public List<ClassInfoDto> ClassSubName(int sub_num);
			
		
		// 강좌 소개 - CLASS_INTRODUCE 테이블
		public ClassIntroduceDto ClassIntroduceSelectList(int class_num);
		public int ClassIntroduceInsert(ClassIntroduceDto dto);
		public int ClassIntroduceUpdate(int class_num, String class_content);
		
			
		// 강좌 데이터 -  CLASS_DATA TABLE
		public List <ClassDataDto> ClassDataSelectList();
		public List<ClassDataDto> ClassDataSelectOne(int class_num);
		public int ClassDataInsert(ClassDataDto dto);	
		public int ClassChapterDataInsert(ClassDataDto dto);
		public int ClassInfoUpdateSub(int class_num);
		public int ClassDataInsertPlus(ClassDataDto dto);
		public int DataVideoUploadUpdate(ClassDataDto dto);
		public int DataVideoSecondInsertPlus(ClassDataDto dto);
		public ClassDataDto videoDataOne(ClassDataDto dto);
		
		// 장바구니 - BASKET 테이블
		public int classBasketInsert(ClassInfoDto dto);
		public int classBasketDelete(int user_num);
		public int classBasketDeleteOne(int class_num);
		
		// 댓글 - CLASS_REVIEW 테이블
		public List <ClassReviewDto> ClassReviewSelectList(int class_num);
		public ClassReviewDto ClassReviewSelectOne(int review_num);
		public int ClassReviewInsert(ClassReviewDto dto);
		public int ClassReviewUpdate(ClassReviewDto dto);
		public int ClassReviewDelete(ClassReviewDto dto);
			
		public int ClassReviewReplyUpdate(ClassReviewDto dto);
		public int ClassReviewInsertAnswer(ClassReviewDto dto);
		public int ClassReviewReplyDelete(int review_num);
		
		//페이징
		public List<ClassInfoDto> selectListPage(int firstIndex, int recordCountPerPage, String txt_search);
		public int selectTotalCount(String txt_search);
		public List<ClassInfoDto> selectListPageTwo(int firstIndex, int recordCountPerPage, String txt_search, String searchOption);
		public int selectTotalCountTwo(String txt_search, String searchOption);
		public List<ClassInfoDto> selectListPageStream(int firstIndex, int recordCountPerPage, String txt_sesarch, String searchOption, int sub_num);
		public int selectTotalCountStream(String txt_search, String searchOption, int sub_num);
		
		public int selectTotalCountQA(String txt_search, int class_num);
		public List<QADto> selectListPageQA(int firstIndex, int recordCountPerPage, String txt_search, int class_num);
		
		// 주류, 부류 - MAIN_STREAM , SUB_STREAM
		//public int mainStreamInsert(MainStreamDto dto);
		//public int subStreamInsert(SubStreamDto dto);
		public List<SubStreamDto> MainStreamSelectOne(int main_num);
		public int ClassCategoryInsert(int main_num, int sub_num);
		public ClassIntroduceDto ClassIntroduceSelectOne(int class_num);
		
		// 질문
		public List<QADto> QASelectList(int class_num);
		public QADto QASelectOne(int qa_num);
		public List<QADto> QAReply(int qa_group_no); 
		public int QAInsert(QADto dto);
		public int QADelete(int qa_group_no);
		public int QAReplyInsert(QADto dto);
		public int QAReplyUpdate(QADto dto);
		public int QAReplyDelete(QADto dto);
		
		// 메인화면 리스트
		public List<ClassInfoDto> SubCountSelectList();
		public List<ClassInfoDto> PopularReviewSelectList();
		
		// LectureDetail Dashboard 리스트
		public List<ClassReviewDto> ReviewList(int class_num);
		public List<QADto> QAList(int class_num);
		// 구독
		public int classInsertSubscribe(int user_num, int class_num);
		// 결제 후 강의 장바구니 삭제
		public int ClassBuyAfter(int class_num, int user_num);
}
