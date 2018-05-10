package com.azrael.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Optional

public class PublishTask extends DefaultTask {

    @Optional
    Object variants
    private UploadBase submitForm
    private String channelName


    def PublishTask() {
        group PublishApkPlugin.GROUP
    }

    @TaskAction
    def publish() {
        channelName = getName().replace(PublishApkPlugin.PLUGIN_PREFIX, "").toLowerCase().replaceAll("(release)|(debug)","")
        if(project.publishConfig.hasProperty("$channelName")){
            def uploadConfig = project.publishConfig["$channelName"]
            submitForm = initUploadType(channelName, uploadConfig.url, uploadConfig)
            submitForm.doAction()
        }
    }

    def initUploadType(String channelName, String url, Object uploadConfig) {
        UploadBase submitFormType
        switch (channelName) {
            case 'zupu':
                submitFormType = new ZupuUpload(url)
                break;
        }
        submitFormType.fillParams(variants.outputs[0].outputFile, uploadConfig)
        return submitFormType;
    }
}