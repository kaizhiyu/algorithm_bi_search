package com.algorithm.$19_todo.list.ytd;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */

public class $2018_01_11_cef {

    /** 1:
     *  archive: v.	存档; n.	档案文件; 档案室;
     *  2:
     *  sibling: n.	兄弟，姐妹; [生] 同科，同属; [人] 氏族成员;
     *
     * 3: assure: v 确认
     *
     * 4：Returns true if an annotation for the specified type is present on this element, else false.
          This method is designed primarily for convenient access to marker annotations.
          The truth value returned by this method is equivalent to: getAnnotation(annotationClass) != null
          The body of the default method is specified to be the code above.

         如果此元素上存在指定类型的注释，则返回true，否则返回false。
         此方法主要是为了方便访问标记注释而设计的。
         此方法返回的真值等价于：getAnnotation（annotationClass）！= null
         默认方法的主体被指定为上面的代码。

     * 5：primarily： 主要的
     *
     * 6:
         Intercept the execution of a handler. Called after HandlerMapping determined an appropriate handler object, but before HandlerAdapter invokes the handler.
         DispatcherServlet processes a handler in an execution chain, consisting of any number of interceptors,
         with the handler itself at the end. With this method, each interceptor can decide to abort the execution chain,
         typically sending a HTTP error or writing a custom response.
         Note: special considerations apply for asynchronous request processing. For more details see AsyncHandlerInterceptor.
         Parameters:
         request - current HTTP request
         response - current HTTP response
         handler - chosen handler to execute, for type and/or instance evaluation
         Returns:
         true if the execution chain should proceed with the next interceptor or the handler itself. Else,
         DispatcherServlet assumes that this interceptor has already dealt with the response itself.

         拦截处理程序的执行。 在HandlerMapping之后调用确定一个适当的处理程序对象，但在HandlerAdapter调用处理程序之前。
         DispatcherServlet处理执行链中的处理程序，由任意数量的拦截器组成，处理器本身在最后。 使用这种方法，每个拦截器可以决定中止执行链，通常发送一个HTTP错误或者写一个自定义响应。
         注意：特殊注意事项适用于异步请求处理。 有关更多详细信息，请参阅AsyncHandlerInterceptor。
         参数：
         请求 - 当前的HTTP请求
         响应 - 当前的HTTP响应
         处理程序 - 选择处理程序来执行，用于类型和/或实例评估
         返回：
         如果执行链应继续执行下一个拦截器或处理程序本身，则为true。 否则，DispatcherServlet假定
     *7：Intercept the execution of a handler. Called after HandlerAdapter actually invoked the handler,
     * but before the DispatcherServlet renders the view. Can expose additional model objects to the view via the given ModelAndView.
     DispatcherServlet processes a handler in an execution chain, consisting of any number of interceptors,
     with the handler itself at the end. With this method, each interceptor can post-process an execution,
     getting applied in inverse order of the execution chain.
     Note: special considerations apply for asynchronous request processing. For more details see AsyncHandlerInterceptor.
     Parameters:
     request - current HTTP request
     response - current HTTP response
     handler - handler (or HandlerMethod) that started asynchronous execution, for type and/or instance examination
     modelAndView - the ModelAndView that the handler returned (can also be null)
     *
     * 8: Credentials 凭证
     * Whether user credentials are supported. By default this is set to true in which case user credentials are supported.
     * 是否支持用户凭据。 默认情况下，这被设置为true，在这种情况下支持用户凭证。
     *
     */




}
