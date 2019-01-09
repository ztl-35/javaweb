package edu.sxu.aop;


import org.springframework.stereotype.Component;

//@Aspect
@Component(value="log")
public class Log {
	//@Pointcut(value="execution(* edu.sxu.service.userService.*.*(..))")
	public void myPoint() {}
	
	//@Before("myPoint()")
	public void mybefore() {
		System.out.println("start method.....");
	}
	//@AfterReturning("myPoint()")
	public void myend() {
		System.out.println("end method.....");
	}
}
