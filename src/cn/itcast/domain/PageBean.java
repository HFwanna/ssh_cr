package cn.itcast.domain;

import java.util.List;

public class PageBean {
	private Integer pagesize; 
	private Integer currentpage;
	private List list;
	private Integer totalcount;
	private Integer totalpage;
	public PageBean(Integer pagesize, Integer currentpage, Integer totalcount) {
		this.pagesize = pagesize;
		this.currentpage = currentpage;
		this.totalcount = totalcount;
		if(this.pagesize==null){
			this.pagesize = 3;
		}
		if(this.currentpage==null){
			this.currentpage = 1;
		}
		//判断页数是否超出范围
		this.totalpage = (this.totalcount+this.pagesize-1)/this.pagesize; 
		if(this.currentpage<1){
			this.currentpage=1;
		}
		if(this.currentpage>totalpage){
			this.currentpage = totalpage;
		}
		
	}
	//功能函数：计算每页起始索引
	public int getStart(){
		return (currentpage-1)*pagesize;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	public Integer getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}
	
	
}
