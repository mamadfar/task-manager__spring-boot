package com.leetjourney.taskmanager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController 
@RequestMapping("/api/${api.version}/info")
public class InfoController {
    
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.max-tasks-per-page}")
    private int maxTasksPerPage;

    @GetMapping
    public Map<String, Object> getAppInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("appName", appName);
        info.put("appVersion", appVersion);
        info.put("maxTasksPerPage", maxTasksPerPage);
        return info;
    }
    
}
