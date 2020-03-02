package com.hg.gama.gamautil.numasutil.exception;


import com.hg.gama.gamautil.numasutil.constants.UtilCodeConstants;
import com.hg.gama.gamautil.numasutil.exception.message.ErrorMessage;
import com.hg.gama.gamautil.numasutil.exception.message.MessageBuilder;
import com.hg.gama.gamautil.numasutil.exception.message.MessageLanguage;
import com.hg.gama.gamautil.numasutil.util.StringUtil2;


public class GamaException extends ServiceException {

    private static final long serialVersionUID = -4126310073349890216L;

    private static final MessageLanguage LANGUAGE = MessageLanguage.CN;

    public GamaException() {
        super(MessageBuilder.build(String.valueOf(UtilCodeConstants.SERVER_UNKNOW)));
    }

    public GamaException(int code, Throwable cause) {
        super(MessageBuilder.build(String.valueOf(code)), cause);
    }

    public GamaException(Throwable cause) {
        super(MessageBuilder.build(String.valueOf(UtilCodeConstants.SERVER_UNKNOW)), cause);
    }

    public GamaException(int code) {
        super(MessageBuilder.build(String.valueOf(code)));
    }

    /**
     * 支持传入参数，替换错误信息中的占位符
     */
    public GamaException(int code, Throwable cause, Object... args) {
        super(MessageBuilder.build(String.valueOf(code), args), cause);
    }

    /**
     * 支持传入参数，替换错误信息中的占位符
     */
    public GamaException(int code, Object... args) {
        super(MessageBuilder.build(String.valueOf(code), args));

    }

    /**
     * 不建议直接抛出错误信息
     */
    public GamaException(String errMsg) {
        super(new ErrorMessage()
                .setContent(StringUtil2.formatSlf4jMsg(errMsg))
                .setLanguage(MessageLanguage.CN.toString()));
    }

    /**
     * 不建议直接抛出错误信息
     */
    public GamaException(String errMsg, Object... args) {
        super(new ErrorMessage()
                .setContent(StringUtil2.formatSlf4jMsg(errMsg, args))
                .setLanguage(MessageLanguage.CN.toString()));
    }

    /**
     * 不建议直接抛出错误信息
     */
    public GamaException(String errMsg, Throwable cause, Object... args) {
        super(new ErrorMessage()
                .setContent(StringUtil2.formatSlf4jMsg(errMsg, args))
                .setLanguage(MessageLanguage.CN.toString()), cause);
    }

}