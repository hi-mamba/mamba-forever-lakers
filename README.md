# mamba-forever-lakers

　湖人总冠军🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆

## 实现功能列表

##### [Redis 延迟任务队列](https://www.jianshu.com/p/63d5c42299f9)

#####  多个请求合并 
<https://juejin.im/post/5de35474e51d4532ed6daaeb>

#### 延时队列

##### 延时队列使用场景
那么什么时候需要用延时队列呢？常见的延时任务场景 举栗子：

- 订单在30分钟之内未支付则自动取消。
- 重试机制实现,把调用失败的接口放入一个固定延时的队列,到期后再重试。
- 新创建的店铺，如果在十天内都没有上传过商品，则自动发送消息提醒。
- 用户发起退款，如果三天内没有得到处理则通知相关运营人员。
- 预定会议后，需要在预定的时间点前十分钟通知各个与会人员参加会议。
- 关闭空闲连接，服务器中，有很多客户端的连接，空闲一段时间之后需要关闭之。
- 清理过期数据业务。比如缓存中的对象，超过了空闲时间，需要从缓存中移出。
- 多考生考试,到期全部考生必须交卷,要求时间非常准确的场景。

三、解决办法多如鸡毛

- 定期轮询（数据库等）
- JDK DelayQueue
- JDK Timer
- ScheduledExecutorService 周期性线程池
- 时间轮(kafka)
- 时间轮(Netty的HashedWheelTimer)
- Redis有序集合（zset）
- zookeeper之curator
- RabbitMQ
- Quartz,xxljob等定时任务框架
- Koala(考拉)
- JCronTab(仿crontab的java调度器)
- SchedulerX（阿里）
- 有赞延迟队列
- .....(鸡毛)

<https://juejin.im/post/5d822b7a6fb9a06b3260a9e6>


## 项目打包

启动项目，且指定环境
> gradle clean source:jar package -P prod


## 注意事项

如果项目启动读取 redis 的配置老是 localhost:6379 ，但是我们确实是配置了集群。
那么只能说明是IDEA 工程有问题!!!

- 解决办法
>  重新导入项目，或者提交代码之后，重新clone 项目。之前尝试过 gradle build 不起作用.

> 如果还是有问题，删除项目的 .gradle 和 .idea 文件夹，重新启动idea 之后选择次项目。

#### 如果这个项目出现问题了。
执行
> gradle clean build -x test

不要问我为什么... 就是项目需要重新构建，执行这个如果启动项目出现问题，那么你需 rebuild project


## 遇到异常

#### SLF4J: Class path contains multiple SLF4J bindings.
JAR 冲突,因为我项目添加zookeeper，需要移除冲突JAR
<https://stackoverflow.com/questions/18952479/how-to-exclude-multiple-slf4j-bindings-to-log4j>

#### Failed to load class "org.slf4j.impl.StaticLoggerBinder

#### Gradle build fails on Lombok annotated classes
IDEA 2019.2 GRADLE 5.X LOMBOK 1.18.8  JDK12
<https://stackoverflow.com/questions/35236104/gradle-build-fails-on-lombok-annotated-classes>

```groovy
dependencies{
compileOnly 'org.projectlombok:lombok:1.18.8'
annotationProcessor 'org.projectlombok:lombok:1.18.8'

}
```
如果项目这在测试类里面还是有问题,那么你需要添加插件
```groovy
 classpath "io.freefair.gradle:lombok-plugin:4.1.0"
 apply plugin: "io.freefair.lombok"
```
参考     
 [io.freefair.lombok](https://plugins.gradle.org/plugin/io.freefair.lombok)  
 [Applying Lombok plugin to Gradle causes “Could not find any public constructor” error](https://stackoverflow.com/questions/56327071/applying-lombok-plugin-to-gradle-causes-could-not-find-any-public-constructor)

#### : [AdminClient clientId=adminclient-1] Connection to node -1 could not be established. Broker may not be available.

#### [spring多模块依赖时，被依赖模块的配置文件不生效的问题解决](https://blog.csdn.net/u014520745/article/details/82706040)

- 方法一：
把log模块中的application.yml文件名改成application-log.yml

然后在web模块中添加如下配置，其实就是和本身模块引用多个配置文件一样，引用即可：
```yaml
spring:
 profiles:
    active: log,kobe
```

- 方法二（推荐方法，模块之间并不用写依赖配置）：   

直接在log模块的resource目录，添加一个config文件夹，
在里面创建application.yml文件即可：

官网也是这样介绍的： 
<https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-application-property-files>

####  [spring boot multi-project dependencies](https://github.com/spring-projects/spring-boot/issues/9242)

> spring boot gradle 多模块,在导入其他子模块编译的时候无法通过，提示不存在此包和这个类
```groovy
:library:compileJava UP-TO-DATE
:library:processResources NO-SOURCE
:library:classes UP-TO-DATE
:library:jar SKIPPED
/Users/yuval/dev/gs-multi-module/complete/application/src/main/java/hello/app/DemoApplication.java:10: error: package hello.service does not exist
import hello.service.Service;
                    ^
/Users/yuval/dev/gs-multi-module/complete/application/src/main/java/hello/app/DemoApplication.java:11: error: package hello.service does not exist
import hello.service.ServiceConfiguration;
                    ^
/Users/yuval/dev/gs-multi-module/complete/application/src/main/java/hello/app/DemoApplication.java:18: error: cannot find symbol
    private final Service service;
                  ^
  symbol:   class Service
  location: class DemoApplication
/Users/yuval/dev/gs-multi-module/complete/application/src/main/java/hello/app/DemoApplication.java:21: error: cannot find symbol
    public DemoApplication(Service service) {
                           ^
  symbol:   class Service
  location: class DemoApplication
/Users/yuval/dev/gs-multi-module/complete/application/src/main/java/hello/app/DemoApplication.java:14: error: cannot find symbol
@Import(ServiceConfiguration.class)
        ^
  symbol: class ServiceConfiguration
5 errors
:application:compileJava FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':application:compileJava'.
> Compilation failed; see the compiler error output for details.
``` 

- 解决方法  
@mrmeisen in your lib project's build.gradle disable bootJar and re-enable the jar tasks via

```groovy
bootJar { enabled = false }
jar {enabled = true}
```
可以在 subprojects{} 里面添加

#### Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
```
Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2019-08-21 16:36:17.368 ERROR 87870 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).


Process finished with exit code 1
```

查阅资料，网上大体有三种解决方案：

1、使用exclude= {DataSourceAutoConfiguration.class}排除DataSource的自动配置；

2、检查是否将resources文件夹设置成了Resources Root目录（名字是否有写错）；

3、在pom.xml的build标签中添加resources指定资源目录；

上面的都解决不了我这个项目说的这个问题

- 最后的解决方案：

[springboot项目提示“Failed to determine a suitable driver class”](https://www.jiweichengzhu.com/article/e6cbb2d6aa7648f1b3046ba7e8580803)

IDEA Build 执行下这个就解决了
> rebuild project

由于我的项目使用gradle 在项目执行   gradle  clean build -x test  启动项目就会有这样的问题

[IDEA 不自动复制资源文件到编译目录 classes 的问题](https://blog.csdn.net/wungmc/article/details/53793177)


#### [Spring Cloud Stream如何消费自己生产的消息](http://blog.didispace.com/spring-cloud-starter-finchley-7-1/)

> org.springframework.beans.factory.BeanDefinitionStoreException: Invalid bean definition with name 'example-topic' defined in com.didispace.stream.TestTopic: bean definition with this name already exists - Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=com.didispace.stream.TestTopic; factoryMethodName=input; initMethodName=null; destroyMethodName=null

#### idea 错误 Two modules in a project cannot share the same content root

直接把项目 .idea 和.gradle 删除，重新导入项目，然后导入模块.


#### java.lang.NoClassDefFoundError: org/mockito/MockitoAnnotations$Mock

> 把  powermock-api-mockito 修改成 'powermock-api-mockito2 
> https://github.com/powermock/powermock/issues/678
>
>https://github.com/powermock/powermock/issues/684
>
> 依赖的mockito已经是2.x了，然后powermock虽然对应有一个api，但是并不能真正的支持，[springboot2.x 单元测试 mockito powermock 兼容性问题解决]
> https://webcache.googleusercontent.com/search?q=cache:yZStUqwRlgwJ:https://blog.csdn.net/u013076044/article/details/99109487+&cd=2&hl=en&ct=clnk&gl=hk

#### flyway 问题
// spring boot 到2.2.0 版本才支持 flyway6.0 ...
// testCompile group: 'org.flywaydb', name: 'flyway-core', version: '6.0.2'
// 为什么 build.gradle 这里设置成 testCompile?
// 因为在spring-boot-autoconfigure项目下有一个FlywayAutoConfiguration类，如果不设置testCompile会去加载 migration，导致项目启动不起来
testCompile group: 'org.flywaydb', name: 'flyway-core', version: '5.2.4'


#### [解决创建数据源(DataSource) Bean 冲突问题](http://www.gxitsky.com/2019/06/18/distributed-micro-app-8-sharding-jdbc-imp/)

druid-spring-boot-starter 默认开启了自动配置，在 application.properties 文件中配置多数据源的话，
因无法定义数据源名称而采用默认的，自动配置在创建多个数据源 Bean 时会存在冲突。

- 解决方案一：使用纯 druid 包 替换 druid-spring-boot-starter 包
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.12</version>
</dependency>
```
- 解决方案二：使用 druid-spring-boot-starter 包，但关闭自动配置
```xml
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }
}
```
实际数据源数据好后，Sharding-JDBC 就支持了读写分离。

## 技能包

- ✅ Kafka生产消费实现
- 分库分表的实现




## 如何执行项目测试用例 

前提条件
1. 必须安装有docker,然后启动docker.
2. 然后在项目 mamba-code-academy 执行 命令 $ docker-compose up 启动.
3. 就可以去执行测试用例了

测试用例使用项目技能包  
1. [Powermock2.0.0 详细 总结](https://www.cnblogs.com/AdaiCoffee/p/10700097.html)
2. [Flyway Programmatic Configuration (Java)](https://flywaydb.org/documentation/api/)
3. [Bitnami MySQL Docker Image](https://github.com/bitnami/bitnami-docker-mysql)




## [Springboot 2.0选择HikariCP作为默认数据库连接池的五大理由](http://blog.didispace.com/Springboot-2-0-HikariCP-default-reason/)
