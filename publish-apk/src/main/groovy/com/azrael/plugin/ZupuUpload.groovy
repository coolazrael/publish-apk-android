package com.azrael.plugin

import groovy.json.JsonSlurper

public class ZupuUpload extends UploadBase {
    private Object infoConfig
    private String AUTHORITYURL = "https://www.zupu.cn/oauth/token"

    ZupuUpload(String url) {
        super(url)
    }

    @Override
    def doAction() {
        println("###############################################")
        before{
            access_token ->
                upload(access_token)
        }
        println(" request => " + toString())
        println("###############################################")
    }

    private void upload(String access_token){
        getConnectionUtil().addTextParameter("access_token",access_token)
        println(getConnectionUtil().getTextParams())
        getConnectionUtil().post{
            println("#  upload success  #")
        }{
            statusCode,error ->
                println("# status -> $statusCode #")
                println("# error -> $error #")
        }
    }

    private void before(Closure callback) {
        HttpURLConnectionUtil authorityPost = new HttpURLConnectionUtil(AUTHORITYURL, Constants.HTTPMETHOD_POST)
        authorityPost.addTextParameter("client_id", infoConfig.clientId)
        authorityPost.addTextParameter("client_secret", infoConfig.clientSecret)
        authorityPost.addTextParameter("grant_type", infoConfig.grantType)
        authorityPost.addTextParameter("scope", infoConfig.grantScope)
        authorityPost.post {
            result ->
                def data = new JsonSlurper().parseText(result)
                callback.call(data.access_token)
        } {
            statusCode,error ->
                println("#status_code => $statusCode  error ->$error#")
        }
    }

    @Override
    def fillParams(File resource, Object params) {
        this.infoConfig = params
        getConnectionUtil().addTextParameter("build", String.valueOf(params.build))
        getConnectionUtil().addTextParameter("type", params.type)
        getConnectionUtil().addTextParameter("name", params.name)
        getConnectionUtil().addTextParameter("description", params.description)
        getConnectionUtil().addTextParameter("published", String.valueOf(params.published))
        getConnectionUtil().addFileParameter("resource", resource)
        return getConnectionUtil();
    }
}