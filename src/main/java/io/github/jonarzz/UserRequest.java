package io.github.jonarzz;

import java.util.List;

public class UserRequest {

    private String name;
    private List<String> entities;

    public static UserRequest builder() {
        return new UserRequest();
    }

    public String getUserName() {
        return name;
    }

    public List<String> getUserEntities() {
        return entities;
    }

    public UserRequest userName(String name) {
        this.name = name;
        return this;
    }

    public UserRequest userEntities(List<String> entities) {
        this.entities = entities;
        return this;
    }

    public UserRequest build() {
        return this;
    }
}
