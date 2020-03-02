package com.hg.gama.gamautil.numasutil.constants;

public interface BaseCode {

    /**
     * 出错，下面对错误状态码的定义，错误状态码数值大于等于100000
     * <p>
     * <p>
     * <p>
     * 每个error code由 6 位的整型数字，分 3 段有含义的数值表示；具体定义方式后面有举例
     * <p>
     * 第1段 由第1位数字表示，其代表error的出处 1:server，2:android，3:web page，4:pc client，9:other;
     * <p>
     * 如 1XXXXX，为每个类别定义起始位
     * <p>
     * 第2段 由第2～3位数字表示，其代表error的类别
     * <p>
     * 第3段 由第4～6位数字表示，自然增长，定义是＋
     */

    // 第1段定义

    int SERVER_ERROR = 100000;
    int ANDROID_ERROR = 200000;
    int WEB_PAGE_ERROR = 300000;
    int PC_CLIENT_ERROR = 400000;
    int OTHER_ERROR = 900000;

    // 第2段定义，此段数字单独没有意义，必需与第1段及具体错误码结合使用，
    // 前后端共用类别
    int DB = 91000; // DB访问错误
    int DS = 92000; // I/O文件类访问错误
    int CACHE = 93000; // 缓存类访问错误
    int THIRD = 94000; // 特定第三方Local api调用错误，如文件指文计算，文件压缩，IP转城市等
    int BS = 95000; // 访问其它应用出错，如其它服务化接口
    int SECURITY = 98000; // 安全类出错，试图操作未授权资源

    // 服务端
    int WEB = 10000; // web代码层出错
    int BIZ = 20000; // service代码层出错
    int DAO = 30000; // dao代码层出错

    // 手机端或PC端
    int UI = 50000; // 手机app层出错或PC app层出错
    int HTTP = 60000; // 手机服务访问出错

    // Web页面
    int JS = 80000; // web html／js前端页面处理出错

    /**
     * error code 使用>=100000 && <=999999范围，其中server error code 使用>=100000 && <=199999范围
     */

    // 比如要定义两个 服务端 db类 的错如下：
    //
    // public static final int SERVER_DB_CONNECTION = SERVER_ERROR + DB + 1; //110001 获取DB连接失败
    //
    // public static final int SERVER_DB_ERROR_PARAM = SERVER_ERROR + DB + 2; //110002 错误的SQL参数
    //
    //
    // //如如说要定义一个 服务端 BIZ业务类 的错如下：
    //
    // public static final int SERVER_BIZ_UPLOAD_SRCFILE_MISSSIZE = SERVER_ERROR + BIZ + 1; //120001 文件上传出错，与上传端文件不一致

    //	// 您还没有登录
    //	public static final int NOT_LOGIN_ERROR = SERVER_ERROR + SECURITY + 1;
    //
    //	// 您没有操作权限
    //	public static final int NOT_LOGIN_PERMISSIONS_ERROR = SERVER_ERROR + SECURITY + 4;
}