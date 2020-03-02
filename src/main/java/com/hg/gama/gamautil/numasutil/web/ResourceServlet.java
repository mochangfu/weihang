package com.hg.gama.gamautil.numasutil.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ResourceServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServlet.class);

    protected final String resourcePath;

    public ResourceServlet(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public void init() throws ServletException {
    }

    protected String getFilePath(String fileName) {
        return resourcePath + fileName;
    }

    protected void returnResourceFile(String fileName, String uri, HttpServletResponse response)
            throws ServletException,
            IOException {
        String filePath = getFilePath(fileName);

        boolean isText = false;
        if (fileName.endsWith(".css")) {
            response.setContentType("text/css;charset=utf-8");
            isText = true;
        } else if (fileName.endsWith(".js")) {
            response.setContentType("text/javascript;charset=utf-8");
            isText = true;
        } else if (fileName.endsWith(".svg")) {
            response.setContentType("text/xml;charset=utf-8");
            isText = true;
        } else if (fileName.endsWith(".html")) {
            response.setContentType("text/html; charset=utf-8");
        }
        if (isText) {
            String text = Utils.readFromResource(filePath);
            if (text == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            response.setHeader("Cache-Control", "max-age=86400");
            response.getWriter().write(text);
        } else {
            byte[] bytes = Utils.readByteArrayFromResource(filePath);
            if (bytes != null) {
                response.getOutputStream().write(bytes);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();

        response.setCharacterEncoding("utf-8");

        if (contextPath == null) { // root context
            contextPath = "";
        }
        String uri = contextPath + servletPath;
        String path = requestURI.substring(contextPath.length() + servletPath.length());

        if ("".equals(path) || "/".equals(path)) {
            if (uri.endsWith("/")) {
                response.sendRedirect(uri + "index.html");
            } else {
                response.sendRedirect(uri + "/index.html");
            }
            return;
        }

        // find file in resources path
        returnResourceFile(path, uri, response);
    }
}
