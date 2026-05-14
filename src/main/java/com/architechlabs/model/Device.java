package com.architechlabs.model;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class Device {
    private String id;
    private String name;
    private String type;
    private String room;
    private Map<String, Object> currentState;
    private LocalDateTime lastUpdated;

}