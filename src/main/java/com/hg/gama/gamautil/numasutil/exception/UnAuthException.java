package com.hg.gama.gamautil.numasutil.exception;


import com.hg.gama.gamautil.numasutil.constants.UtilCodeConstants;

public class UnAuthException extends RuntimeException {

    private int code;
    private String errMsg;

    public UnAuthException(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public UnAuthException() {
        code = UtilCodeConstants.UNAUTH_LOGIN;
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
