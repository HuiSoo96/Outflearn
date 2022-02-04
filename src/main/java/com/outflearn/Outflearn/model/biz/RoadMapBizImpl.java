package com.outflearn.Outflearn.model.biz;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.CommentDto;
import com.outflearn.Outflearn.dto.MainStreamDto;
import com.outflearn.Outflearn.dto.RoadMapInfoDto;
import com.outflearn.Outflearn.dto.RoadUserCombineDto;
import com.outflearn.Outflearn.dto.SubStreamDto;
import com.outflearn.Outflearn.model.dao.RoadMapDao;

@Service
public class RoadMapBizImpl implements RoadMapBiz {
	
	@Inject
	SqlSessionTemplate session;
	
	@Inject
	RoadMapDao dao;

	@Override
	public int insertRoadMap(RoadMapInfoDto dto) {
		
		return dao.insertRoadMap(dto);
	}

	@Override
	public int selectSQ() {
		
		return dao.selectSQ();
	}

	@Override
	public int insertroadNclass(String[] class_num, String seq) {
		
		return dao.insertroadNclass(class_num, seq);
	}

	@Override
	public List<MainStreamDto> mainStreamList() {
		
		return dao.mainStreamList();
	}

	@Override
	public List<SubStreamDto> subStreamList() {
		
		return dao.subStreamList();
	}
	
	@Override
	public List<ClassInfoDto> classInfoList(String[] subFilter) {
		
		return dao.classInfoList(subFilter);
	}

	@Override
	public List<RoadMapInfoDto> roadMapList() {
		
		return dao.roadMapList();
	}

	@Override
	public RoadMapInfoDto selectOneRoadMap(String roadNum) {
	
		return dao.selectOneRoadMap(roadNum);
	}

	@Override
	public List<Integer> RoadMapConList(String roadNum) {
		
		return dao.RoadMapConList(roadNum);
	}

	@Override
	public List<ClassInfoDto> RoadClassInfoList(List<Integer> list) {
		
		return dao.RoadClassInfoList(list);
	}

	@Override
	public int roadMapSubscribeInsert(String roadNum, String userNum) {
		
		return dao.roadMapSubscribeInsert(roadNum, userNum);
	}

	@Override
	public int updateRoadSubscribe(String roandNum) {
		
		return dao.updateRoadSubscribe(roandNum);
	}

	@Override
	public int roadJoinChk(String roadNum, String userNum) {
		
		return dao.roadJoinChk(roadNum, userNum);
	}

	@Override
	public int roadMapSubscribeDelete(String roadNum, String userNum) {
		
		return dao.roadMapSubscribeDelete(roadNum, userNum);
	}

	@Override
	public int deleteRoadSubscribe(String roadNum) {
		
		return dao.deleteRoadSubscribe(roadNum);
	}

	@Override
	public List<RoadUserCombineDto> roadMapComList() {
		
		return dao.roadMapComList();
	}

	@Override
	public RoadUserCombineDto roadMapComSelectOne(String roadNum) {
		
		return dao.roadMapComSelectOne(roadNum);
	}
	
	@Override
	public List<RoadMapInfoDto> selectListPage(int firstIndex, int recordCountPerPage, String txt_search,
			String searchOption) {
		return dao.selectListPage(firstIndex, recordCountPerPage, txt_search, searchOption);
	}

	@Override
	public int selectTotalCountRoadMap(String txt_search, String searchOption, int main_num) {
		
		return dao.selectTotalCountRoadMap(txt_search, searchOption , main_num);
	}

	@Override
	public List<Integer> SubBaList(String userNum) {
	
		return dao.SubBaList(userNum);
	}

	@Override
	public int AddToCart(List<String> cartArray, String userNum) {
		
		return dao.AddToCart(cartArray, userNum);
	}


	@Override
	public List<RoadUserCombineDto> roadMapComList(int firstIndex, int recordCountPerPage, String txt_search,
			String searchOption , int main_num) {
		// TODO Auto-generated method stub
		return dao.roadMapComList(firstIndex, recordCountPerPage, txt_search, searchOption, main_num);
	}

	@Override
	public int roadMapUpdate(RoadMapInfoDto dto) {
		
		return dao.roadMapUpdate(dto);
	}

	@Override
	public int DeleteroadConBeforeUpdate(String roadNum) {
		
		return dao.DeleteroadConBeforeUpdate(roadNum);
	}

	@Override
	public int addComment(CommentDto dto) {
		
		return dao.addComment(dto);
	}

	@Override
	public List<CommentDto> commentList(String roadNum) {
	
		return dao.commentList(roadNum);
	}

	@Override
	public int commentUpdate(String content, String ComNum) {
		
		return dao.commentUpdate(content, ComNum);
	}

	@Override
	public int deleteComment(String comNum) {
		
		return dao.deleteComment(comNum);
	}

	@Override
	public int reCommentSqUpdate(CommentDto dto) {
	
		return dao.reCommentSqUpdate(dto);
		
	}

	@Override
	public int reCommentAdd(CommentDto dto) {
		
		return dao.reCommentAdd(dto);
	}

	@Override
	public List<Integer> getSubnumList(List<Integer> list) {
		
		return dao.getSubnumList(list);
	}

	@Override
	public List<String> getsubnumName(List<Integer> list) {
		
		return dao.getsubnumName(list);
	}

	


}
