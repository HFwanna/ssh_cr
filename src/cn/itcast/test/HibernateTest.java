package cn.itcast.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	@Resource(name="sessionFactory")
	private SessionFactory sf;

	@Test
	//hibernate原生方法测试，没加入spring容器
	public void fun1(){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//----------------------------------------------------
		User u = new User();
		u.setUser_code("tommy");
		u.setUser_name("汤米");
		u.setUser_password("666");
		session.save(u);
		//----------------------------------------------------
		tx.commit();
		session.close();
		sf.close();
	}
	
	@Test
	public void fun2(){
		//通过修改spring的配置，在sessionFactory中加入
		//<property name="configLocation" value="classpath:hibernate.cfg.xml"></property>
		//这样，一下利用spring容器获取sessionfactory对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		sf = (SessionFactory) ac.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//----------------------------------------------------
		User u = new User();
		u.setUser_code("tommy");
		u.setUser_name("汤米");
		u.setUser_password("666");
		session.save(u);
		//----------------------------------------------------
		tx.commit();
		session.close();
		sf.close();
	}
	
	@Test
	//使用注解方式联系
	public void fun3(){
		//通过修改spring的配置，在sessionFactory中加入
		//<property name="configLocation" value="classpath:hibernate.cfg.xml"></property>
		//这样，一下利用spring容器获取sessionfactory对象
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//----------------------------------------------------
		User u = new User();
		u.setUser_code("tommy");
		u.setUser_name("汤米");
		u.setUser_password("666");
		session.save(u);
		//----------------------------------------------------
		tx.commit();
		session.close();
		sf.close();
	}
	
	
	//（注解）测试使用spring管理连接池方式的数据，因为使用spring管理，所以
	// 事务操作也可以交给spring统一管理，这里的测试就取消掉tx部分
	/**
	 * 在applicationContext.xml中配置：
	<!-- 读取db.properties文件 -->
	<context:property-placeholder location="classpath:db.properties"/>
	<!-- 配置c3p0连接池 -->
	<bean name="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="jdbcUrl" value="${jdbc.jdbcUrl}" ></property>
		<property name="driverClass" value="${jdbc.driverClass}" ></property>
		<property name="user" value="${jdbc.user}" ></property>
		<property name="password" value="${jdbc.password}" ></property>
	</bean>
	
	 * 删除<bean>sessionFactory中的
	 * <!-- <prop key="hibernate.connection.driver_class">com.mysql.jdbc.Driver</prop>
			<prop key="hibernate.connection.url">jdbc:mysql:///test</prop>
			<prop key="hibernate.connection.username">root</prop>
			<prop key="hibernate.connection.password">123</prop> -->
	 * 
	 */
	@Test
	public void fun4(){
		//通过修改spring的配置，在sessionFactory中加入
		//<property name="configLocation" value="classpath:hibernate.cfg.xml"></property>
		//这样，一下利用spring容器获取sessionfactory对象
		Session session = sf.openSession();
		//Transaction tx = session.beginTransaction();
		//----------------------------------------------------
		User u = new User();
		u.setUser_code("tommy");
		u.setUser_name("汤米");
		u.setUser_password("666");
		session.save(u);
		//----------------------------------------------------
		//tx.commit();
		session.close();
		sf.close();
	}
	
	@Resource(name="userDao")
	private UserDao ud;
	@Test
	//测试dao模板
	public void fun5(){
		User user = ud.getByUserCode("tommy");
		System.out.println(user);
	}
	
	@Resource(name="userService")
	private UserService us;
	@Test
	//测试事务
	public void fun6(){
		User u = new User();
		u.setUser_code("弹弹");
		u.setUser_name("d");
		u.setUser_password("123456");
		us.saveUser(u);
	}
	
}
