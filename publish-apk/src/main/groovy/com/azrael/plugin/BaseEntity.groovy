package com.azrael.plugin


public class BaseEntity {
    String url
    String resource
    String description
    String name
    boolean autoUpload = true

    String getUrl() {
        return url
    }

    void setUrl(String url) {
        this.url = url
    }

    String getResource() {
        return resource
    }

    void setResource(String resource) {
        this.resource = resource
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}