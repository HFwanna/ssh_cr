package cn.itcast.web.action;


import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.domain.BaseDict;
import cn.itcast.service.BaseDictService;
import net.sf.json.JSONArray;

public class BaseDictAction extends ActionSupport {
	
	private String dict_type_code;
	
	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	
	private BaseDictService baseDictService;
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	@Override
	public String execute() throws Exception {
		// 1.调用service根据typecode获取list对象
		List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);
		// 2.将list转换成json格式
		String json = JSONArray.fromObject(list).toString();
		// 3.将json对象发送到浏览器
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}
	
}
