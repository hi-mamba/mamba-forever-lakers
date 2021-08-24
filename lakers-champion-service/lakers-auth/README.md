
# 权限系统

> Oauth2认证服务，负责对登录用户进行认证

用户不能直接去访问资源服务器（网关），必须先到认证服务器认证，
通过后颁发一个token令牌给你，你只有拿着token访问资源服务器才能通过，
令牌token是有时间限制的，到时间了就无效。


## 注册服务到nacos问题

目前服务启动完成，在nacos 可以查询到服务，但是通过feign 无法调用。得等30多秒之后才可以。

spring cloud feign报错：Load balancer does not have available server for clien

类似问题：<https://blog.csdn.net/qq32933432/article/details/105823229>


## 遇到异常

## com.sun.xml.bind.v2.runtime.reflect.opt.Injector.defineClass" is null
<https://gitmemory.com/issue/spring-projects/spring-boot/26637/846425446>
在启动的时候 VM 添加以下配置 
> --illegal-access=permit

Quickly I found this post: https://www.gitmemory.com/issue/highsource/maven-jaxb2-plugin/201/802605932 
That points to JEP 396: Strongly Encapsulate JDK Internals by Default (JDK-8256299). 
That triggered this behavior. Adding --illegal-access=permit to the VM options resolves the problem. 
Not sure if this is the "best solution" but at least a workaround for now.