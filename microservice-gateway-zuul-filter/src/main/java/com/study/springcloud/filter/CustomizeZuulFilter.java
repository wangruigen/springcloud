package com.study.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 继承 {@link ZuulFilter} 实现自己的ZuulFilter
 * zuul过滤器的类型：
 * 1.pre ：这种过滤器在请求被路由之前调用，可利用这种过滤器实现身份验证、在急群众选择请求的微服务、记录调速记录等。
 * 2.routing ：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或者Netflix Ribbon请求微服务。
 * 3.post : 这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的http header、收集统计信息和指标、将响应从微服务发送到客户端等。
 * 4.error ：在其他阶段发生错误时执行该过滤器，用户记录错误日志，输出错误结果等。
 * 5.自定义的过滤器，需继承{@link ZuulFilter}
 *
 * zuulFilter相关常量参考：{@link FilterConstants}
 */
public class CustomizeZuulFilter extends ZuulFilter {


    @Override
    public String filterType() {
        /**
         * 过滤器类型是pre
         */
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器执行顺序，
     * 在{@link org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter}之前启动
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        return null;
    }
}
