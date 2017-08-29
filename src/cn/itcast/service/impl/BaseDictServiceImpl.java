package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.domain.BaseDict;
import cn.itcast.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {
	
	private BaseDictDao bdd;
	
	
	public void setBdd(BaseDictDao bdd) {
		this.bdd = bdd;
	}


	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		// TODO Auto-generated method stub
		return bdd.getListByTypeCode(dict_type_code);
	}

}
