package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
/**
 * @EnableZuulServer 开启zuul服务
 * 此注解会开启：
 * 1、pre类型过滤器
 * {@link org.springframework.cloud.netflix.zuul.filters.pre.ServletDetectionFilter}：该过滤器用于检查请求是否通过Spring Dispatcher。检查后，通过isDispatcherServletRequest设置布尔值。
 * {@link org.springframework.cloud.netflix.zuul.filters.pre.FormBodyWrapperFilter}：解析表单数据，并为请求重新编码。
 * {@link org.springframework.cloud.netflix.zuul.filters.pre.DebugFilter}：顾名思义，调试用的过滤器，可以通过zuul.debug.request=true，或在请求时，加上debug=true的参数，例如$ZUUL_HOST:ZUUL_PORT/path?debug=true开启该过滤器。这样，该过滤器就会把RequestContext.setDebugRouting()、RequestContext.setDebugRequest()设为true。
 * 2、route类型过滤器
 * {@link org.springframework.cloud.netflix.zuul.filters.route.SendForwardFilter}：
 *  该过滤器使用Servlet RequestDispatcher转发请求，转发位置存储在RequestContext.getCurrentContext().get(“forward.to”)中
 *
 * 3、post类型过滤器
 * {@link org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter}：将Zuul所代理的微服务的响应写入当前响应。
 *
 * 4、error类型过滤器
 * {@link org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter}：如果RequestContext.getThrowable()不为null，那么默认就会转发到/error，也可以设置error.path属性修改默认的转发路径。
 */
//@EnableZuulServer
/**
 * @EnableZuulProxy 开启zuul代理，此注解是@EnableZuulServer的增强版，当zuul与eureka、ribbon等组件配合使用时，此注解是常用的
 * 使用此注解,除了@EnableZuulServer注解开启的过滤器之外，还开启了以下的过滤器：
 * 1.pre类型： {@link org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter},该过滤器根据提供的RouteLocator(路由定位器)
 * 确定路由到的地址，以及怎样去路由。同时，该过滤器还在为下游请求设置各种代理相关的header
 * 2.route类型：
 *  2.1 {@link org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter},该过滤器使用Ribbon、hystrix和
 *  可插播的http客户端发送请求。serviceId在RequestContext的属性FilterConstans.SERVER_ID_KEY中。该过滤器可使用如下这些不同的http客户端：
 *      Apache HttpClient:默认的Http客户端
 *      Square OkHttpClient v3: 若需使用改客户端，需保证com.squareup.okhttp3的依赖在classpath中，并设置ribbon.okhttp.enable=true
 *      Netflix Ribbon HttpClient: 设置ribbon.restclient.enable=true即可开启该http客户端。该客户端有一定限制，如不支持PATCH方法，另外，它有
 *      内置的重试机制。
 *  2.2 {@link org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter},该过滤器通过Apache HttpClient向指定的URL发送
 *  请求，URL在RequestContext.getRouteHost()中。
 */
@EnableZuulProxy
public class ZuulBootRun {
    public static void main(String[] args) {
        SpringApplication.run(ZuulBootRun.class, args);
    }
}
