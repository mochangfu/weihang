package com.hg.gama.boot.sys.model;

public enum Status {
    /*
  请求失败
   */
    FAIL(-1,"FAIL"),
    /*
    未登录
     */
    LOGIN(1,"LOGIN"),
    /*
    请求成功
     */
    SUCCESS(1,"SUCCESS");

    private int code;
    private String msg;
    private Status(int code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public int code() {
        return this.code;
    }
    public String msg() {
        return this.msg;
    }
}
