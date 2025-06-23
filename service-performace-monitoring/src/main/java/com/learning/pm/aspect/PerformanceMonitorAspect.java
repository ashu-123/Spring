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

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.toShortString();
        Object[] args = joinPoint.getArgs();

        logger.info("{} executed in {} ms", methodName, duration);

        if (duration > properties.getThresholdMs()) {
            String suspiciousArgs = Arrays.stream(args)
                    .map(this::describeSuspiciousArg)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("None");

            logger.warn("⚠️ Method {} took {} ms (threshold: {} ms). Args: {}", methodName, duration, properties.getThresholdMs(), Arrays.toString(args));
            if (!"None".equals(suspiciousArgs)) {
                logger.warn("🚨 Possible cause: {}", suspiciousArgs);
            }

            triggerAlert(methodName, duration, args, suspiciousArgs);
        }

        return result;
    }

    private Optional<String> describeSuspiciousArg(Object arg) {
        if (arg == null) return Optional.empty();

        return switch (arg) {
            case String s when s.length() > 100 -> Optional.of("Large String (length=" + s.length() + ")");
            case Collection<?> c when c.size() > 10 -> Optional.of("Large Collection (size=" + c.size() + ")");
            case Map<?, ?> m when m.size() > 10 -> Optional.of("Large Map (size=" + m.size() + ")");
            case Object o when o.getClass().getSimpleName().toLowerCase().contains("file") ->
                    Optional.of("File-like Object: " + o.getClass().getSimpleName());
            default -> Optional.empty();
        };
    }

    private void triggerAlert(String methodName, long duration, Object[] args, String cause) {
        // Simulated alert system (replace with email or Slack logic later)
        logger.error("🚨 ALERT: {} took {} ms. Cause: {}. Args: {}",
                methodName, duration, cause, Arrays.toString(args));
    }
}


