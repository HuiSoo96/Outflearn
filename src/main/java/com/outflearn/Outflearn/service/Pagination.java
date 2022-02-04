package com.outflearn.Outflearn.service;

public class Pagination {

	//게시글 수
	private int pageSize;
	
	//처음 페이지 번호
	private int firstPageNo;
	
	//이전 페이지 번호
	private int prevPageNo;
	
	//시작페이지
	private int startPageNo;
	
	//페이지 번호
	private int pageNo;
	
	//끝 페이지
	private int endPageNo;
	
	//다음 페이지 번호
	private int nextPageNo;
	
	//마지막 페이지 번호
	private int finalPageNo;
	
	//게시 글 전체 수
	private int totalCount;
	
	

	public Pagination() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getFinalPageNo() {
		return finalPageNo;
	}

	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.makePaging();
	}

	private void makePaging() {
		//게시글 전체 수가 없는 경우
		if(this.totalCount == 0) return;
		
		//기본값
		if(this.pageNo == 0 ) this.setPageNo(1);
	
		//기본값
		if(this.pageSize == 0) this.setPageSize(10);
		
		//마지막 페이지
		int finalPage = (totalCount + (pageSize -1)) / pageSize;
		//마지막 페이지 기본값 설정
		if(this.pageNo > finalPage) this.setPageNo(finalPage);
	
		//현재 페이지 유효성 체크
		if(this.pageNo < 0 || this.pageNo > finalPage) this.pageNo = 1;
	
		//시작페이지 (전체)
		boolean isNowFirst = pageNo == 1 ? true : false; 
		
		//마지막페이지
		boolean isNowFinal = pageNo == finalPage? true : false;
		
		//시작페이지(페이징 네비 기준)
		int startPage = ((pageNo -1)/5) * 5 + 1;
		
		//끝 페이지(페이징 네비 기준)
		int endPage = startPage + 5 - 1;
	
		//마지막 페이지 (페이징 네비기준) > 마지막 페이지
		if(endPage> finalPage) {
			endPage = finalPage;
		}
	
		//첫번째 페이지 번호
		this.setFirstPageNo(1);
		
		//이전 페이지 번호
		if(isNowFirst) {
			this.setPrevPageNo(1);
		} else {
			this.setPrevPageNo(((pageNo - 1) < 1 ? 1 : (pageNo -1)));
		}
	
		//시작 페이지(페이징 네비 기준)
		this.setStartPageNo(startPage);
		
		//끝 페이지(페이징 네비 기준)
		this.setEndPageNo(endPage);
		
		if(isNowFinal) {
			this.setNextPageNo(finalPage);//다음페이지 번호
		} else {
			this.setNextPageNo(((pageNo + 1) > finalPage ? finalPage : (pageNo + 1)));//다음페이지 번호
		}
		
		this.setFinalPageNo(finalPage);
	}
	
	
}
