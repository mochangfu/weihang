package com.hg.gama.gamautil.numasutil.web;


import com.hg.gama.gamautil.numasutil.util.DateUtils2;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class DateHandlerMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        if (parameter.hasParameterAnnotation(DateParam.class)) {
            return Date.class.isAssignableFrom(paramType);
        }
        return false;
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        DateParam ann = parameter.getParameterAnnotation(DateParam.class);
        return ann != null ? new RequestParamNamedValueInfo(ann) : new RequestParamNamedValueInfo();
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        String val = servletRequest.getParameter(name);
        return DateUtils2.parseDate(val);
    }

    @Override
    protected void handleMissingValue(String name, MethodParameter parameter) throws ServletException {
        throw new MissingServletRequestParameterException(name, parameter.getParameterType().getSimpleName());
    }

    private static class RequestParamNamedValueInfo extends NamedValueInfo {

        public RequestParamNamedValueInfo() {
            super("", false, ValueConstants.DEFAULT_NONE);
        }

        public RequestParamNamedValueInfo(DateParam annotation) {
            super(annotation.name(), annotation.required(), annotation.defaultValue());
        }
    }
}
