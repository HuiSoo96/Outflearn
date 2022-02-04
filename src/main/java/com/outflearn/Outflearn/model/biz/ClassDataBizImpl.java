package com.outflearn.Outflearn.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outflearn.Outflearn.dto.ClassDataDto;
import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.ClassIntroduceDto;
import com.outflearn.Outflearn.dto.ClassReviewDto;
import com.outflearn.Outflearn.dto.QADto;
import com.outflearn.Outflearn.dto.SubStreamDto;
import com.outflearn.Outflearn.model.dao.ClassDataDao;


@Service
public class ClassDataBizImpl implements ClassDataBiz {
	
	@Autowired
	private ClassDataDao dao;



//	--------------------------------------------------- 강좌 정보(CLASS_INFO)
	public List<ClassInfoDto> ClassInfoSelectList() {
		
		return dao.ClassInfoSelectList();
	}
	
	@Override
	public List<ClassInfoDto> CategorySelectList(String class_category) {
		return dao.CategorySelectList(class_category);
	}
	
	@Override
	public ClassInfoDto ClassInfoSelectOne(int class_num) {
		
		return dao.ClassInfoSelectOne(class_num);
	}
	
	@Override
	public List<ClassInfoDto> classInfoSelectListUser(int user_num) {
		
		return dao.classInfoSelectListUser(user_num);
	}

	@Override
	public int ClassInfoInsert(ClassInfoDto dto) {
		
		return dao.ClassInfoInsert(dto);
	}
	
	@Override
	public List<ClassInfoDto> ClassSubName(int sub_num) {
		System.out.println("비즈오니?");
		return dao.ClassSubName(sub_num);
	}


//	---------------------------------------------------	강좌 소개(CLASS_INTRODUCE)
	@Override
	public ClassIntroduceDto ClassIntroduceSelectList(int class_num) {
		
		return dao.ClassIntroduceSelectList(class_num);
	}
	

	@Override
	public int ClassIntroduceInsert(ClassIntroduceDto dto) {
		
		return dao.ClassIntroduceInsert(dto);
	}
	
	@Override
	public int ClassIntroduceUpdate(int class_num, String class_content) {
		
		return dao.ClassIntroduceUpdate(class_num, class_content);
	}
	
//	--------------------------------------------------- 강좌 데이터(CLASS_DATA)
	
	@Override
	public ClassIntroduceDto ClassIntroduceSelectOne(int class_num) {

		return dao.ClassIntroduceSelectOne(class_num);
	}
	
	@Override
	public List<ClassDataDto> ClassDataSelectOne(int class_num) {
	
		return dao.ClassDataSelectOne(class_num);
	}
	
	@Override
	public int ClassDataInsert(ClassDataDto dto) {
		
		return dao.ClassDataInsert(dto);
	}
	
	@Override
	public int ClassChapterDataInsert(ClassDataDto dto) {
		
		return dao.ClassChapterDataInsert(dto);
	}
	
	@Override
	public int ClassInfoUpdateSub(int class_num) {
		
		return dao.ClassInfoUpdateSub(class_num);
	}
	
	@Override
	public int ClassDataInsertPlus(ClassDataDto dto) {
		
		return dao.ClassDataInsertPlus(dto);
	}
	
	@Override
	public int DataVideoUploadUpdate(ClassDataDto dto) {
		
		return dao.DataVideoUploadUpdate(dto);
	}
	
	@Override
	public int DataVideoSecondInsertPlus(ClassDataDto dto) {
		
		return dao.DataVideoSecondInsertPlus(dto);
	}
	
	@Override
	public ClassDataDto videoDataOne(ClassDataDto dto) {
	
		return dao.videoDataOne(dto);
	}

//	-------------------------------------------------장바구니(BASKET)
	@Override
	public int classBasketInsert(ClassInfoDto dto) {
	
		return dao.classBasketInsert(dto);
	}

	@Override
	public int classBasketDeleteOne(int class_num) {

		return dao.classBasketDeleteOne(class_num);
	}
	
	
	@Override
	public int classBasketDelete(int user_num) {
		
		return dao.classBasketDelete(user_num);
	}
	

	
//	--------------------------------------------------- 댓글
	@Override
	public List<ClassReviewDto> ClassReviewSelectList(int class_num) {
	
		return dao.ClassReviewSelectList(class_num);
	}

	@Override
	public ClassReviewDto ClassReviewSelectOne(int review_num) {

		return dao.ClassReviewSelectOne(review_num);
	}

	@Override
	public int ClassReviewInsert(ClassReviewDto dto) {
		
		return dao.ClassReviewInsert(dto);
	}

	@Override
	public int ClassReviewUpdate(ClassReviewDto dto) {
	
		return dao.ClassReviewUpdate(dto);
	}

	@Override
	public int ClassReviewDelete(ClassReviewDto dto) {
		
		return dao.ClassReviewDelete(dto);
	}

	@Override
	public int ClassReviewInsertAnswer(ClassReviewDto dto) {
		return dao.ClassReviewInsertAnswer(dto);
	}
	
	@Override
	public int ClassReviewReplyUpdate(ClassReviewDto dto) {
		return dao.ClassReviewReplyUpdate(dto);
	}

	@Override
	public int ClassReviewReplyDelete(int review_num) {
		return dao.ClassReviewReplyDelete(review_num);
	}
	
	@Override
	public List<ClassDataDto> ClassDataSelectList() {
		return dao.ClassDataSelectList();
	}
//-----------------------------------------------------페이징 및 리스트 불러오기	
	@Override
	public int selectTotalCount(String txt_search) {
		return dao.selectTotalCount(txt_search);
	}
	@Override
	public List<ClassInfoDto> selectListPage(int firstIndex, int recordCountPerPage, String txt_search) {
		return dao.selectListPage(firstIndex, recordCountPerPage, txt_search);
	}
	
	@Override
	public List<ClassInfoDto> selectListPageTwo(int firstIndex, int recordCountPerPage, String txt_search, String searchOption) {
		return dao.selectListPageTwo(firstIndex, recordCountPerPage, txt_search, searchOption);
	}
	@Override
	public int selectTotalCountTwo(String txt_search, String searchOption) {
		return dao.selectTotalCountTwo(txt_search, searchOption);
	}
	
	@Override
	public int selectTotalCountQA(String txt_search, int class_num) {
		return dao.selectTotalCountQA(txt_search, class_num);
	}

	@Override
	public List<QADto> selectListPageQA(int firstIndex, int recordCountPerPage, String txt_search, int class_num) {
		return dao.selectListPageQA(firstIndex, recordCountPerPage, txt_search, class_num);
	}
	
	@Override
	public List<QADto> QASelectList(int class_num) {
		return dao.QASelectList(class_num);
	}
	
	@Override
	public List<SubStreamDto> MainStreamSelectOne(int main_num) {
	
		return dao.MainStreamSelectOne(main_num);
	}
	
	/* 주류
	@Override
	public int mainStreamInsert(MainStreamDto dto) {
		
		return dao.mainStreamInsert(dto);
	}
	*/
	/* 부류
	@Override
	public int subStreamInsert(SubStreamDto dto) {
		
		return dao.subStreamInsert(dto);
	}
	*/
	
	@Override
	public int ClassCategoryInsert(int main_num, int sub_num) {
	
		return dao.ClassCategoryInsert(main_num, sub_num);
	}
		
	public QADto QASelectOne(int qa_num) {
		return dao.QASelectOne(qa_num);
	}
	
	@Override
	public List<QADto> QAReply(int qa_group_no) {
		return dao.QAReply(qa_group_no);
	}
	
	@Override
	public int QAInsert(QADto dto) {
		return dao.QAInsert(dto);
	}

	@Override
	public int QAReplyInsert(QADto dto) {
		return dao.QAReplyInsert(dto);
	}
	
	@Override
	public int QAReplyUpdate(QADto dto) {
		return dao.QAReplyUpdate(dto);
	}

	// 구독
	@Override
	public List<ClassInfoDto> SubCountSelectList() {
		return dao.SubCountSelectList();
	}
	
	// 결제 후 강의 장바구니 삭제
	@Override
	public int ClassBuyAfter(int class_num, int user_num) {
	
		return dao.ClassBuyAfter(class_num, user_num);
	}

	@Override
	public List<ClassInfoDto> PopularReviewSelectList() {
		return dao.PopularReviewSelectList();
	}

	@Override
	public int QADelete(int qa_group_no) {
		return dao.QADelete(qa_group_no);
	}

	@Override
	public int QAReplyDelete(QADto dto) {
		return dao.QAReplyDelete(dto);
	}

	@Override
	public int selectTotalCountStream(String txt_search, String searchOption, int sub_num) {
		return dao.selectTotalCountStream(txt_search, searchOption, sub_num);
	}

	@Override
	public List<ClassInfoDto> selectListPageStream(int firstIndex, int recordCountPerPage, String txt_sesarch,
			String searchOption, int sub_num) {
		return dao.selectListPageStream(firstIndex, recordCountPerPage, txt_sesarch, searchOption, sub_num);
	}
	
	@Override
	public List<ClassReviewDto> ReviewList(int class_num) {
		return dao.ReviewList(class_num);
	}

	@Override
	public List<QADto> QAList(int class_num) {
		return dao.QAList(class_num);
	}

	@Override
	public int classInsertSubscribe(int user_num, int class_num) {
		return dao.classInsertSubscribe(user_num, class_num);
	}
	
}
