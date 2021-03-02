

# 如何配置gitlab使用sonar扫描和执行单元测试

## [gitlab 安装](https://github.com/hi-mamba/study-notes/tree/master/022%E3%80%81tools%E3%80%90%E5%B7%A5%E5%85%B7%E7%AE%B1%F0%9F%A7%B0%E3%80%91/050%E3%80%81gitlab%E3%80%90%E4%BB%93%E5%BA%93%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F%E3%80%91)



## 环境依赖
- docker
- docker-compose
- gitlab
- gitlab-runner
- sonarqube
- maven
 

## docker 安装与配置
<https://github.com/hi-mamba/docker-learning/blob/master/02%E3%80%81docker%20%E5%AE%89%E8%A3%85%E3%80%81%E5%85%A5%E9%97%A8/03%E3%80%81docker%E5%AE%89%E8%A3%85%E5%8F%8A%E5%85%B6%E5%8D%B8%E8%BD%BD.md> 

## docker-compose 安装与配置

<https://github.com/hi-mamba/docker-learning/blob/master/02%E3%80%81docker%20%E5%AE%89%E8%A3%85%E3%80%81%E5%85%A5%E9%97%A8/07%E3%80%81docker-compose%E5%AE%89%E8%A3%85.md> 

## [maven 安装与配置](03、maven安装与配置.md)


## gitlab 安装与配置


## [gitlab-runner 安装](04、gitlab-runner 安装与配置)
 

## [sonarqube安装与配置](https://github.com/hi-mamba/study-notes/blob/master/022%E3%80%81tools%E3%80%90%E5%B7%A5%E5%85%B7%E7%AE%B1%F0%9F%A7%B0%E3%80%91/051%E3%80%81sonar%E4%BB%A3%E7%A0%81%E8%B4%A8%E9%87%8F%E7%AE%A1%E7%90%86/01%E3%80%81%E7%AE%80%E4%BB%8B%E5%8F%8A%E5%85%B6%E5%AE%89%E8%A3%85.md)

### 注意事项

安装的 sonarqube 是社区版，因此没有gitlab 插件，需要导入这个jar
 
<https://github.com/gabrie-allaigre/sonar-gitlab-plugin>
 
使用这个版本 <https://github.com/gabrie-allaigre/sonar-gitlab-plugin/releases/download/4.1.0-SNAPSHOT/sonar-gitlab-plugin-4.1.0-SNAPSHOT.jar>
 

社区版本push 上去都是在master，因此`.gitlab-ci.yml` 根据 commit 和 区分扫描
 
需要在gtilab 创建一个账号且生成一个token给 sonar 扫描完成的时候使用
需要 sonar 生成一个公共的token 给gitlab 使用
 

SonarQube Community 实现多分支扫描分析，使用配置好的，就不需要关心这个配置


## 项目如何接入soanr 扫描

项目需要拥有master 权限才可以查看

> http://gitlab.com/mamba/你的项目/settings/ci_cd



目前我们 选择了 Specific Runners 这个需要每个项目都需要配置【我推荐是使用 Shared Runners（这个就不需要每个项目都激活，点击enable就可以了），
但是这个需要更高的权限，你懂的】

 
## 选择 Specific Runners 

token，你需要拷贝这个激活【gitlab-runner 的机器】 

激活的 命令 
```shell script
docker run --rm -t -i -v /tol/soft/gitlab-runner/config:/etc/gitlab-runner gitlab/gitlab-runner register
```

## 你需要在你的项目 配置好

> key: SONAR_LOGIN_TOKEN  value: xxxx
 

项目根目录添加 .gitlab-ci.yml
```yaml
stages:
 - inspection

sonar-preview:
  stage: inspection
  image: mamba/docker-maven-mysql-kobe:1.0.0
  script:
    - mvn --batch-mode sonar:sonar -Dsonar.host.url=http://sonar.kobe.com -Dsonar.login=${SONAR_LOGIN_TOKEN} -Dsonar.sourceEncoding=UTF-8 -Dsonar.projectName=${CI_PROJECT_NAMESPACE}:${CI_PROJECT_NAME}:master -Dsonar.projectKey=${CI_PROJECT_PATH_SLUG}:master -Dsonar.gitlab.project_id=${CI_PROJECT_PATH} -Dsonar.gitlab.commit_sha=${CI_COMMIT_SHA} -Dsonar.gitlab.ref_name=${CI_COMMIT_REF_NAME} -Dsonar.gitlab.failure_notification_mode=exit-code -Dsonar.gitlab.issue_filter=MAJOR -Dsonar.java.binaries=target/sonar
  except:
    - master
  tags:
    - ci

sonar-analyze:
  stage: inspection
  image: mamba/docker-maven-mysql-kobe:1.0.0
  script:
    - mvn --batch-mode sonar:sonar -Dsonar.host.url=http://sonar.kobe.com -Dsonar.login=${SONAR_LOGIN_TOKEN} -Dsonar.sourceEncoding=UTF-8 -Dsonar.projectName=${CI_PROJECT_NAMESPACE}:${CI_PROJECT_NAME}:master -Dsonar.projectKey=${CI_PROJECT_PATH_SLUG}:master -Dsonar.gitlab.project_id=${CI_PROJECT_PATH} -Dsonar.gitlab.commit_sha=${CI_COMMIT_SHA} -Dsonar.gitlab.ref_name=${CI_COMMIT_REF_NAME} -Dsonar.gitlab.failure_notification_mode=exit-code -Dsonar.projectVersion=${CI_COMMIT_SHA} -Dsonar.java.binaries=target/sonar
  only:
    - master
  tags:
    - ci
``` 





 