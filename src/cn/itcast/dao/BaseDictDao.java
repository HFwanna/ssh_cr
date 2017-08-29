package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict> {
	//根据数据字典类型字段获得数据字典对象
	List<BaseDict> getListByTypeCode(String dict_type_code);
}
