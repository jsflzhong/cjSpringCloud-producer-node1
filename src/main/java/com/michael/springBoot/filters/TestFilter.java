package com.michael.springBoot.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author cj
 * @description 测试用web拦截器
 *      该拦截器需先在此处自定义,然后需要在配置类中进行配置.(免去了在web.xml中的配置)
 * @date 2018/3/21
 */
public class TestFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        //i can writer other business code right here...

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info("@@@TestFilter in service-producer-node1 is running," +
                "request url is : {}", request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //No need to do anything.
    }
}
