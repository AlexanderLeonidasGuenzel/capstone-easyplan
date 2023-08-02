package de.neuefische.easyplan.backend.utils;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class IDGenerator {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
