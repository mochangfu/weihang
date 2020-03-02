package com.hg.gama.gamautil.numasutil.constants;

import org.springframework.context.annotation.Configuration;

/**
 * 异常错误码类 大写命名，用下划线'_'做分割 映射的property文件的key用点'.'做分割
 */
@Configuration
public class UtilCodeConstants implements BaseCode {
    // 服务端业务异常, code区间
    // 120001 - 121000 //蔡振森
    // 121001 - 122000 //nursecare_duty
    // 122001 - 123000
    // 123001 - 124000
    // 124001 - 125000
    // 125001 - 126000 //舒健苗
    // 126001 - 127000 // 付强 train模块
    // 127001 - 128000 //祝靖俊
    // 128001 - 129000 //龙璇
    // 129001 - 129999 //郑尚达


    // server服务端错务码定义
    public static final int SERVER_UNKNOW = SERVER_ERROR; // 100000 表示为服务端未知错误unknow，可视为无定义时的默认值undefine


    // 参数校验错误，参数不能为空
    public static final int PARAMETERS_CHECK_NULL_ERROR = SERVER_ERROR + SECURITY + 11;

    // 参数校验错误，ID无效
    public static final int PARAMETERS_CHECK_ID_ERROR = SERVER_ERROR + SECURITY + 12;

    // 参数均为null
    public static final int PARAMETERS_ALL_NULL_ERROR = SERVER_ERROR + SECURITY + 13;

    //登录失效
    public static final int UNAUTH_LOGIN = SERVER_ERROR + SECURITY + 14;

    //    //登录token无效
    public static final int UNAUTH_TOKEN_INVALID = SERVER_ERROR + SECURITY + 15;

    // 注解校验错误
    public static final int PARAMETERS_VALIDATE_COMMON_ERROR = SERVER_ERROR + SECURITY + 16;

    //    // 文件上传失败
    public static final int UPLOAD_ERROR = SERVER_ERROR + SECURITY + 17;


    public static final int OBJECT_UNEXIST = SERVER_ERROR + BIZ + 3;


}