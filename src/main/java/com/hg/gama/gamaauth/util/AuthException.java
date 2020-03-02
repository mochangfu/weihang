package com.hg.gama.gamaauth.util;

public class AuthException extends RuntimeException {

    private int code;
    private String errMsg;

    public AuthException(int code, String errMsg, Exception e) {
        super(errMsg, e);
        this.code = code;
        this.errMsg = errMsg;
    }

    public AuthException(int code) {
        this.code = code;
    }

    public AuthException(int code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
