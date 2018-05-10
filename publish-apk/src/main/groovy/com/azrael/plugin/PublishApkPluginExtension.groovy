package com.azrael.plugin

public class PublishApkPluginExtension {
    String message
    public PublishApkPluginExtension(){
        this.extensions.create("zupu",ZupuEntity)
    }
}