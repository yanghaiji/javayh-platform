package com.javayh.api.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import java.util.List;
import com.javayh.mybatis.page.PageQuery;
import com.javayh.api.sys.pojo.SysLogistics;
import com.javayh.api.sys.pojo.dto.SysLogisticsDTO;
import com.javayh.api.sys.dao.SysLogisticsMapper;
import com.javayh.api.sys.service.ISysLogisticsService;

/**
 * <p>  *  ServiceImpl
 * </p>  * @author：Dylan
 * @version：V1.0
 * @since：2020-04-16
 */
@Service
public class SysLogisticsServiceImpl implements ISysLogisticsService {

	@Autowired
	private SysLogisticsMapper sysLogisticsMapper; 

	/**
	 *  根据条件查询-分页
	 */
	@Override
	public PageQuery<SysLogistics> findByPage (SysLogisticsDTO sysLogisticsDTO){
		PageHelper.startPage(sysLogisticsDTO.getPageNo(),sysLogisticsDTO.getPageSize());
		List<SysLogistics> sysLogisticsList = sysLogisticsMapper.findByPage(sysLogisticsDTO);
		PageQuery page = PageQuery.ofPage(sysLogisticsList);
		return page;
	}

	/**
	 *  根据id查询
	 */
	@Override
	public SysLogistics findById(String id){
		return (SysLogistics)sysLogisticsMapper.findById(id);
	}

	/**
	 *  添加数据
	 */
	@Override
	public int insert(SysLogistics sysLogistics){
		return sysLogisticsMapper.insert(sysLogistics);
	}

	/**
	 *  添加数据
	 */
	@Override
	public int batchInsert(List<SysLogistics> sysLogistics){
		return sysLogisticsMapper.batchInsert(sysLogistics);
	}

	/**
	 *  修改数据
	 */
	@Override
	public int update(SysLogistics sysLogistics){
		return sysLogisticsMapper.update(sysLogistics);
	}

	/**
	 *  根据id删除
	 */
	@Override
	public int deleteById(String id){
		return sysLogisticsMapper.deleteById(id);
	}

}
