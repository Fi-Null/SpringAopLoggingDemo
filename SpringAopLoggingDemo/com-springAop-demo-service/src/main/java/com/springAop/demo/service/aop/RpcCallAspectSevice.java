package com.springAop.demo.service.aop;

import com.google.gson.Gson;
import com.springAop.demo.domain.annotation.AopCallSuccessJudge;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RpcCallAspectSevice {
    private Logger logger = LoggerFactory.getLogger(RpcCallAspectSevice.class);
    private static Gson gson = new Gson();

    // 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut(value = "execution(public * com.springAop.demo.rpc..*.*(..))")
    public void aspect() {
    }

    @Around("aspect()")
    public Object call(JoinPoint joinPoint) {
        ProceedingJoinPoint pjp = (ProceedingJoinPoint) joinPoint;
        String methodLong = pjp.getSignature().toLongString();
        logger.info("methodLong: {},", methodLong);
        try {
            String umpKey = pjp.getTarget().getClass().getPackage().getName() + "." + StringUtils.substringBefore(pjp.getSignature().toShortString(), "(");
            AopCallSuccessJudge aopCallSuccessJudge = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AopCallSuccessJudge.class);
            System.out.println(aopCallSuccessJudge.value());
            Object result = pjp.proceed();

            logger.info("umpKey : {}, rpc call[{}],入参:{},返回值:{}", umpKey, methodLong, gson.toJson(pjp.getArgs()), gson.toJson(result));
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
