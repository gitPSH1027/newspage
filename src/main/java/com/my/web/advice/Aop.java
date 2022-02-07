package com.my.web.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aop {
	
	@Before("execution(* com.my.web.controller.*.*(..))")//Pointcuts
	public void beforeLogService(JoinPoint jp)
	{
		System.out.println("Before: "+jp.getSignature().toShortString() + " : " + Arrays.toString(jp.getArgs()));
	}
	
	//리턴값 출력
	//정상 수행후
	@AfterReturning(pointcut = "execution(* com.my.web.service.*.*(..))",returning = "rObj")
	public void afterLogService(JoinPoint jp, Object rObj)
	{
		if(rObj != null) {
			System.out.println("Return: "+jp.getSignature().toShortString() + " : " + rObj.toString());
		}
		else {
			System.out.println("Return: X");
		}
		
	}
}
