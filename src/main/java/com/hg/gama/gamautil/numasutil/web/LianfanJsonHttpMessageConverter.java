package com.hg.gama.gamautil.numasutil.web;

import org.apache.commons.io.input.TeeInputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class LianfanJsonHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public static final String REQUEST_BODY_ATTRIBUTE_NAME = "RequestBody";

    @Override
    public Object read(Type type, Class<?> contextClass, final HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {

        final ByteArrayOutputStream writerStream = new ByteArrayOutputStream();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String contentType = request.getHeader("Content-Type");
        if (contentType != null && contentType.startsWith("multipart/form-data")) {
            RequestContextHolder.getRequestAttributes().setAttribute(REQUEST_BODY_ATTRIBUTE_NAME, writerStream,
                    RequestAttributes.SCOPE_REQUEST);

            return super.read(type, contextClass, inputMessage);
        } else {
            HttpInputMessage message = new HttpInputMessage() {
                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }

                @Override
                public InputStream getBody() throws IOException {
                    return new TeeInputStream(inputMessage.getBody(), writerStream);
                }
            };

            RequestContextHolder.getRequestAttributes().setAttribute(REQUEST_BODY_ATTRIBUTE_NAME, writerStream,
                    RequestAttributes.SCOPE_REQUEST);

            return super.read(type, contextClass, message);
        }

    }
}