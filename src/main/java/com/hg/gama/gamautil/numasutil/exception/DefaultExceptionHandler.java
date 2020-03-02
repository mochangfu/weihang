package com.hg.gama.gamautil.numasutil.exception;

import com.google.common.collect.Sets;
import com.hg.gama.gamautil.numasutil.constants.PropertyConstants;
import com.hg.gama.gamautil.numasutil.constants.UtilCodeConstants;
import com.hg.gama.gamautil.numasutil.exception.message.ErrorMessage;
import com.hg.gama.gamautil.numasutil.util.JsonUtil;
import com.hg.gama.gamautil.numasutil.web.LianfanJsonHttpMessageConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.ibatis.exceptions.IbatisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@ControllerAdvice("com.lianfan")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    private Set<String> blacklist = Sets.newHashSet();

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex, HttpServletResponse response) throws IOException {
        if (ex instanceof AsyncRequestTimeoutException) {
            return null;
        }
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setCharacterEncoding("UTF-8");

        ServiceException lianfanEx = PropertyConstants.isDevMode() ? convertExceptionDevMode(ex, response) : convertException(ex, response);
        printTrace(request, ex); // 打印堆栈
        return errorJson(lianfanEx, response);
    }

    public void addExceptionTraceFilterCode(String exceptionCode) {
        blacklist.add(exceptionCode);
    }

    private ModelAndView errorJson(ServiceException lianfanEx, HttpServletResponse response) throws IOException {
        response.setContentType("text/JavaScript; charset=utf-8");
        response.getWriter().print(JsonUtil.toJson(buildErrorDetail(lianfanEx.getMsgDef())));
        return null;
    }

    private ServiceException convertExceptionDevMode(Exception ex, HttpServletResponse response) {
        ServiceException lianfanEx;
        if (ex instanceof ServiceException) {
            lianfanEx = (ServiceException) ex;
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (ex instanceof ConstraintViolationException) { // 注解校验失败异常
            ConstraintViolationException validationEx = (ConstraintViolationException) ex;
            lianfanEx = ServiceException.createById(UtilCodeConstants.PARAMETERS_VALIDATE_COMMON_ERROR, ex,
                    buildValidationMessage(validationEx.getConstraintViolations()));
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (ex instanceof BindException) {
            BindException bindEx = (BindException) ex;
            lianfanEx = ServiceException.createById(UtilCodeConstants.PARAMETERS_VALIDATE_COMMON_ERROR, ex,
                    buildBindMessage(bindEx.getBindingResult().getAllErrors()));
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (ex instanceof IllegalArgumentException) {
            IllegalArgumentException bindEx = (IllegalArgumentException) ex;
            lianfanEx = ServiceException.createByMessage(bindEx.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else {
            lianfanEx = ServiceException.createByMessage(ex.getClass().getSimpleName() + "(" + ex.getMessage() + ")");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return lianfanEx;
    }

    @SuppressWarnings("deprecation")
    private ServiceException convertException(Exception ex, HttpServletResponse response) {
        ServiceException lianfanEx;
        if (ex instanceof ServiceException) {
            lianfanEx = (ServiceException) ex;
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (ex instanceof SQLException || ex instanceof IbatisException
                || ex instanceof DataAccessException) {
            lianfanEx = ServiceException.createByMessage("数据库访问异常");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else if (ex instanceof IllegalArgumentException) {
            IllegalArgumentException bindEx = (IllegalArgumentException) ex;
            lianfanEx = ServiceException.createByMessage(bindEx.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else {
            lianfanEx = ServiceException.createByMessage(ex.getClass().getSimpleName());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return lianfanEx;
    }

    private String buildValidationMessage(Set<ConstraintViolation<?>> constraintViolations) {
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            for (ConstraintViolation<?> violation : constraintViolations) {
                builder.append(violation.getMessage()).append(";");
            }
        }
        return builder.toString();
    }

    private String buildBindMessage(List<ObjectError> resultList) {
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (ObjectError result : resultList) {
                builder.append(result.getDefaultMessage()).append(";");
            }
        }
        return builder.toString();
    }

    public static ErrorDetail buildErrorDetail(final ErrorMessage msg) {
        ErrorDetail error = new ErrorDetail();
        error.setErrCode(String.valueOf(msg.getId()));
        error.setErrMsg(msg.getContent());
        return error;
    }

    private void printTrace(HttpServletRequest request, Exception ex) {
        if (ex instanceof ServiceException && blacklist.contains(((ServiceException) ex).getMsgDef().getId())) {
            return;
        }

        String bodyStr = "";
        Object data = request.getAttribute(LianfanJsonHttpMessageConverter.REQUEST_BODY_ATTRIBUTE_NAME);
        if (data instanceof ByteArrayOutputStream) {
            ByteArrayOutputStream body = (ByteArrayOutputStream)
                    request.getAttribute(LianfanJsonHttpMessageConverter.REQUEST_BODY_ATTRIBUTE_NAME);

            if (body != null) {
                try {
                    bodyStr = StringEscapeUtils.escapeJava(body.toString("utf-8"));
                } catch (UnsupportedEncodingException e1) {
                    logger.error("", e1);
                }
            }

        } else if (data instanceof String) {
            bodyStr = (String) data;
        }

        logger.error("Call [{}:{}] failed! Client:[{}], Session:[{}], Parameter:[{}], Body:[{}].", request.getMethod(), request.getRequestURI(),
                request.getRemoteAddr(), request.getRequestedSessionId(), request.getQueryString(), bodyStr, ex);
    }
}
