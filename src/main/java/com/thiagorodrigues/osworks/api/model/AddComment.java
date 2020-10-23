package com.thiagorodrigues.osworks.api.model;

import javax.validation.constraints.NotBlank;

public class AddComment {

    @NotBlank
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
