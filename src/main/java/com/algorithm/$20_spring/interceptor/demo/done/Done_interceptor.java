package com.algorithm.$20_spring.interceptor.demo.done;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**

 * <p>
 * 判断 session
 * <p>
 * 首先判断这个接口需不要session
 * <p>
 * 不需要直接到过滤
 * <p>
 * 需要的，要做过滤，判断session是否过期
 */
@Component
public class Done_interceptor extends HandlerInterceptorAdapter {


    /**
     *

     * @param handler 我只能猜这个应该是 下一个interceptorHandler吧。。。？？？？？  for HandlerExecutionChain handlerExecutionChain

     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getServletPath();
//
//        //token存在则设置本地变量
//        UserInfo userInfo = (UserInfo) userObject;
//        this.setLocalUserInfo(userInfo.getUserLoginNo(), userInfo.getUserName());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        Object object = MDC.get(Span.TRACE_ID_NAME);

    }



    /**
     * 设置结果值
     *
     * @param httpServletResponse
     * @param result
     * @throws IOException
     */
    public void setResponseData(HttpServletResponse httpServletResponse, String result) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = httpServletResponse.getWriter();
        out.print(result);
    }

    // 设置本地变量
    private void setLocalUserInfo(String userNo, String userName) {
        // 本地线程，保存用户信息
//        UserContext.init(userNo, userName);
    }


}
