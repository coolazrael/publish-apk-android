package com.azrael.plugin

public class ZupuEntity extends BaseEntity {
    String clientId
    String clientSecret
    int build
    String type
    boolean published
    String grantType
    String grantScope

    boolean getPublished() {
        return published
    }

    void setPublished(boolean published) {
        this.published = published
    }

    String getClientId() {
        return clientId
    }

    void setClientId(String clientId) {
        this.clientId = clientId
    }

    String getClientSecret() {
        return clientSecret
    }

    void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret
    }

    int getBuild() {
        return build
    }

    void setBuild(int build) {
        this.build = build
    }

    String getType() {
        return type
    }

    void setType(String type) {
        this.type = type
    }
}