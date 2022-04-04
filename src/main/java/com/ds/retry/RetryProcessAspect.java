package com.ds.retry;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author hanfeng
 */
@Component
@Aspect
public class RetryProcessAspect {
    protected org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    // 配置RetryableProcess.class路径
    @Pointcut("@annotation(com.ds.retry.RetryableProcess)")
    public void pointCutR() {
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 埋点拦截器具体实现
     */
    @Around("pointCutR()")
    public Object methodRHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        RetryableProcess retryableProcess = targetMethod.getAnnotation(RetryableProcess.class);
        BackoffProcess backoff = retryableProcess.backoff();
        RetryableRedisProcess redisOperation = backoff.redisOperation();
        int maxAttempts = retryableProcess.maxAttempts();
        long sleepSecond = backoff.value();
        double multiplier = backoff.multiplier();
        if (multiplier <= 0) {
            multiplier = 1;
        }
        Exception ex = null;
        int retryCount = 0;
        do {
            try {

                Object proceed = joinPoint.proceed();
                return proceed;
            } catch (BtException e) {
                logger.info("等待{}毫秒", sleepSecond);
                Thread.sleep(sleepSecond);
                retryCount++;
                sleepSecond = (long) (multiplier) * sleepSecond;
                if (sleepSecond > backoff.maxDelay()) {
                    sleepSecond = backoff.maxDelay();
                    logger.info("等待时间太长，更新为{}毫秒", sleepSecond);
                }

                List<String> strings = Arrays.asList(backoff.retryExceptionCode());
                if (backoff.retryExceptionCode().length > 0 && !strings.contains(e.getErrorEnum().getCode())) {
                    throw e;
                }
                ex = e;
                redisOperation(redisOperation);
            }
        } while (retryCount <= maxAttempts);

        throw ex;
    }

    /**
     * redis操作
     * @param redisOperation
     */
    public void redisOperation(RetryableRedisProcess redisOperation){
        String[] redisRemoves = redisOperation.retryRedisRemove();
        for (String redisName : redisRemoves) {
            redisTemplate.delete(redisName);
        }

    }



}