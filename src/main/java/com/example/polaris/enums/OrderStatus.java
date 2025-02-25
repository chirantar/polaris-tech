package com.example.polaris.enums;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum OrderStatus {
    PLACED,
    ACCEPTED,
    REJECTED,
    IN_PROGRESS,
    COMPLETED
}

