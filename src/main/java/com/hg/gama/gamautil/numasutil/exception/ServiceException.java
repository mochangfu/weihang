package com.hg.gama.gamautil.numasutil.exception;


import com.hg.gama.gamautil.numasutil.exception.message.ErrorMessage;
import com.hg.gama.gamautil.numasutil.exception.message.MessageBuilder;
import com.hg.gama.gamautil.numasutil.exception.message.MessageLanguage;
import com.hg.gama.gamautil.numasutil.util.StringUtil2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceException extends RuntimeException {

    /**
     * 错误信息定义类
     */
    private ErrorMessage msgDef;

    public static ServiceException createByMessage(String message, Object... args) {
        ErrorMessage msg = new ErrorMessage()
                .setContent(StringUtil2.formatSlf4jMsg(message, args))
                .setLanguage(MessageLanguage.CN.toString());
        return new ServiceException(msg);
    }

    public static ServiceException createByMessage(String message, Throwable cause, Object... args) {
        ErrorMessage msg = new ErrorMessage()
                .setContent(StringUtil2.formatSlf4jMsg(message, args))
                .setLanguage(MessageLanguage.CN.toString());
        return new ServiceException(msg, cause);
    }

    public static ServiceException createById(String id, Object... args) {
        ErrorMessage msg = MessageBuilder.build(id, args);
        return new ServiceException(msg);
    }

    public static ServiceException createById(String id, Throwable cause, Object... args) {
        ErrorMessage msg = MessageBuilder.build(id, args);
        return new ServiceException(msg, cause);
    }

    public static ServiceException createById(Integer id, Object... args) {
        ErrorMessage msg = MessageBuilder.build(String.valueOf(id), args);
        return new ServiceException(msg);
    }

    public static ServiceException createById(Integer id, Throwable cause, Object... args) {
        ErrorMessage msg = MessageBuilder.build(String.valueOf(id), args);
        return new ServiceException(msg, cause);
    }

    public ServiceException(ErrorMessage msgDef) {
        super(msgDef.formatMessage());
        setMsgDef(msgDef);
    }

    public ServiceException(ErrorMessage msgDef, Throwable cause) {
        super(msgDef.formatMessage(), cause);
        setMsgDef(msgDef);
    }

    public ErrorMessage getMsgDef() {
        return msgDef;
    }

    public void setMsgDef(ErrorMessage msgDef) {
        this.msgDef = msgDef;
    }

}
