package com.payn.ink.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 登录切面
 *
 * @author: payn
 * @date: 2020/8/18 16:03
 */
@Aspect
@Component
public class LoginAspect {

	private Logger logger = LoggerFactory.getLogger(LoginAspect.class);

	/**
	 * 配置切入点表达式
	 */
	@Pointcut("execution(public * com.payn.ink.controller.*.*(..))")
	public void pointCut_() {
	}

	/**
	 * 前置
	 */
	@Before("pointCut_()")
	public void doBefore(JoinPoint joinPoint) {
		//获取request，记录请求内容
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		logger.info("———————————————————————————————————————————————————————————————————————————————");
	}

}
