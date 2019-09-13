# mamba-forever-lakers

　湖人总冠军🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆🏆



## 注意

如果项目启动读取 redis 的配置老是 localhost:6379 ，但是我们确实是配置了集群。
那么只能说明是IDEA 工程有问题!!!

- 解决办法
>  重新导入项目，或者提交代码之后，重新clone 项目。之前尝试过 gradle build 不起作用.

## 如果这个项目出现问题了。
执行
> gradle clean build -x test

不要问我为什么... 就是项目需要重新构建，执行这个如果启动项目出现问题，那么你需 rebuild project


## 启动项目
> gradle clean source:jar package -P prod


## 遇到异常

### SLF4J: Class path contains multiple SLF4J bindings.

JAR 冲突,因为我项目添加zookeeper，需要移除冲突JAR

<https://stackoverflow.com/questions/18952479/how-to-exclude-multiple-slf4j-bindings-to-log4j>

### Failed to load class "org.slf4j.impl.StaticLoggerBinder


### Gradle build fails on Lombok annotated classes
IDEA 2019.2 GRADLE 5.X LOMBOK 1.18.8  JDK12
<https://stackoverflow.com/questions/35236104/gradle-build-fails-on-lombok-annotated-classes>

```groovy
dependencies{

compileOnly 'org.projectlombok:lombok:1.18.8'
annotationProcessor 'org.projectlombok:lombok:1.18.8'

}
```

### : [AdminClient clientId=adminclient-1] Connection to node -1 could not be established. Broker may not be available.

### [spring多模块依赖时，被依赖模块的配置文件不生效的问题解决](https://blog.csdn.net/u014520745/article/details/82706040)

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

###  [spring boot multi-project dependencies](https://github.com/spring-projects/spring-boot/issues/9242)

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


### Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
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


### [Spring Cloud Stream如何消费自己生产的消息](http://blog.didispace.com/spring-cloud-starter-finchley-7-1/)

> org.springframework.beans.factory.BeanDefinitionStoreException: Invalid bean definition with name 'example-topic' defined in com.didispace.stream.TestTopic: bean definition with this name already exists - Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=com.didispace.stream.TestTopic; factoryMethodName=input; initMethodName=null; destroyMethodName=null

### idea 错误 Two modules in a project cannot share the same content root

直接把项目 .idea 和.gradle 删除，重新导入项目，然后导入模块.

## 测试用例 
> CREATE DATABASE IF NOT EXISTS unit_test default charset utf8 COLLATE utf8_general_ci; 
>
参考 <https://www.cnblogs.com/AdaiCoffee/p/10700097.html>

### java.lang.NoClassDefFoundError: org/mockito/MockitoAnnotations$Mock

> 把  powermock-api-mockito 修改成 'powermock-api-mockito2 
> https://github.com/powermock/powermock/issues/678
>
>https://github.com/powermock/powermock/issues/684
>
> 依赖的mockito已经是2.x了，然后powermock虽然对应有一个api，但是并不能真正的支持，[springboot2.x 单元测试 mockito powermock 兼容性问题解决]
> https://webcache.googleusercontent.com/search?q=cache:yZStUqwRlgwJ:https://blog.csdn.net/u013076044/article/details/99109487+&cd=2&hl=en&ct=clnk&gl=hk


## [Springboot 2.0选择HikariCP作为默认数据库连接池的五大理由](http://blog.didispace.com/Springboot-2-0-HikariCP-default-reason/)