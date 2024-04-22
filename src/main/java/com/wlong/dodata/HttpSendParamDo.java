package com.wlong.dodata;

import java.util.Map;

public class HttpSendParamDo implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private String getOrPost;
    private String url;
    private String wirteFileStr;
    private String isJsonParams;

    private String isReaderCookie;
    private String isSetCookie;
    private String cookieStr;
    private String contentType;

    private Map<String,String> otherParames;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGetOrPost() {
        return getOrPost;
    }

    public void setGetOrPost(String getOrPost) {
        this.getOrPost = getOrPost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWirteFileStr() {
        return wirteFileStr;
    }

    public void setWirteFileStr(String wirteFileStr) {
        this.wirteFileStr = wirteFileStr;
    }

    public Map<String, String> getOtherParames() {
        return otherParames;
    }

    public void setOtherParames(Map<String, String> otherParames) {
        this.otherParames = otherParames;
    }

    public String getIsJsonParams() {
        return isJsonParams;
    }

    public void setIsJsonParams(String isJsonParams) {
        this.isJsonParams = isJsonParams;
    }

    public String getIsReaderCookie() {
        return isReaderCookie;
    }

    public void setIsReaderCookie(String isReaderCookie) {
        this.isReaderCookie = isReaderCookie;
    }

    public String getCookieStr() {
        return cookieStr;
    }

    public void setCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
    }

    public String getIsSetCookie() {
        return isSetCookie;
    }

    public void setIsSetCookie(String isSetCookie) {
        this.isSetCookie = isSetCookie;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
