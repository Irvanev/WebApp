package com.example.webapp.dtos.brands;

import java.time.LocalDateTime;

public class UpdateBrandDto {
    private String name;
    private LocalDateTime modified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
