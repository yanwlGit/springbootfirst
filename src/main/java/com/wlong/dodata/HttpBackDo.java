package com.wlong.dodata;

public class HttpBackDo  implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private String returnContentText;
    private String status;
    private String writeFileStr;

    private String cookieStr;

    public String getReturnContentText() {
        return returnContentText;
    }

    public void setReturnContentText(String returnContentText) {
        this.returnContentText = returnContentText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWriteFileStr() {
        return writeFileStr;
    }

    public void setWriteFileStr(String writeFileStr) {
        this.writeFileStr = writeFileStr;
    }

    public String getCookieStr() {
        return cookieStr;
    }

    public void setCookieStr(String cookieStr) {
        this.cookieStr = cookieStr;
    }
}
