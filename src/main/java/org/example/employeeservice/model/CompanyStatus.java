package org.example.employeeservice.model;

public enum CompanyStatus {
    CHAMPION("Чемпион"),
    SILVER("Серебро"),
    BRONZE("Бронза"),
    NONE("Нет статуса");

    private final String displayName;

    CompanyStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 