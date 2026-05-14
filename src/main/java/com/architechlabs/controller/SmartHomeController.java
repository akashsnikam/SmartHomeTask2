package com.architechlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.architechlabs.model.AutomationRule;
import com.architechlabs.model.Device;
import com.architechlabs.service.SmartHomeService;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SmartHomeController {

    @Autowired
    SmartHomeService service;

    @GetMapping("/devices")
    public Collection<Device> getDevices() {
        return service.getDevices();
    }

    @PostMapping("/devices")
    public void addDevice(@RequestBody Device d) {
        service.addDevice(d);
    }

    @PostMapping("/devices/{id}/control")
    public String control(@PathVariable String id,
                          @RequestBody Map<String, Object> state) {

        service.controlDevice(id, state);
        return "Device Updated";
    }

    @PostMapping("/rules")
    public String addRule(@RequestBody AutomationRule r) {
        service.addRule(r);
        return "Rule Added Successfully";
    }

    @GetMapping("/rules")
    public List<AutomationRule> getRules() {
        return service.getRules();
    }

    @PostMapping("/simulate-trigger/{id}")
    public void simulate(@PathVariable String id) {
        service.simulateTrigger(id);
    }
}