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

不要问我为什么... 就是项目需要重新构建


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

## [Springboot 2.0选择HikariCP作为默认数据库连接池的五大理由](http://blog.didispace.com/Springboot-2-0-HikariCP-default-reason/)