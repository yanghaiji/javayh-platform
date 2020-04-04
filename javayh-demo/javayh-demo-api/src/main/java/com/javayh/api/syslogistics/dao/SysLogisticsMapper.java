/**
 * 版权所属： Java有货
 * 作者：杨海吉
 * 版本：V1.0
 * 创建日期：2020-04-05
 * 修改日期：2020-04-05
 */
package com.javayh.api.syslogistics.dao;

import com.javayh.api.syslogistics.pojo.SysLogistics;
import com.javayh.api.syslogistics.pojo.dto.SysLogisticsDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * <p>  *  iMapper
 * </p>  * @author：Dylan
 * @version：V1.0
 * @since：2020-04-05
 */
@Mapper
public interface SysLogisticsMapper{

	/**
	 *  分页查询
	 */
	List<SysLogistics> findByPage( SysLogisticsDTO sysLogisticsDTO);


	/**
	 *  根据id查找
	 */
	SysLogistics findById(@Param("id") Integer id);

	/**
	 *  新增
	 */
	int insert(SysLogistics sysLogistics);

	/**
	 *  修改
	 */
	int update(SysLogistics sysLogistics);

	/**
	 *  根据id删除
	 */
	int deleteById(@Param("id") Integer id);
}
