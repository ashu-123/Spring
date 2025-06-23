package com.learning.pm.detector;

import java.util.Optional;

public interface SuspiciousArgumentDetector {
    Optional<String> detect(Object arg);
}

