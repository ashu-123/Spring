package com.learning.pm.detector;

import com.learning.pm.config.PerformanceProperties;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
public class DefaultSuspiciousArgumentDetector implements SuspiciousArgumentDetector {

    private final PerformanceProperties props;

    public DefaultSuspiciousArgumentDetector(PerformanceProperties props) {
        this.props = props;
    }

    @Override
    public Optional<String> detect(Object arg) {
        if (arg == null) return Optional.empty();

        return switch (arg) {
            case String s when s.length() > props.getMaxStringLength() ->
                    Optional.of("Large String (length=" + s.length() + ")");
            case Collection<?> c when c.size() > props.getMaxCollectionSize() ->
                    Optional.of("Large Collection (size=" + c.size() + ")");
            case Map<?, ?> m when m.size() > props.getMaxMapSize() -> Optional.of("Large Map (size=" + m.size() + ")");
            case Object o when o.getClass().getSimpleName().toLowerCase().contains("file") ->
                    Optional.of("File-like Object: " + o.getClass().getSimpleName());
            default -> Optional.empty();
        };
    }
}

