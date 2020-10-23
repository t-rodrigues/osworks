package com.thiagorodrigues.osworks.domain.exception;

public class EntityNotFound extends DomainException {

    private static final long serialVersionUID = 1L;

    public EntityNotFound(String message) {
        super(message);
    }

}
