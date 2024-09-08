package com.hoavd.fbstore.common.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingInfoAspect {
  @Before("execution(* com.hoavd.fbstore.admin.service.*.*.*.*(..))")
  public void logBeforeMethodAdminExecution() {
    System.out.println("A method in the service package Admin is about to be executed");
  }

  @Before("execution(* com.hoavd.fbstore.customer.service.*.*.*.*(..))")
  public void logBeforeMethodCustomerExecution() {
    System.out.println("A method in the service package Customer is about to be executed");
  }

  @After("execution(* com.hoavd.fbstore.admin.service.*.*.*.*(..))")
  public void audit(JoinPoint joinPoint) {
    // Audit logic (e.g., logging the method name, arguments, etc.)
    System.out.println("Auditing method: " + joinPoint.getSignature().getName());
  }
}
