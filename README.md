# springcloud技术栈

## 1.[eureka-service-descovery](https://github.com/wangruigen/springcloud/tree/master)
    eureka服务中心
    
## 2.eureka-service-descovery-ha
    1.高可用eureka server，eureka server集群，见配置application-simple.yml
    2.eureka用户认证，application.yml
    
## 3.user-provider
    1.用户微服务，做为服务提供者提供用户信息查询
    2.jpa+h2构建数据源

## 4.user-provider-metadata
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
