package com.leetjourney.taskmanager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leetjourney.taskmanager.config.AppProperties;

@RestController 
@RequestMapping("/api/${api.version}/info/advanced")
public class InfoControllerAdvanced {
    
    private final AppProperties appProperties;

    public InfoControllerAdvanced(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping
    public Map<String, Object> getAppInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("appName", appProperties.getName());
        info.put("appVersion", appProperties.getVersion());
        info.put("maxTasksPerPage", appProperties.getMaxTasksPerPage());
        return info;
    }
}
