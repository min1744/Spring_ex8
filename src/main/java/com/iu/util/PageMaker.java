package com.iu.util;

public class PageMaker {
	private int perPage = 10;
	private Integer curPage;
	private String kind;
	private String search;
	
	//rownum
	private int startRow;
	private int lastRow;
	
	//page
	private int totalBlock;
	private int curBlock;
	private int startNum;
	private int lastNum;
	
	public int getTotalBlock() {
		return totalBlock;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public int getStartNum() {
		return startNum;
	}
	public int getLastNum() {
		return lastNum;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public Integer getCurPage() {
		if(curPage == null) {
			this.curPage = 1;
		}
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
		if(curPage == null) {
			this.curPage = 1;
		}
	}
	public String getKind() {
		if(this.search == null) {
			this.search = "";
		}
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	//startRow, LastRow
	public void makeRow() {
		this.startRow = (this.getCurPage()-1)*perPage + 1;
		this.lastRow = curPage*perPage;
	}
	
	public void makePage(int totalCount) {
		//1. 전체 글의 갯수
		//2. totalPage 구하기
		int totalPage=totalCount/perPage;
		if(totalCount%perPage != 0) {
			totalPage++;
		}
		
		//3. totalBlock
		int perBlock = 5;
		this.totalBlock=totalPage/perBlock;
		if(totalPage%perBlock != 0) {
			this.totalBlock++;
		}
		
		//4. curPage를 이용해서 curBlock
		this.curBlock=curPage/perBlock;
		if(this.curPage%perBlock != 0) {
			this.curBlock++;
		}
		
		//5. startNum, lastNum
		this.startNum=(this.curBlock-1)*perBlock+1;
		this.lastNum = this.curBlock*perBlock;
		
		//6. 현재 블럭이 마지막 블럭일 때
		if(this.curBlock == totalBlock) {
			this.lastNum=totalPage;
		}
	}
}