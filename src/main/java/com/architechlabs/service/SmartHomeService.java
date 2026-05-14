package com.architechlabs.service;

import org.springframework.stereotype.Service;
import com.architechlabs.model.AutomationRule;
import com.architechlabs.model.Device;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SmartHomeService {

    private Map<String, Device> devices = new HashMap<>();
    private List<AutomationRule> rules = new ArrayList<>();

    // GET DEVICES
    public Collection<Device> getDevices() {
        return devices.values();
    }

    // ADD DEVICE
    public void addDevice(Device d) {
        if (d.getCurrentState() == null) {
            d.setCurrentState(new HashMap<>());
        }
        d.setLastUpdated(LocalDateTime.now());
        devices.put(d.getId(), d);
    }

    // CONTROL DEVICE (FIXED)
    public void controlDevice(String id, Map<String, Object> state) {

        Device d = devices.get(id);
        if (d == null) return;

        Map<String, Object> currentState = d.getCurrentState();

        if (currentState == null) {
            currentState = new HashMap<>();
        }

        // merge incoming state
        currentState.putAll(state);

        // IMPORTANT: store COPY to avoid shared reference bug
        d.setCurrentState(new HashMap<>(currentState));
        d.setLastUpdated(LocalDateTime.now());

        // pass FULL updated state to rules
        evaluateRules(id, d.getCurrentState());
    }

    // ADD RULE
    public void addRule(AutomationRule r) {
        r.setActive(true);
        rules.add(r);
    }

    // RULE ENGINE (FIXED)
    private void evaluateRules(String deviceId,
                               Map<String, Object> state) {

        for (AutomationRule r : rules) {

            if (!r.isActive()) continue;

            if (!r.getTriggerDeviceId().equals(deviceId)) continue;

            Object actualValue = state.get(r.getTriggerKey());

            if (actualValue == null) continue;

            if (!actualValue.toString().trim()
                    .equalsIgnoreCase(r.getTriggerValue().trim())) {
                continue;
            }

            Device target = devices.get(r.getActionDeviceId());

            if (target == null) continue;

            Map<String, Object> targetState = target.getCurrentState();

            if (targetState == null) {
                targetState = new HashMap<>();
            } else {
                targetState = new HashMap<>(targetState);
            }

            targetState.put(r.getActionKey(), r.getActionValue());

            target.setCurrentState(targetState);
            target.setLastUpdated(LocalDateTime.now());

            System.out.println("RULE EXECUTED -> " + target.getName());
        }
    }

    // SIMULATE TRIGGER
    public void simulateTrigger(String deviceId) {
        Device d = devices.get(deviceId);
        if (d != null && d.getCurrentState() != null) {
            evaluateRules(deviceId, d.getCurrentState());
        }
    }

    public List<AutomationRule> getRules() {
        return rules;
    }
}