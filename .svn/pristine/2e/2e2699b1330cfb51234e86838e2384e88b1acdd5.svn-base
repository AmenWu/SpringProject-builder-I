/**
 * 
 */
package com.risenb.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.risenb.bean.Page;

/**
 * <pre>
 * <p>Title: BaseDao接口，提供通用的增删改查</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing Risenb Technology Co.,Ltd. </p>
 * <p>Build: 2017年8月22日 上午11:28:40 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public interface BaseMapper<T, ID extends Serializable> {

	/**
	 * 增加记录
	 * 
	 * @param record
	 * @return 受影响行数
	 */
	int insert(T record);

	/**
	 * 批量添加
	 * 
	 * @param list
	 * @return
	 */
	int batchInsert(@Param("list") List<T> list);

	/**
	 * 删除记录
	 * 
	 * @param id 主键id
	 * @return 受影响行数
	 */
	int delete(ID id);

	/**
	 * 删除记录(支持批量刪除)
	 * 
	 * @param ids
	 *            主键ids
	 * @return 受影响行数
	 */
	int batchDelete(@Param("ids") List<ID> ids);

	/**
	 * 修改记录
	 * 
	 * @param record
	 * @return 受影响行数
	 */
	int update(T record);

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
	List<T> getListByPage(Page<T> page);

	/**
	 * 根据参数条件获取记录条数
	 * 
	 * @return
	 */
	int getCount(Map<String, Object> params);

}