package com.azrael.plugin

public abstract class UploadBase {
    private HttpURLConnectionUtil connectionUtil

    UploadBase(String url) {
        connectionUtil = new HttpURLConnectionUtil(url, Constants.HTTPMETHOD_POST);
    }

    def abstract doAction()

    def abstract fillParams(File resource, Object params)

    HttpURLConnectionUtil getConnectionUtil() {
        return connectionUtil
    }

    void setConnectionUtil(HttpURLConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil
    }

    @Override
    String toString() {
        def string = this.class.name + "{"
        connectionUtil.getTextParams().each {
            string += "$it.key='$it.value',"
        }
        string += "}"
        return string
    }
}