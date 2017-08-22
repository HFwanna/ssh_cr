package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Customer;

public interface CustomerDao {

	List<Customer> getPageList(DetachedCriteria dc, Integer start, Integer pagesize);
	Integer getTotalCount(DetachedCriteria dc);
}
