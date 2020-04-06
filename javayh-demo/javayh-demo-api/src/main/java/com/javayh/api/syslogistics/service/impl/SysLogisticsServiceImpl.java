/**
 * 版权所属： Java有货
 * 作者：杨海吉
 * 版本：V1.0
 * 创建日期：2020-04-05
 * 修改日期：2020-04-05
 */
package com.javayh.api.syslogistics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javayh.mybatis.page.PageQuery;
import com.github.pagehelper.PageHelper;
import java.util.List;

import com.javayh.api.syslogistics.pojo.SysLogistics;
import com.javayh.api.syslogistics.pojo.dto.SysLogisticsDTO;
import com.javayh.api.syslogistics.dao.SysLogisticsMapper;
import com.javayh.api.syslogistics.service.ISysLogisticsService;

/**
 * <p>
 * * ServiceImpl
 * </p>
 * * @author：Dylan @version：V1.0 @since：2020-04-05
 */
@Service
public class SysLogisticsServiceImpl implements ISysLogisticsService {

	@Autowired
	private SysLogisticsMapper sysLogisticsMapper;

	/**
	 * 根据条件查询-分页
	 */
	@Override
	public PageQuery<SysLogistics> findByPage(SysLogisticsDTO sysLogisticsDTO) {
		PageHelper.startPage(sysLogisticsDTO.getPageNo(), sysLogisticsDTO.getPageSize());
		List<SysLogistics> sysLogisticsList = sysLogisticsMapper
				.findByPage(sysLogisticsDTO);
		PageQuery page = PageQuery.ofPage(sysLogisticsList);
		return page;
	}

	/**
	 * 根据id查询
	 */
	@Override
	public SysLogistics findById(String id) {
		return sysLogisticsMapper.findById(id);
	}

	/**
	 * 添加数据
	 */
	@Override
	public int insert(SysLogistics sysLogistics) {
		return sysLogisticsMapper.insert(sysLogistics);
	}

	/**
	 * 修改数据
	 */
	@Override
	public int update(SysLogistics sysLogistics) {
		return sysLogisticsMapper.update(sysLogistics);
	}

	/**
	 * 根据id删除
	 */
	@Override
	public int deleteById(Integer id) {
		return sysLogisticsMapper.deleteById(id);
	}

}
