package com.azrael.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Optional

public class PublishTask extends DefaultTask {

    @Optional
    String assertPath

    private UploadBase submitForm
    private String channelName


    def PublishTask() {
        group PublishApkPlugin.GROUP
    }

    @TaskAction
    def publish() {
        channelName = getName().replace(PublishApkPlugin.PLUGIN_PREFIX, "").toLowerCase().replaceAll("(release)|(debug)","")
        def params = project.publishConfig["$channelName"]
        submitForm = initUploadType(channelName, params.url, params)
        submitForm.doAction()
    }

    def initUploadType(String channelName, String url, Object params) {
        UploadBase submitFormType
        switch (channelName) {
            case 'zupu':
                submitFormType = new ZupuUpload(url)
                break;
        }
        submitFormType.fillParams(new File(assertPath), params)
        return submitFormType;
    }
}