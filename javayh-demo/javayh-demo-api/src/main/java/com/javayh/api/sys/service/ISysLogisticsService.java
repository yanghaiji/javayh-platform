package com.javayh.api.sys.service;

import com.javayh.api.sys.pojo.SysLogistics;
import java.util.List;
import com.javayh.api.sys.pojo.dto.SysLogisticsDTO;
import com.javayh.mybatis.page.PageQuery;

/**
 * <p>  *  iService
 * </p>  * @author：Dylan
 * @version：V1.0
 * @since：2020-04-16
 */
public interface ISysLogisticsService {
	/**
	 *  根据条件查找-分页
	 */
	PageQuery<SysLogistics> findByPage (SysLogisticsDTO sysLogisticsDTO);

	/**
	 *  根据id查询
	 */
	SysLogistics findById(String id);


	/**
	 *  添加数据
	 */
	int insert(SysLogistics sysLogistics);


	/**
	 *  批量添加数据
	 */
	int batchInsert(List<SysLogistics> sysLogistics);

	/**
	 *  修改数据
	 */
	int update(SysLogistics sysLogistics);

	/**
	 *  根据id删除
	 */
	int deleteById(String id);

}
