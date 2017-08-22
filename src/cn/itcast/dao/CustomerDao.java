package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface CustomerDao {

	List getCustomerList(DetachedCriteria dc, int start, int pagesize);
	Integer getTotalCount(DetachedCriteria dc);
}
