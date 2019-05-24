package com.kitri.dto;

import java.util.List;


public class PageBean {

	private int countPerPage = 10;		// 페이지 별 보여줄 목록 수
	private int startRow = 1;			// 시작 행
	private int endRow = 1;				// 끝 행
	private List<RepBoard> list;		// 목록
	private int totalPage = 1;			// 총 페이지 수
	private int totalCount;				// 총 게시글 수
	private int countPerPageGroup = 3;	// 페이지 그룹에 보여줄 페이지 수
	private int startPage;				// 페이지 그룹의 시작 페이지
	private int endPage;				// 페이지 그룹의 끝 페이지
	private String url;					// 페이지 링크 클릭시 요청할 URL
	private int currentPage;			// 현재 페이지
	
	
	public PageBean() {
		
	}

	public PageBean(int countPerPage, int totalCount, int countPerPageGroup, String url, int currentPage) {
		super();
		this.countPerPage = countPerPage;
		this.totalCount = totalCount;
		this.countPerPageGroup = countPerPageGroup;
		this.url = url;
		this.currentPage = currentPage;
		
		execute();
	}
	
	public void execute() {
		// to get List<board>
		startRow = (currentPage - 1) * countPerPage + 1;
		endRow = currentPage * countPerPage;
		
		totalPage = ((totalCount - 1) / countPerPage) + 1;
		
		startPage = ((currentPage - 1) / countPerPageGroup) * countPerPageGroup + 1;
		endPage = startPage + countPerPageGroup - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}

	
	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public List<RepBoard> getList() {
		return list;
	}

	public void setList(List<RepBoard> list) {
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCountPerPageGroup() {
		return countPerPageGroup;
	}

	public void setCountPerPageGroup(int countPerPageGroup) {
		this.countPerPageGroup = countPerPageGroup;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
