package edu.miu.cs.cs544.najeeb.midterm.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("execution(* edu.miu.cs.cs544.najeeb.midterm.service.CRUD_Service.create(..))")
    public void logCreate() {
        System.out.println("AOP create");
    }
}
