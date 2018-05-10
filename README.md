# Apk Publish 
Upload packaged apk to specified platform

# Features
- Support upload to zupu

# Installation
This plugin require gradle 3.3 version
1. Add this property to you root gradle.build 
    ```
    buildscript {
        repositories {
            jcenter()
            maven{
                url "http://nexus.9527.cloud:9527/repository/zupu_test"
            }
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:2.3.3'
            classpath 'com.azrael.plugin:publish-apk:1.0.0'
            // NOTE: Do not place your application dependencies here; they belong
            // in the individual module build.gradle files
        }
    }
    ```
2. Add this property before `android` property to you gradle.build in `app` dir 
    ```
    apply plugin: 'com.azrael.apkpublish'
    
    publishConfig {
        zupu {
            name "1.0" //这个可以自己定义
            url "https://www.zupu.cn/api/v1/versions"
            clientId  "XXXXXXX"
            clientSecret  "XXXXXXX"
            grantType "client_credentials"
            grantScope "XXXXXXX"
            autoUpload true
            build 1
            type "Version::Android"
            description "这是一个描述部分"
        }
    }
    android{
        productFlavors {
            zupu {}
        }
    }
    ```
3. Then execute build command
    ```
    gradlew build
    ```
   You actually see a publishapk task group in the gradle panel
   ```
   ----publisapk
   ------ publish${channelName}Debug
   ------ publish${channelName}Release
   ```
4. Upload Apk 

    Upload the packaged APK file to the specified channel by executing the 'gradlew publish${channelName}Release' command
    
 
    

