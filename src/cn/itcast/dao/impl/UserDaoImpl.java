package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.BaseDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
//继承hibernate模板，然后想用这个模板想要创建出来需要sessionfactory，
//所以HibernateDaoSupport要为Dao注入sessionFactroy
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

//public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@Override
	public User getByUserCode(final String usercode) {
		// HQL
//		return getHibernateTemplate().execute(new HibernateCallback<User>() {
//
//			@Override
//			public User doInHibernate(Session session) throws HibernateException {
//				String hql ="from User where user_code = ?";
//				Query query = session.createQuery(hql);
//				query.setParameter(0, usercode);
//				User user = (User) query.uniqueResult();
//				return user;
//			}
//		});
		
		//Criteria 
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_code",usercode));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
//
//	@Override
//	public void save(User u) {
//		// TODO Auto-generated method stub
//		getHibernateTemplate().save(u);
//	}

}
