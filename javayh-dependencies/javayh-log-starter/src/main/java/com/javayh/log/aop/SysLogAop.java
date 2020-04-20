package com.javayh.log.aop;

import com.alibaba.fastjson.JSONObject;
import com.javayh.common.constant.ConstantUtils;
import com.javayh.common.util.IPUtils;
import com.javayh.common.util.RandomUtil;
import com.javayh.common.util.servlet.RequestUtils;
import com.javayh.log.annotation.SysLog;
import com.javayh.log.entity.OperationLog;
import com.javayh.log.log.LogError;
import com.javayh.log.mapper.LogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 * 日志记录Aop
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 23:49
 */
@Slf4j
@Aspect
@Order(-1)
public class SysLogAop {

	private static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	private volatile int size = 200;
	private static CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

	@Autowired
	private TaskExecutor taskExecutor;

	@Autowired(required = false)
	private LogError logError;

	@Autowired(required = false)
	private LogMapper logInfoApi;

	@Around("@annotation(sysLog)")
	public Object getLog(ProceedingJoinPoint joinPoint, SysLog sysLog) throws Throwable {
		Object proceed = null;
		long time = System.currentTimeMillis();
		try {
			proceed = joinPoint.proceed();
			time = System.currentTimeMillis() - time;
			return proceed;
		}
		catch (Throwable throwable) {
			logError.logPrint("SysLogAop", throwable.getStackTrace());
			throw throwable;
		}
		finally {
			// 方法执行后
			addLogAspect(joinPoint, proceed, time);
		}
	}

	private void addLogAspect(ProceedingJoinPoint joinPoint, Object proceed, long time) {
		HttpServletRequest request = RequestUtils.getRequest();
		// 判断返回值是否为空
		if (!ObjectUtils.allNotNull()) {
		}
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		OperationLog operationLog = new OperationLog();
		// 消耗的时间
		operationLog.setRunTime(time);
		// UID
		operationLog.setId(RandomUtil.generateOrderCode());
		// 参数 防止参数中有不能序列化的对象 而报错
		Object[] args = joinPoint.getArgs();
		List<Object> params = new ArrayList<>();
		for (Object arg : args) {
			if (arg instanceof ServletRequest || arg instanceof ServletResponse
					|| arg instanceof MultipartFile) {
				continue;
			}
			params.add(arg);
		}
		operationLog.setArgs(JSONObject.toJSONString(params));
		// 创建时间
		operationLog.setCreateTime(DateFormatUtils.format(new Date(), YMDHMS));
		// 类名
		String className = methodSignature.getDeclaringTypeName() + "."
				+ methodSignature.getName();
		operationLog.setMethod(className);
		// 获取注解
		SysLog annotation = methodSignature.getMethod().getAnnotation(SysLog.class);
		if (annotation != null) {
			// 描述
			operationLog.setDescribe(annotation.detail());
			// 等级
			operationLog.setLevel(annotation.level());
			// 调用方ip
			operationLog.setCallerIp(request.getRemoteAddr());
			// 本地ip
			operationLog.setLocalHostIp(IPUtils.getHostIp());
			// 获取当前用户信息 TODO
			operationLog.setUserId("1234");
			operationLog.setUserName("javayh");
			operationLog.setMode(annotation.value());
			// 判断是否持久化
			boolean requestParam = annotation.recordRequestParam();
			save(requestParam,operationLog,className,time);
		}
	}

	/**
	 * <p>
	 *      持久化操作
	 * </p>
	 * @version 1.0.0
	 * @author Dylan
	 * @since 2020/4/16
	 * @param requestParam	标识
	 * @param operationLog	内容
	 * @param className		类名
	 * @return void
	 */
	private void save(boolean requestParam,OperationLog operationLog,String className,long time){
//		copyOnWriteArrayList.add(operationLog);
		// FIXME: 2020/4/17
        StringBuilder sb = new StringBuilder();
		if (requestParam && copyOnWriteArrayList.size() >= size) {
			// 进行日志的保存
			CompletableFuture.runAsync(() -> {
				try {
					log.trace("日志落库开始：{}", operationLog);
					// 持久化
					logInfoApi.batchInsert(copyOnWriteArrayList);
					log.trace("开始落库结束：{}", operationLog);
				}
				catch (Exception e) {
					logError.logPrint("落库失败：{}", e.getStackTrace());
				}

			}, taskExecutor);
		}else {
            sb.append(ConstantUtils.NEWLINE).append(ConstantUtils.PREFIX).append(className).append("-> 执行耗时: ").append(time).append("s");
            sb.append(ConstantUtils.NEWLINE).append(ConstantUtils.PREFIX).append("操作记录:").append(operationLog);
            log.info(sb.toString());
		}

	}

}
