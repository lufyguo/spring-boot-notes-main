package com.ziorye.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziorye
 */
@RestController
public class AddCustomCounterToMeterRegistry {
    Counter counter;

    public AddCustomCounterToMeterRegistry(MeterRegistry meterRegistry) {
        counter = meterRegistry.counter("custom.customMetrics.viewsCount");
    }

    @GetMapping("/custom-metrics")
    String customMetrics() {
        counter.increment();
        return "custom metrics page";
    }

}
