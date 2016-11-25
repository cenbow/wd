package com.okwei.datasource;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import com.okwei.datasource.DataSourceSwitcher;

public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
	// service方法执行之前被调用
	public void before(Method method, Object[] args, Object target) throws Throwable {
//		System.out.println("切入点: " + target.getClass().getName() + "类中" + method.getName() + "方法");
		String methodname=method.getName().toLowerCase();
		if(methodname.startsWith("add") 
			|| methodname.startsWith("create")
			|| methodname.startsWith("save")
			|| methodname.startsWith("edit")
			|| methodname.startsWith("update")
			|| methodname.startsWith("delete")
			|| methodname.startsWith("remove")
			|| methodname.startsWith("insert")
			|| methodname.startsWith("Insert")
			|| methodname.startsWith("UP")
			|| methodname.startsWith("new")
			|| methodname.startsWith("set")
			|| methodname.startsWith("change")
			|| methodname.startsWith("batch")
			|| methodname.startsWith("submit")
			|| methodname.startsWith("del")
			|| methodname.startsWith("reset")
			|| methodname.startsWith("billing")
			|| methodname.startsWith("transfer")
			|| methodname.startsWith("audit")){
//			System.out.println("切换到: master");
			DataSourceSwitcher.setMaster();
		}
		else  {
//			System.out.println("切换到: slave");
			DataSourceSwitcher.setSlave();
		}
	}

	// service方法执行完之后被调用
	public void afterReturning(Object arg0, Method method, Object[] args, Object target) throws Throwable {
	}

	// 抛出Exception之后被调用
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
		DataSourceSwitcher.setSlave();
//		System.out.println("出现异常,切换到: slave");
	}

}

