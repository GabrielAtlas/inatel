package com.inatel.projeto.objects;

import java.util.UUID;

public class Todo {

    private final UUID uuid;
    private String message;

    public Todo(UUID uuid, String message) {
        this.uuid = uuid;
        this.message = message;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
