package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public Integer getTotalCount(DetachedCriteria dc) {
		//这里是我自己想用sql查询代码，因为传进来是离线查询dc对象，dc貌似没有拆分属性方法，
		//所以我这里如果想用sql，需要接受一个属性cust_name，才行，故这里这种方法不适用
//		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
//			@Override
//			public Integer doInHibernate(Session session) throws HibernateException {
//				String sql = "select count(cust_id) from cst_customer where cust_name like '%?%'";
//				SQLQuery query =session.createSQLQuery(sql);
//				query.setParameter(sql, dc);
//				int rowcount = (int) query.uniqueResult();
//				return rowcount;
//			}
//		});
		//查询聚合函数，总记录数
		dc.setProjection(Projections.rowCount());
		//获取模板,调用criteria查询方法
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		//清空第一步设置的聚合函数，因为在service层第三条语句又使用了dc这个对象，所以要还原
		dc.setProjection(null);
		//从list中取出总数结果,list是一个对象，list！=null保证list地址不为空，
		//这样保障后面的list.size（）>0判断中，加入list不存在而出现的空指针异常，其次，主要是通过
		//list.size判断了list中元素是否为空
		if(list!=null && list.size()>0){
			Long count = list.get(0);
			return count.intValue();
		}else{
			return null;
		}
		
		//自己尝试代码，因为觉得list如果为空，返回的也是空，所以效果应该和上面一样
		//return list.get(0).intValue();
		
		
	}

	@Override
	public List<Customer> getCustomerList(DetachedCriteria dc, int start, int pagesize) {
		List<Customer> list = (List<Customer>)getHibernateTemplate().findByCriteria(dc, start, pagesize);
		System.out.println(list);
		return list;
	}

}
