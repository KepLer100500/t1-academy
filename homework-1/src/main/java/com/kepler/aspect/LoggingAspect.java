package com.kepler.aspect;

import com.kepler.model.Well;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Aspect for logging behavior in various service methods.
 * This aspect intercepts method calls and logs relevant information.
 */
@Aspect
@Slf4j
@Component
public class LoggingAspect {

    /**
     * Inserts an empty line in the console after every service method execution,
     * excluding {@code saveRecord} method of {@code DatabaseService}.
     */
    @After("execution(* com.kepler.service..*(..)) && !execution(* com.kepler.service.DatabaseService.saveRecord(..))")
    public void insertBlankLineAfterMethod() {
        System.out.println();
    }

    /**
     * Logs method name and integer parameters for methods in service implementation packages
     * that accept an {@code Integer} as an argument.
     *
     * @param joinPoint provides reflective access to both the state and behavior of the advised method.
     */
    @Before("execution(* com.kepler.service.impl.ModbusServiceImpl.*(Integer))")
    public void logReadingNameAndRegister(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String nameMethod = methodSignature.getName().replace("read", "");
        log.info("Считываемый параметр: {}", nameMethod);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Integer) {
                log.info("Регистр: {}", arg);
            }
        }
    }

    /**
     * Logs the returned value after the execution of any method in {@code ModbusService}.
     *
     * @param value the returned value from the method.
     */
    @AfterReturning(pointcut = "execution(* com.kepler.service.impl.ModbusServiceImpl.*(Integer))", returning = "value")
    public void logReturnedValue(Double value) {
        log.info("Значение: {}", value);
    }

    /**
     * Logs information before and after the execution of {@code saveRecord} in {@code DatabaseService}.
     * It logs the well information being saved to the database.
     *
     * @param joinPoint provides reflective access to the method being called.
     * @return the result of method execution.
     * @throws Throwable if the method throws an exception.
     */
    @Around("execution(* com.kepler.service.DatabaseService.saveRecord(..))")
    public Object logDatabase(ProceedingJoinPoint joinPoint) throws Throwable {
        Well well = (Well) joinPoint.getArgs()[0];
        log.info("Добавление записи '{}' в базу данных. Данные были получены: {}", well.getUuid(), well.getDateTime());
        Object result = joinPoint.proceed();
        log.info("Запись добавлена\n");
        return result;
    }

    /**
     * Logs errors when methods annotated with {@code LogErrorExecution} throw an exception.
     *
     * @param ex the exception thrown by the method.
     */
    @AfterThrowing(pointcut = "@annotation(com.kepler.annotation.LogErrorExecution)", throwing = "ex")
    public void logOPCDA(Exception ex) {
        log.error("Ошибка при работе с OPCDA сервером: {}", ex.getMessage());
    }

    /**
     * Logs the authorization process on the OPCDA server.
     * This method is triggered before executing any method matched by the
     * {@link com.kepler.aspect.LoggingPointcut#readPLCPointcut()} pointcut.
     * It logs an informational message about the authorization attempt on the OPCDA server.
     */
    @Before("com.kepler.aspect.LoggingPointcut.readPLCPointcut()")
    public void logAuthOPCDA() {
        log.info("Авторизация на OPCDA сервере");
    }
}
