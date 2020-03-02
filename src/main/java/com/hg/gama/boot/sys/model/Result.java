package com.hg.gama.boot.sys.model;

/**
 * http请求做外层对象
 * @Auther: curry
 * @Date: 2018/6/2 14:35
 * @Description:
 */
public class Result<T> {
     // 状态
    private Status ret;
     // 提示信息
    private String msg;
   // 返回数据
    private T data;

    /*成功 */
    public Result(T data){
        this.ret =Status.SUCCESS;
        this.data=data;
    }
    /*失败*/
    public Result(Status status,String msg){
        this.ret =status;
        this.msg=msg;
    }

    public int getStatusCode() {
        return ret ==null? null: ret.code();
    }

    public String getRet() {
        return ret ==null? null: ret.msg();
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}