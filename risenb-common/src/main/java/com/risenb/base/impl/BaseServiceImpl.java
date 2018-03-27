package com.risenb.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.risenb.base.BaseMapper;
import com.risenb.base.BaseService;
import com.risenb.bean.Page;

/**
 * <pre>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月22日 上午11:39:53 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	public abstract BaseMapper<T, ID> getBaseMapper();

	@Override
	public boolean insert(T record) {
		return getBaseMapper().insert(record) > 0 ? true : false;
	}

	@Override
	public boolean batchInsert(List<T> list) {
		return getBaseMapper().batchInsert(list) > 0 ? true : false;
	}

	@Override
	public boolean delete(ID id) {
		return getBaseMapper().delete(id) > 0 ? true : false;
	}

	@Override
	public boolean batchDelete(List<ID> ids) {
		return getBaseMapper().batchDelete(ids) > 0 ? true : false;
	}

	@Override
	public boolean update(T record) {
		return getBaseMapper().update(record) > 0 ? true : false;
	}

	@Override
	public T getById(ID id) {
		return getBaseMapper().getById(id);
	}

	@Override
	public List<T> getAll() {
		return getBaseMapper().getAll();
	}

	@Override
	public List<T> getByParams(Map<String, Object> params) {
		return getBaseMapper().getByParams(params);
	}

	@Override
	public Page<T> getListByPage(Page<T> page) {
		return page.setResults(getBaseMapper().getListByPage(page));
	}

	@Override
	public int getCount(Map<String, Object> params) {
		return getBaseMapper().getCount(params);
	}

}
