/**
 * 版权所属： Java有货
 * 作者：杨海吉
 * 版本：V1.0
 * 创建日期：2020-04-05
 * 修改日期：2020-04-05
 */
package com.javayh.api.syslogistics.service;

import com.javayh.api.syslogistics.pojo.SysLogistics;
import com.javayh.api.syslogistics.pojo.dto.SysLogisticsDTO;
import com.javayh.common.page.PageQuery;

/**
 * <p>  *  iService
 * </p>  * @author：Dylan
 * @version：V1.0
 * @since：2020-04-05
 */
public interface ISysLogisticsService {
	/**
	 *  根据条件查找-分页
	 */
	PageQuery<SysLogistics> findByPage (SysLogisticsDTO sysLogisticsDTO);

	/**
	 *  根据id查询
	 */
	SysLogistics findById(Integer id);


	/**
	 *  添加数据
	 */
	int insert(SysLogistics sysLogistics);

	/**
	 *  修改数据
	 */
	int update(SysLogistics sysLogistics);

	/**
	 *  根据id删除
	 */
	int deleteById(Integer id);

}
