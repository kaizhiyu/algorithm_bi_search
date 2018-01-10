package com.algorithm.$16_javascript;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * From 天帝
 * CrosFilter : 跨域资源共享过滤器, 该过滤器设置response header, 解决跨域ajax请求报错
 * CrossOrigin
 * 解决ajax跨域问题
 * 处理页面跨域问题
 */

// http://www.makaidong.com/%E5%8D%9A%E5%AE%A2%E5%9B%AD%E9%97%AE%E7%AD%94/13205.shtml
@Component
public class CorsFilter_CrossOrigin implements Filter {
    public static final String HEADER_ERROR = "X-tjmjApp-error";
    public static final String HEADER_ALERT = "X-tjmjApp-alert";
    public static final String HEADER_PARAM = "X-tjmjApp-param";

    public static final String EXPOSE_HEADER = HEADER_ERROR + ", " + HEADER_ALERT + ", " + HEADER_PARAM;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse)res;

        response.setHeader("Access-Control-Allow-Origin", "*");// 允许所有域进行访问,可以指定多个Access-Control-Allow-Origin:http://localhost:8080/
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");// 允许的方法
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Cache-Control, Authorization, username");
        response.setHeader("Access-Control-Expose-Headers", "content-type" + ", " + EXPOSE_HEADER);//如果不设置，js获取不到头信息
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
