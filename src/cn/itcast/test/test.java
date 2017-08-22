package cn.itcast.test;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.web.action.UserAction;

public class test {
	@Test
	public void mucn1(){
		//该方法调用struts的方法，这里还没有配置struts过滤器的话获取的内容就为空，所以测试用第二种方法
		ServletContext sc = ServletActionContext.getServletContext();
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		UserAction ua = (UserAction) ac.getBean("userAction");
		System.out.println(ua);
	}
	@Test
	public void mucn2(){
		//1.创建容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.向容器要“user”对象
		UserAction ua = (UserAction) ac.getBean("userAction");
		//3.打印user对象
		System.out.println(ua);
	}

}
	
