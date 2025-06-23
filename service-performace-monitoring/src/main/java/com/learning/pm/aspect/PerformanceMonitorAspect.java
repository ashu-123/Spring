package com.learning.pm.aspect;

import com.learning.pm.config.PerformanceProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
public class PerformanceMonitorAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    private final PerformanceProperties properties;

    public PerformanceMonitorAspect(PerformanceProperties properties) {
        this.properties = properties;
    }

    @Around("execution(* com.example.service..*(..))")
    public Object profileServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.toShortString();
        Object[] args = joinPoint.getArgs();

        logger.info("{} executed in {} ms", methodName, duration);

        if (duration > properties.getThresholdMs()) {
            StringBuilder suspiciousArgs = new StringBuilder();

            for (Object arg : args) {
                if (arg instanceof String str && str.length() > 100) {
                    suspiciousArgs.append("Large String (length=").append(str.length()).append("), ");
                } else if (arg instanceof Collection<?> col && col.size() > 10) {
                    suspiciousArgs.append("Large Collection (size=").append(col.size()).append("), ");
                } else if (arg instanceof Map<?, ?> map && map.size() > 10) {
                    suspiciousArgs.append("Large Map (size=").append(map.size()).append("), ");
                } else if (arg != null && arg.getClass().getSimpleName().toLowerCase().contains("file")) {
                    suspiciousArgs.append("File-like Object: ").append(arg.getClass().getSimpleName()).append(", ");
                }
            }

            logger.warn("⚠️ Method {} took {} ms which exceeds the threshold of {} ms. Args: {}",
                    methodName, duration, properties.getThresholdMs(), Arrays.toString(args));

            if (!suspiciousArgs.isEmpty()) {
                logger.warn("🚨 Possible cause: {}", suspiciousArgs);
            }

            // Simulating alert (==> replace with actual email/Slack call)
            triggerAlert(methodName, duration, args);
        }

        return result;
    }

    private void triggerAlert(String methodName, long duration, Object[] args) {
        // Simulated alert - in real app, integrate with email, Slack, etc.
        logger.error("🚨 ALERT: {} took {} ms with args: {}", methodName, duration, Arrays.toString(args));
        // TODO: Replace with actual alert mechanism (mail service / Slack webhook / monitoring tool)
    }
}

