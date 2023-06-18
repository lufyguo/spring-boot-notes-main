package com.ziorye.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author ziorye
 */
@Component
@Endpoint(id = "custom-endpoint", enableByDefault = true)
public class CustomEndpoint {
    @ReadOperation
    public Map readOperation() {
        return Collections.singletonMap("CustomEndpoint", "readOperation");
    }

    @WriteOperation
    public Map writeOperation() {
        return Collections.singletonMap("CustomEndpoint", "writeOperation");
    }
}
