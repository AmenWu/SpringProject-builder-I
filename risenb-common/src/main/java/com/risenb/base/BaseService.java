/**
 * 
 */
package com.risenb.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.risenb.bean.Page;

/**
 * <pre>
 * <p>Title: BaseService接口，提供通用的增删改查</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月15日 上午8:57:43 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public interface BaseService<T, ID extends Serializable> {

	/**
	 * 增加记录
	 * 
	 * @param record
	 * @return 受影响行数
	 */
	boolean insert(T record);

	/**
	 * 批量添加
	 * 
	 * @param list
	 * @return
	 */
	boolean batchInsert(List<T> list);

	/**
	 * 删除记录
	 * 
	 * @param id
	 *            主键id
	 * @return 受影响行数
	 */
	boolean delete(ID id);

	/**
	 * 删除记录(支持批量刪除)
	 * 
	 * @param ids
	 *            主键ids
	 * @return 受影响行数
	 */
	boolean batchDelete(List<ID> ids);

	/**
	 * 修改记录
	 * 
	 * @param record
	 * @return 受影响行数
	 */
	boolean update(T record);

	/**
	 * 根据主键id查询记录
	 * 
	 * @param id
	 * @return
	 */
	T getById(ID id);

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	List<T> getAll();

	/**
	 * 根据参数条件获取所有记录
	 * 
	 * @return
	 */
	List<T> getByParams(Map<String, Object> params);

	/**
	 * 分页查询记录
	 * 
	 * @param page
	 * @return
	 */
	Page<T> getListByPage(Page<T> page);
	/**
	 * 根据参数条件获取记录条数
	 * 
	 * @return
	 */
	int getCount(Map<String, Object> params);


}