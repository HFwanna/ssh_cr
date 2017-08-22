package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Customer;
import cn.itcast.domain.PageBean;

public interface CustomerService {
	PageBean getPageBean(DetachedCriteria dc, Integer pageSize, Integer currentPage);
}
