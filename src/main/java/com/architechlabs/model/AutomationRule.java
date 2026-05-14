package com.architechlabs.model;

import lombok.Data;
@Data
public class AutomationRule {

    private String id;
    private String name;

    private String triggerDeviceId;
    private String triggerCondition;

    private String actionDeviceId;
    private String actionType;

    private boolean isActive = true;

    private String triggerKey;
    private String triggerValue;

    private String actionKey;
    private String actionValue;
}