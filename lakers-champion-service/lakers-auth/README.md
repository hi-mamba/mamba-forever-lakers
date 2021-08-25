
# 权限系统

> OAuth是一个网络开放协议。为保证用户资源的安全授权提供了简易的标准


> Oauth2认证服务，负责对登录用户进行认证

用户不能直接去访问资源服务器（网关），必须先到认证服务器认证，
通过后颁发一个token令牌给你，你只有拿着token访问资源服务器才能通过，
令牌token是有时间限制的，到时间了就无效。


## [Oauth2.0协议流程](https://www.jianshu.com/p/243b85771be2)

### 2.1 角色
在介绍协议流程之前先要说明一下oauth2.0定义的几个角色：

- resource owner：资源所有者，这里可以理解为用户。

- client：客户端，可以理解为一个第三方的应用程序。 resource server：资源服务器，它存储用户或其它资源。

- authorization server：授权服务器，它认证resource owner的身份，为 resource owner提供授权审批流程，并最终颁发授权令牌(Access Token)。

- useragent：用户代理，这里可以理解为“浏览器”。

> 这里面有个需要注意的地方，这里只是在逻辑上把authorization server与resource server区分开来；
> 在物理上，authorization server与resource server的功能可以由同一个服务器来提供服务

### 2.2 授权过程
```
（A）用户打开客户端以后，客户端要求用户给予授权。
（B）用户同意给予客户端授权。
（C）客户端使用上一步获得的授权，向认证服务器申请令牌。
（D）认证服务器对客户端进行认证以后，确认无误，同意发放令牌。
（E）客户端使用令牌，向资源服务器申请获取资源。
（F）资源服务器确认令牌无误，同意向客户端开放资源。
```
上面六个步骤之中，B是关键，即用户怎样才能给于客户端授权。有了这个授权以后，客户端就可以获取令牌，进而凭令牌获取资源。

## 四种授权模式

- Authorization Code（授权码模式）：正宗的OAuth2的授权模式，客户端先将用户导向认证服务器，登录后获取授权码，然后进行授权，最后根据授权码获取访问令牌；

- Implicit（简化模式）：和授权码模式相比，取消了获取授权码的过程，直接获取访问令牌；

- Resource Owner Password Credentials（密码模式）：客户端直接向用户获取用户名和密码，之后向认证服务器获取访问令牌；

- Client Credentials（客户端模式）：客户端直接通过客户端认证（比如client_id和client_secret）从认证服务器获取访问令牌。

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


