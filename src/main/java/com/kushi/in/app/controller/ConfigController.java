package com.kushi.in.app.controller;

import com.kushi.in.app.service.AwsSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "*")
public class ConfigController {

    @Autowired
    private AwsSsmService awsSsmService;

    /**
     * Get analytics configuration from AWS SSM
     * Frontend calls this on app load to get tracking IDs
     */
    @GetMapping("/analytics")
    public ResponseEntity<Map<String, String>> getAnalyticsConfig() {
        Map<String, String> config = new HashMap<>();
        
        config.put("googleAnalyticsId", awsSsmService.getGoogleAnalyticsId());
        config.put("facebookPixelId", awsSsmService.getFacebookPixelId());
        
        return ResponseEntity.ok(config);
    }
}
