package com.learning.pm.aspect;

import com.learning.pm.config.PerformanceProperties;
import com.learning.pm.detector.SuspiciousArgumentDetector;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
public class PerformanceMonitorAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitorAspect.class);
    private final PerformanceProperties properties;

    private final SuspiciousArgumentDetector suspiciousArgumentDetector;

    public PerformanceMonitorAspect(PerformanceProperties properties,
                                    SuspiciousArgumentDetector suspiciousArgumentDetector) {
        this.properties = properties;
        this.suspiciousArgumentDetector = suspiciousArgumentDetector;
    }

    @Around("execution(* com.learning.pm.service..*(..))")
    public Object profileServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.toShortString();
        Object[] args = joinPoint.getArgs();

        logger.info("{} executed in {} ms", methodName, duration);

        if (duration > properties.getThresholdMs()) {
            List<String> suspiciousArgs = Arrays.stream(args)
                    .map(suspiciousArgumentDetector::detect)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            logger.warn("⚠️ Method {} took {} ms (threshold: {} ms). Args: {}", methodName, duration, properties.getThresholdMs(), Arrays.toString(args));
            if (!suspiciousArgs.isEmpty()) {
                logger.warn("🚨 Possible cause(s): {}", String.join(", ", suspiciousArgs));
            }

            triggerAlert(methodName, duration, args, suspiciousArgs);
        }

        return result;
    }

    private void triggerAlert(String methodName, long duration, Object[] args, List<String> cause) {
        // Simulated alert system (replace with email or Slack logic later)
        logger.error("🚨 ALERT: {} took {} ms. Cause: {}. Args: {}",
                methodName, duration, cause, Arrays.toString(args));
    }
}


