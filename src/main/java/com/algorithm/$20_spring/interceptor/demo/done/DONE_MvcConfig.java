package com.algorithm.$20_spring.interceptor.demo.done;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//5c721de17675e2f610f36c1bd5bf1900


/**
 * Created by huguoju on 2016/12/30.
 */
//http://blog.csdn.net/zhangchao19890805/article/details/53893735
@Configuration
public class DONE_MvcConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private Done_interceptor loginInterceptor;
  

  
  /**
   * 拦截器配置
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 注册监控拦截器
    registry.addInterceptor(loginInterceptor)
        .addPathPatterns("/**");
  
  }
  
  @Override
  public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**").allowedOrigins("*")
              .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
              .allowCredentials(false).maxAge(3600);
  }
  
//  /**
//   * 资源处理器
//   * @param registry
//   */
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    logger.info("addResourceHandlers");
//    registry.addResourceHandler("/swagger-ui.html")
//        .addResourceLocations("classpath:/META-INF/resources/");
//    registry.addResourceHandler("/webjars/**")
//        .addResourceLocations("classpath:/META-INF/resources/webjars/");
//  }
  
}