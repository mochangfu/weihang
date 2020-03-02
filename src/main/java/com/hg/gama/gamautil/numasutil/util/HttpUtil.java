package com.hg.gama.gamautil.numasutil.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.*;

public final class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 默认字符集
     **/
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 读超时
     **/
    private static final int SOCKET_TIMEOUT = 2000;
    /**
     * 连接超时
     **/
    private static final int CONNECT_TIMEOUT = 2000;
    /**
     * 从连接池获取连接超时
     **/
    private static final int CONNECTION_REQUEST_TIMEOUT = 2000;

    /**
     * 超时设置
     **/
    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT).build();

    private static final PoolingHttpClientConnectionManager CONNECTION_MANAGER = new PoolingHttpClientConnectionManager();

    /**
     * 总共最多可以创建的连接数
     **/
    private static final int MAX_CONNECTION_TOTAL = 3000;
    /**
     * 连接到单个主机最多可以创建的连接数
     **/
    private static final int MAX_CONNECTION_PER_ROUTE = 500;

    /** 连接池大小设置 **/
    static {
        CONNECTION_MANAGER.setMaxTotal(MAX_CONNECTION_TOTAL);
        CONNECTION_MANAGER.setDefaultMaxPerRoute(MAX_CONNECTION_PER_ROUTE);
    }

    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.custom()
            // 不允许自动重试
            .disableAutomaticRetries()
            .setConnectionManager(CONNECTION_MANAGER).build();

    private HttpUtil() {

    }

    public static final HttpResponse get(String url) {
        return execute(assembleGet(url, null, null));
    }

    public static final HttpResponse get(String url, Map<String, String> params) {
        return execute(assembleGet(url, params, null));
    }

    public static final HttpResponse get(String url, Map<String, String> params, Map<String, String> headers) {
        return execute(assembleGet(url, params, headers));
    }

    public static final HttpResponse post(String url, Map<String, String> params) {
        return execute(assemblePost(url, params, new HashMap<>(0)));
    }


    public static final HttpResponse post(String url, Map<String, String> params, Map<String, String> headers) {
        return execute(assemblePost(url, params, headers));
    }

    public static final HttpResponse post(String url, Map<String, String> headers, String json) {
        return execute(assemblePost(url, headers, json));
    }

    public static final HttpResponse post(String url, Map<String, String> params, Map<String, String> headers, File file,
            String fileName) {
        return execute(assemblePost(url, params, headers, file, fileName));
    }

    public static final HttpResponse post(String url, Map<String, String> params, Map<String, String> headers, byte[] bytes,
            String fileName) {
        return execute(assemblePost(url, params, headers, bytes, fileName));
    }


    private static HttpResponse execute(HttpRequestBase req) {
        req.setConfig(REQUEST_CONFIG);

        CloseableHttpResponse response = null;
        try {
            response = HTTP_CLIENT.execute(req);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                return new HttpResponse(response.getStatusLine().getStatusCode(),
                        new String(EntityUtils.toByteArray(entity), DEFAULT_CHARSET), false);
            } else {
                return new HttpResponse(response.getStatusLine().getStatusCode(),
                        new String(EntityUtils.toByteArray(entity), DEFAULT_CHARSET), true);
            }
        } catch (SocketTimeoutException e) {
            log.error(e.getMessage(), e);
            return new HttpResponse(HttpStatus.SC_GATEWAY_TIMEOUT, e);
        } catch (ConnectTimeoutException e) {
            log.error(e.getMessage(), e);
            return new HttpResponse(HttpStatus.SC_BAD_GATEWAY, e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new HttpResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 将参数拼接到url里并返回get对象
     */
    private static HttpGet assembleGet(String url, Map<String, String> params, Map<String, String> headers) {
        if (params != null && !params.isEmpty()) {
            if (-1 == url.indexOf("?")) {
                url += "?" + parseRequestQueryString(params, DEFAULT_CHARSET);
            } else {
                url += "&" + parseRequestQueryString(params, DEFAULT_CHARSET);
            }
        }
        HttpGet method = new HttpGet(url);

        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> heads = headers.entrySet();
            for (Map.Entry<String, String> head : heads) {
                method.addHeader(head.getKey(), head.getValue());
            }
        }

        return method;
    }

    /**
     * 返回一个参数已经组装及编码的post对象
     */
    private static HttpPost assemblePost(String url, Map<String, String> params, Map<String, String> headers) {
        HttpPost method = new HttpPost(url);
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> heads = headers.entrySet();
            for (Map.Entry<String, String> head : heads) {
                method.addHeader(head.getKey(), head.getValue());
            }
        }
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> param : entries) {
                pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
            try {
                method.setEntity(new UrlEncodedFormEntity(pairs, DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return method;
    }

    private static HttpPost assemblePost(String url, Map<String, String> headers, String data) {
        HttpPost method = new HttpPost(url);

        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> heads = headers.entrySet();
            for (Map.Entry<String, String> head : heads) {
                method.addHeader(head.getKey(), head.getValue());
            }
        }
        if (null != data && !"".equalsIgnoreCase(data)) {
            method.setEntity(
                    EntityBuilder.create().setContentEncoding(DEFAULT_CHARSET).setContentType(ContentType.APPLICATION_JSON)
                            .setText(data).build());
        }
        return method;
    }

    private static HttpPost assemblePost(String url, Map<String, String> params, Map<String, String> headers, File file,
            String fileName) {
        HttpPost method = new HttpPost(url);
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> heads = headers.entrySet();
            for (Map.Entry<String, String> head : heads) {
                method.addHeader(head.getKey(), head.getValue());
            }
        }
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart(fileName, new FileBody(file));
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> param : entries) {
                multipartEntityBuilder.addTextBody(param.getKey(), param.getValue());
            }
        }
        method.setEntity(multipartEntityBuilder.build());
        return method;
    }

    private static HttpPost assemblePost(String url, Map<String, String> params, Map<String, String> headers, byte[] bytes,
            String fileName) {
        HttpPost method = new HttpPost(url);
        if (headers != null && !headers.isEmpty()) {
            Set<Map.Entry<String, String>> heads = headers.entrySet();
            for (Map.Entry<String, String> head : heads) {
                method.addHeader(head.getKey(), head.getValue());
            }
        }
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart(fileName, new ByteArrayBody(bytes, fileName));
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> param : entries) {
                multipartEntityBuilder.addTextBody(param.getKey(), param.getValue());
            }
        }
        method.setEntity(multipartEntityBuilder.build());
        return method;
    }

    private static String parseRequestQueryString(Map<String, String> params, String charset) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        Set<Map.Entry<String, String>> entries = params.entrySet();
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>(params.size());
        for (Map.Entry<String, String> entry : entries) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return URLEncodedUtils.format(pairs, charset);
    }

    /**
     * 仅在应用退出时调用此方法，例如shutdownhook或者web应用的listener
     */
    public static final void close() {
        try {
            HTTP_CLIENT.close();
        } catch (IOException e) {
        }
    }

    public static final class HttpResponse {
        private boolean success = false;
        private int code;
        private String text;
        private IOException exception;

        public HttpResponse(int code, boolean success) {
            super();
            this.code = code;
            this.success = success;
        }

        public HttpResponse(int code, IOException exception) {
            super();
            this.code = code;
            this.exception = exception;
        }

        public HttpResponse(int code, String text, boolean success) {
            super();
            this.code = code;
            this.text = text;
            this.success = success;
        }

        public JsonNode getResult() {
            return JsonUtil.toObject(text);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public IOException getException() {
            return exception;
        }

        public void setException(IOException exception) {
            this.exception = exception;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        @Override
        public String toString() {
            return "HttpResponse [code=" + code + ", text=" + text + ", exception=" + exception + "success=" + success + "]";
        }

    }

}
