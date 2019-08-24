
# MAMBA_PAPER

## [springboot与web前端的下划线与驼峰的json转换配置](https://www.jianshu.com/p/cb02796dfbd2)

```yaml
#命名规则下划线式
spring:
   jackson:
     property-naming-strategy: SNAKE_CASE
```

这样设置之后就是全局了,如果前端传递的不是下划线，而是驼峰，那么还是接收不到.

jackson 只支持@ResponseBody 以及@RequestBody,
而对于没带任何注解的实体类参数是没法处理的，所以就想怎么在解释实体参数时可以自定义解释逻辑，

[Spring - 关于Controller类方法里的实体参数解释原理](https://www.jianshu.com/p/59f5e3824520)

- 解决的方案
[Spring Boot Get请求参数为下划线式](http://www.appblog.cn/2019/06/10/Spring%20Boot%20Get%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0%E4%B8%BA%E4%B8%8B%E5%88%92%E7%BA%BF%E5%BC%8F/)

[spring mvc 自定义参数绑定，自定义数据返回格式](https://scutuyu.github.io/2018/12/18/spring-mvc-%E8%87%AA%E5%AE%9A%E4%B9%89%E5%8F%82%E6%95%B0%E7%BB%91%E5%AE%9A%EF%BC%8C%E8%87%AA%E5%AE%9A%E4%B9%89%E6%95%B0%E6%8D%AE%E8%BF%94%E5%9B%9E%E6%A0%BC%E5%BC%8F/)

[目前代码里使用这个方法](https://github.com/lemonzone2010/springmvc-demo/blob/master/src/main/java/com/example/springmvcdemo/controller/camel/SnakeToCamelServletRequestDataBinderFactory.java)


## springboot 中接口驼峰自动转下划线以及跨域代码块

[Spring MVC 响应json修改命名方式（驼峰修改为下划线）](https://segmentfault.com/a/1190000018635039)

- 方法一：在config里设置
 在spring config 中新建一个Jackson2ObjectMapperBuilderCustomizer 的bean 
 可以参考springboot中接口驼峰自动转下划线以及跨域代码块
```java
 @Bean
 public Jackson2ObjectMapperBuilderCustomizer customJackson() {
     return new Jackson2ObjectMapperBuilderCustomizer() {
         @Override
         public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
             //驼峰转换为下划线
             jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
         }
 
 };
```

- 方法二：在application.properties里设置
 在中application.properties设置一下属性，可以参考Spring Jackson property naming strategy

设置返回对象使用下划线 - (驼峰修改为下划线）
[Spring Jackson property naming strategy](https://javadeveloperzone.com/spring/spring-jackson-property-naming-strategy/)

```properties
 #命名规则下划线式
 spring.jackson.propertyNamingStrategy=SnakeCaseStrategy
 ```
- 方法三

参数返回转换实现：

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)：在实体类上使用，整个实体返回的json字段格式转换为下划线格式
或者：@JsonProperty：在字段名上使用，返回的json字段格式转换为下划线格式

例一：
```java

/**
 * @author pankui
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class UserInfo {
    private String userName;
}
```

例二： 
```java
@JsonProperty("pay_account")
private String payAccount;
```





