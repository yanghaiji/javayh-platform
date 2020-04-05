/**
 * 版权所属： Java有货
 * 作者：杨海吉
 * 版本：V1.0
 * 创建日期：2020-04-05
 * 修改日期：2020-04-05
 */
package com.javayh.api.syslogistics.controller;

import com.javayh.common.result.ResultData;
import com.javayh.mybatis.page.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.javayh.api.syslogistics.pojo.SysLogistics;
import com.javayh.api.syslogistics.pojo.dto.SysLogisticsDTO;
import com.javayh.api.syslogistics.service.ISysLogisticsService;

/**
 * <p>  *  Controller
 * </p>  * @author：Dylan
 * @version：V1.0
 * @since：2020-04-05
 */
@RestController
@Api(tags = "")
@RequestMapping("/sys/logistics")
public class SysLogisticsController {

	@Autowired
	private ISysLogisticsService sysLogisticsServiceImpl; 

	/**
	 *  根据条件查找-分页
	 */
	@ApiOperation(value = "根据条件查找-分页")
	@PostMapping(value = "/findByPage")
	public ResultData<PageQuery> findByPage (@RequestBody SysLogisticsDTO sysLogisticsDTO){
		return ResultData.success(sysLogisticsServiceImpl.findByPage(sysLogisticsDTO));
	}

	/**
	 *  根据id查询
	 */
	@ApiOperation(value = "根据id查询")
	@GetMapping(value = "/findById")
	public ResultData<SysLogistics> findById(String id){
		return ResultData.success(sysLogisticsServiceImpl.findById(id));
	}

	/**
	 *  添加数据
	 */
	@ApiOperation(value = "添加数据")
	@PostMapping(value = "/insert")
	public ResultData<Integer> insert(@RequestBody SysLogistics sysLogistics){
		sysLogisticsServiceImpl.insert(sysLogistics);
		return ResultData.success();
	}

	/**
	 *  修改数据
	 */
	@ApiOperation(value = "修改数据")
	@PutMapping(value = "/update")
	public ResultData<Integer> update(@RequestBody SysLogistics sysLogistics){
		sysLogisticsServiceImpl.update(sysLogistics);
		return ResultData.success();
	}

	/**
	 *  根据id删除
	 */
	@ApiOperation(value = "根据id删除")
	@DeleteMapping(value = "/deleteById")
	public ResultData<Integer> deleteById(Integer id){
		sysLogisticsServiceImpl.deleteById(id);
		return ResultData.success();
	}

}
