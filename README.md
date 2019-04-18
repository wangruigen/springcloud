# springcloud技术栈
> 感谢此书《Spring Cloud与Docker微服务架构实战》（第二版）和作者，获益匪浅。
## 1.[eureka-service-descovery](https://github.com/wangruigen/springcloud/tree/master/eureka-service-descovery)
    eureka服务中心
    
## 2.[eureka-service-descovery-ha](https://github.com/wangruigen/springcloud/tree/master/eureka-service-descovery-ha)
    1.高可用eureka server，eureka server集群，见配置application-simple.yml
    2.eureka用户认证，application.yml
    
## 3.[user-provider](https://github.com/wangruigen/springcloud/tree/master/user-provider)
    1.用户微服务，做为服务提供者提供用户信息查询
    2.jpa+h2构建数据源

## 4.[user-provider-metadata](https://github.com/wangruigen/springcloud/tree/master/user-provider-metadata)
```yaml
#用户微服务自定义元数据
eureka:
   client:
     service-url:
       #将应用注册到注册中心，配置注册中心地址
       #这里的url服务端客户端和配置中心的单词必须完全一样
       #这里的url服务端客户端和配置中心的单词必须完全一样
       #这里的url服务端客户端和配置中心的单词必须完全一样
       defaultZone: http://127.0.0.1:8761/eureka
   instance:
     prefer-ip-address: true #将自己ip注册到eureka server,默认false,如果false,表示将系统所在的hostname注册到eureka server
     #自定义元数据
     metadata-map:
       #key:value形式
       my-metadata: 自定义的元数据 
```

## 5.[user-provider-with-auth](https://github.com/wangruigen/springcloud/tree/master/user-provider-with-auth)
    微服务访问需要认证的eureka server
```yaml
eureka:
  client:
    service-url:
      #将应用注册到注册中心，配置注册中心地址（也可以配置一个，注册中心会同步节点，最好是配置两个，避免极端情况，节点同步不了）
      #defaultZone: http://peer1:8761/eureka,http://peer2:8762/eureka
      defaultZone: http://admin:admin@peer1:8761/eureka,http://admin:admin@peer2:8762/eureka
      #defaultZone: http://localhost:8761/eureka
```

## 6.[movie-comsumer](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer)
    1.电影微服务，服务消费者，调用user-provider查询用户信息
    2.RestTemplate调用http api
    
## 7.[movie-comsumer-metadata](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-metadata)
    电影微服务-服务消费者-元数据配置
    
## 8.[movie-comsumer-ribbon](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-ribbon)
    1.电影微服务-服务消费者-整合ribbon,实现客户端负载均衡
    2.整合hystrix断路器@EnableCircuitBreaker
    
## 9.[movie-comsumer-ribbon-customize](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-ribbon-customize)
    `电影微服务-服务消费者-整合ribbon-定制ribbon`
```java

/**
 * Ribbon配置类，不在springboot扫描范围内
 */
@Configuration
public class RibbonConfiguration {

    /**
     * 定义一个负债均衡规则：随机访问
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}

/**
 * <p>使用RibbonClient，为特定name的Ribbon Client自定义配置</p>
 * <p>使用RibbonClient的configuretion属性，指定ribbon的配置类</p>
 *
 */
@Configuration
@RibbonClient(name = "user-provider-metadata",configuration = RibbonConfiguration.class)
public class TestRibbonConfiguration {
}

```     
## 10.[movie-comsumer-ribbon-customize-properties](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-ribbon-customize-properties)
    电影微服务-服务消费者-整合ribbon-使用属性自定义ribbon配置
```yaml

###==============================使用属性自定义ribbon配置,配置文件配置的方式优先级高于代码编程配置的方式
#格式：RibbonClientName.ribbon.NF*className: classNameImpl (ribbon客户端名称.ribbon.NF*className: NF*classNameImpl)
#RibbonClientName:配置在eureka server的实例，serviceId,若不配置，则对全局client instance有效。
#NF*className： NFLoadBalancerCLassName:配置ILoadBalancer的实现类
#               NFLoadBalancerRuleClassName:配置IRule的实现类
#               NFLoadBalancerPingClassName:配置IPing的实现类
#               NIWSServerListClassName: 配置ServerList的实现类
#               NIWSServerListFilterClassName: 配置ServerListFilter的实现类
#下面是配置实例为user-provider-metadata实例的ribbon负载均衡策略为随机选择实例
user-provider-metadata:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
    #配置请求列表，可以从listOfServers的列表中选择user-provider-metadata的请求。不配置的话所有的user-provider-metadata实例根据负载均衡策略进行请求
    NIWSServerListClassName: com.netflix.loadbalancer.ConfigurationBasedServerList
  listOfServers: localhost:9001,localhost:9000 #user-provider-metadata实例只会从这里选择服务列表

#配置多个ribbon client负载均衡策略
user-provider-metadata1:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule

user-provider-metadata2:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule


#对所有的ribbonClient实例使用RandomRule
#ribbon:
#  NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
ribbon:
  eager-load:
    clients: user-provider-metadata,user-provider-metadata2 #饥饿加载的ribbon client
    enabled: true #饥饿加载 默认是false(懒加载，只有在被使用时才执行初始化操作)
```
    
## 11.[movie-comsumer-feign](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-feign)
    1.电影微服务-服务消费者-使用feign调用http api
    2.feignClient默认集成了Ribbon,默认负载均衡策略是轮询
    
## 12.[movie-comsumer-feign-customize](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-feign-customize)
    电影微服务-自定义feignCLients的configuration
```java
/*
配置注解FeignClient，指定eureka server列表中的服务，这里是用户提供者服务实例名.path(Path prefix)表示的是应用名，可理解成tomcat root path
   里面还有很多参数，简单讲url,url指定服务端地址，这里配合eureka使用，自动从eureka获取服务，可以不写。
configuration:配置自己自定义的feign client
 */
@FeignClient(name = "user-provider",path = "/user-provider",configuration = MyFeignConfiguration.class)
public interface UserFeignClient {

    /*@RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    User findById(@PathVariable(value = "id") String id);*/

    /**
     * 使用feign自带的注解标识方法 @RequestLine
     * @param id
     * @return
     */
    @RequestLine("GET /user/{id}")
    User findById(@Param("id")String id);
}

/**
 * 该类是自定义的feign配置类，不在springboot扫描的范围内，不然配置类中的
 * feign.Decoder,feign.Encode,feign.Contract等配置会被所有的@feignClient共享。
 * 解决办法：要么不配置@Configuration注解，要么配置类不放在springboot扫描的范围上
 */
//@Configuration
public class MyFeignConfiguration {

    /**
     * 将契约修改成feign原生的默认契约，这样就可以使用feign自带的注解
     * @return 默认的feign契约
     */
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }
}

```
## 13.[movie-comsumer-feign-hystrix-fallback](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-feign-hystrix-fallback)
     电影微服务,feign整合hystrix,实现feign回退
```java
@FeignClient(name = "user-provider",path = "/user-provider",fallback = FeignClientFallBack.class)
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    User findById(@PathVariable(value = "id") String id);

}
@Component
class FeignClientFallBack implements UserFeignClient {
    @Override
    public User findById(String id) {
        User user = new User();
        user.setId(10000L);
        user.setUsername("default_username");
        user.setName("defalut_name");
        user.setAge(0);
        user.setBalance(new BigDecimal(0));
        return user;
    }
}
```

## 14.[movie-comsumer-feign-hystrix-fallback-stream](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-feign-hystrix-fallback-stream)
    1.电影微服务-服务消费者-使用feign调用http api,feign整合hystrix,实现feign回退。
    2.启动类添加@EnableCircuitBreaker注解或@EnableHystrix(默认开启熔断支持)，可以使用/hystrix.stream监控此服务

## 15.[movie-comsumer-feign-manual](https://github.com/wangruigen/springcloud/tree/master/movie-comsumer-feign-manual)
    电影微服务-服务消费者-手动创建feignClient调用http api
    
## 16.[hystrix可视化监控hystrix.stream](https://github.com/wangruigen/springcloud/tree/master/hystrix可视化监控hystrix.stream)
    hystrix可视化监控hystrix.stream

## 17.[microservice-hystrix-turbine](https://github.com/wangruigen/springcloud/tree/master/microservice-hystrix-turbine)
    使用turbine监控多个微服务
    
## 18.[microservice-gateway-zuul](https://github.com/wangruigen/springcloud/tree/master/microservice-gateway-zuul)
    1.微服务-网关zuul
    2.路由配置见application.yml，需自己动手实践体验，才能理解。

## 19.[microservice-gateway-zuul-filter](https://github.com/wangruigen/springcloud/tree/master/microservice-gateway-zuul-filter)
    1.微服务网关zuul-自定义zuulfilter
    2.注解需细看
    
## 20.[microservice-gateway-zuul-fallback](https://github.com/wangruigen/springcloud/tree/master/microservice-gateway-zuul-fallback)
    微服务网关zuul-zuul的容错与回退

## 21.[config-repository](https://github.com/wangruigen/springcloud/tree/master/config-repository)
    springcloud config server 配置

## 22.[microservice-config-server](https://github.com/wangruigen/springcloud/tree/master/microservice-config-server)
    spring cloud config-server
    
# 更新中。。。。。。
