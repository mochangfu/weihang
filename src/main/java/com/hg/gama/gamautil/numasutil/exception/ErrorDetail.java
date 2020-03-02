package com.hg.gama.gamautil.numasutil.exception;

public class ErrorDetail {

    private String errCode;

    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public ErrorDetail setErrCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public ErrorDetail setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}