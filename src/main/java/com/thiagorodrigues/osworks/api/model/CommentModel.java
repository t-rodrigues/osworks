package com.thiagorodrigues.osworks.api.model;

import java.time.OffsetDateTime;

public class CommentModel {

    private Long id;
    private String description;
    private OffsetDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

}
