package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.sun.glass.ui.Application;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.impl.CustomerDaoImpl;
import cn.itcast.domain.Customer;
import cn.itcast.domain.PageBean;
import cn.itcast.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao cd ;
	
	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}

	@Override
	public PageBean getPageBean(DetachedCriteria dc,Integer currentPage,Integer pageSize) {
		//1.调用dao查询总记录数
		int totalCount = cd.getTotalCount(dc);
		//2.创建PageBean对象
		PageBean pb = new PageBean(pageSize,currentPage, totalCount);
		//3.调用Dao查询分页列表数据
		pb.setList(cd.getCustomerList(dc, pb.getStart(), pb.getPagesize()));
		//4.返回对象
		return pb;
	}



}
