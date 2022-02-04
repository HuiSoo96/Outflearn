package com.outflearn.Outflearn.model.biz;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.outflearn.Outflearn.dto.ClassInfoDto;
import com.outflearn.Outflearn.dto.CommentDto;
import com.outflearn.Outflearn.dto.MainStreamDto;
import com.outflearn.Outflearn.dto.RoadMapInfoDto;
import com.outflearn.Outflearn.dto.RoadUserCombineDto;
import com.outflearn.Outflearn.dto.SubStreamDto;

@Service
public interface RoadMapBiz {

	public int insertRoadMap(RoadMapInfoDto dto);
	
	public int selectSQ();
	
	public int insertroadNclass(@RequestParam String[] class_num, @RequestParam String seq);
	
	public List<MainStreamDto> mainStreamList();
	
	public List<SubStreamDto> subStreamList() ;	
	
	public List<ClassInfoDto> classInfoList(String[] subFilter);
	
	public List<RoadMapInfoDto> roadMapList();
	
	public RoadMapInfoDto selectOneRoadMap(String roadNum);
	
	public List<Integer> RoadMapConList(String roadNum);
	
	public List<ClassInfoDto> RoadClassInfoList(List<Integer> list);
	
	public int roadMapSubscribeInsert(String roadNum, String userNum);
	
	public int updateRoadSubscribe(String roadNum);
	
	public int roadJoinChk(String roadNum, String userNum);
	
	public int roadMapSubscribeDelete(String roadNum, String userNum);
	
	public int deleteRoadSubscribe(String roadNum);
	
	public List<RoadUserCombineDto> roadMapComList();
	
	public RoadUserCombineDto roadMapComSelectOne(String roadNum);

	public List<RoadMapInfoDto> selectListPage(int firstIndex, int recordCountPerPage, String txt_search, String searchOption);
	
	public int selectTotalCountRoadMap(String txt_search, String searchOption, int main_num);

	public List<Integer> SubBaList(String userNum);
	
	public int AddToCart(List<String>cartArray, String userNum);
	
	public int roadMapUpdate(RoadMapInfoDto dto);
	
	public int DeleteroadConBeforeUpdate(String roadNum);
	
	public int addComment(CommentDto dto);
	
	public List<CommentDto> commentList(String roadNum);
	
	public int commentUpdate(String content, String ComNum);
	
	public int deleteComment(String comNum);
	
	public int reCommentSqUpdate (CommentDto dto);
	
	public int reCommentAdd (CommentDto dto);
	
	public List<Integer> getSubnumList(List<Integer> list);
	
	public List<String> getsubnumName(List<Integer> list);

	List<RoadUserCombineDto> roadMapComList(int firstIndex, int recordCountPerPage, String txt_search,
			String searchOption, int main_num);
	
}
