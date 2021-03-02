

# maven 安装与配置

## 安装
这个maven 安装在你申请的机器上，因为在之后配置的 `.gitlab-ci.yml` 会使用到maven仓库

1、前提：先安装jdk

2、获取maven安装包
> wget  https://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.6.2/binaries/apache-maven-3.6.2-bin.tar.gz

3、解压 `tar -zxvf apache-maven-3.6.2-bin.tar.gz`

假设当前环境为 /data/soft/maven/

4、配置环境变量
> vim  ~/.bash_profile

在尾部追加行：
```
export MAVEN_HOME=/data/soft/maven/apache-maven-3.6.2
export PATH=${MAVEN_HOME}/bin:{PATH}
```
5、最后，使更改生效

> source /etc/profile

6、看下是否安装成功

> mvn -v

遇到问题
> prompt_status:5: command not found: wc 

解决方案： https://stackoverflow.com/a/18428774

 

## 构建自己的maven + mysql 镜像
maven在配置 `.gitlab-ci.yml` 的时候会使用，mysql 在执行单元测试的时候会使用

### 编写Dockerfile
宿主机执行命令，在家目录下创建一个dockerfiles/maven文件夹用来存放我们的配置文件以及Dockerfile文件
```shell script
$ cd ~
$ mkdir -p dockerfiles/maven
```
在maven目录下创建两个文件，分别是settings.xml与Dockerfile

```shell script
$ cd dockerfiles/maven
$ touch settings.xml Dockerfile
```

编写Dockerfile，内容如下(把编写好的配置settings.xml复制到镜像内)
```dockerfile
FROM bitnami/mysql:5.7.26
FROM maven:3.6.0-jdk-8-alpine
COPY settings.xml /usr/share/maven/ref/
```
 
编辑 settings.xml，主要是对maven的repository与mirror进行配置，内容如下

```xml 
<settings xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
http://maven.apache.org/xsd/settings-1.0.0.xsd">

<servers>
<server>
<id>releases</id>
<username>xxx</username>
<password>xxx</password>
</server>
<server>
<id>thirdparty</id>
<username>xxx</username>
<password>xxx</password>
</server>
<server>
<id>snapshots</id>
<username>xxx</username>
<password>xxx</password>
</server>
</servers>

<profiles>

<profile>
<id>dev-nexus</id>
<properties>
<maven.compiler.source>1.8</maven.compiler.source>
<maven.compiler.target>1.8</maven.compiler.target>
</properties>
<repositories>
<repository>
<id>local-nexus</id>
<url>http://maven.aliyun.com/nexus/content/groups/public/</url>
<releases>
<enabled>true</enabled>
<!-- <updatePolicy>always</updatePolicy> -->
</releases>
<snapshots>
<enabled>true</enabled>
<!-- <updatePolicy>always</updatePolicy> -->
</snapshots>
</repository>
</repositories>
<pluginRepositories>
<pluginRepository>
<id>local-nexus</id>
<url>http://maven.aliyun.com/nexus/content/groups/public/</url>
<releases>
<enabled>true</enabled>
</releases>
<snapshots>
<enabled>true</enabled>
</snapshots>
</pluginRepository>
</pluginRepositories>
</profile>
</profiles>
<activeProfiles>
<activeProfile>dev-nexus</activeProfile>
</activeProfiles>

<localRepository>/usr/local/repository</localRepository>

<mirrors>
<mirror>
<id>ali maven</id>
<mirrorOf>central</mirrorOf>
<name>ali maven</name>
<url>http://maven.aliyun.com/nexus/content/groups/public/</url>
</mirror>
</mirrors>

</settings>
```

### 构建镜像
在Dockerfile文件所在目录执行如下命令进行镜像的构建，就是docker build命令
```shell script
$ docker build -t mamba/docker-maven-mysql-kobe .
```
注意这个镜像名称 `mamba` 是的docker 账号，不能修改成其他的，注意 -kobe 最后的点号

构建自己的镜像这里需要push 到 https://hub.docker.com 这里需要登录，
因为在执行 `.gitlab-ci.yml` 的时候会去拉取镜像

```shell script
$ docker login
```
输入用户名

输入密码

 
$ docker push mamba/docker-maven-mysql-kobe