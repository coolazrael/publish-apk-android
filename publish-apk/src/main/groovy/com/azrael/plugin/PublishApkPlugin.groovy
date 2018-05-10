package com.azrael.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

public class PublishApkPlugin implements Plugin<Project> {
    private Project project = null;
    static String EXTENSION_NAME = 'publishConfig'
    static String GROUP = "publishApk"
    static String PLUGIN_PREFIX = "publish"

    @Override
    void apply(Project project) {
        //接受外部参数
        this.project = project
        project.extensions.create(EXTENSION_NAME, PublishApkPluginExtension.class)
        if (project.android.hasProperty("applicationVariants")) {
            project.android.applicationVariants.all { variant ->
                String variantName = variant.name.capitalize()
                File apkFile = variant.outputs[0].outputFile
                Task task = project.tasks.create("publish$variantName", PublishTask) {
                    assertPath = apkFile.getAbsolutePath()
                }
                if(project.publishConfig[variantName.replace(PublishApkPlugin.PLUGIN_PREFIX, "").toLowerCase().replaceAll("(release)|(debug)","")].autoUpload){
                    println("dddddddddddddddddddddddd")
                    project.tasks["assemble$variantName"].finalizedBy task
                }
            }
        }
    }
}
