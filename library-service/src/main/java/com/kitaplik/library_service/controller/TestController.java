package com.kitaplik.library_service.controller;

import io.micrometer.tracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired(required = false)
    private Tracer tracer;

    @Autowired(required = false)
    private io.micrometer.observation.ObservationRegistry observationRegistry;

    @Autowired
    private org.springframework.context.ApplicationContext applicationContext;

    @GetMapping("/trace-test")
    public String test() {
        String tracerBeans = String.join(", ", applicationContext.getBeanNamesForType(Tracer.class));
        return String.format("Tracer: %s, ObservationRegistry: %s, All Tracer Beans: [%s]", 
            tracer != null ? "OK" : "NULL", 
            observationRegistry != null ? "OK" : "NULL",
            tracerBeans);
    }
}
