#  javayh-platform

<p align="center">
  <img src='https://img.shields.io/badge/license-Apache%202-borightgreen' alt='License'/>
  <img src="https://img.shields.io/badge/Spring%20Boot-2.2.6.RELEASE-borightgreen" alt="SpringBoot"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR3-borightgreen" alt="SpringCloud"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.1.1.RELEASE-borightgreen" alt="SpringCloudAlibaba"/>
</p>

#### 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢
本项目处于开发阶段，想加入开发的小伙伴添加`微信:372787553 备注进群或者开发`
#### 如果Github clone 比较慢，请您移步Gittee：https://gitee.com/YangHaiJi/javayh-platform 
## 简介
javayh-platform 使用Springboot2.2.6为开发脚手架，SpringCloud为云端服务框架，
Nacos为注册中心、分布式配置管理中心，Oauth2协议实现统一授权，Mybatis作为持久层框架，
提供了代码生成器，SQL防注入，SwaggerAPI文档，Redis 作为缓存服务等强大的功能 
项目采用`JDK14`的开发环境,如果您想采用`JDK8`的环境运行，请将一下内容进行修改

```xml
    <java.version>1.8</java.version>
    <spring-boot.version>2.1.6.RELEASE</spring-boot.version>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <spring-cloud-dependencies.version>Greenwich.SR3</spring-cloud-dependencies.version>
```
项目中也有用到JDK14的几个新特性，您可将报错文件的代码进行修改

- 注
如果您对JDK14还不了解,请阅读JDK14重大更新 https://blog.csdn.net/weixin_38937840/article/details/105054595 这将对您的修改有所帮助
## 使用说明
- 项目使用说明 https://github.com/Dylan-haiji/javayh-platform/wiki
## 技术框架

| 框架                   | 说明        | 版本               |
|----------------------|-----------|------------------|
| JDK          | 开发环境     | 14         |
| SpringBoot           | 开发脚手架     | 2.2.6.RELEASE          |
| SpringCloud          | 云框架       | Hoxton.SR3   |
| SpringCloudGateway   | 路由        |                  |
| SpringCloudOpenFeign | 服务调用      |                  |
| SpringCloudHystrix   | 服务降级熔断    |                  |
| SpringCloudOauth2    | 资源权限验证    |                  |
| SpringCloudAlibaba   | 阿里微服务解决方案 | 2\.1\.1\.RELEASE |
| Alibaba  Nacos       | 注册中心、配置中心 | 1\.3\.3          |
| Zipkin               | 分布式链式调用   | 2\.9\.4          |
| Reids                | 非关系型数据库        |                  |

## 项目结构说明

```
javayh-platform                         父工程
├─javayh-demo                           测试区
│  ├─javayh-demo-api                    swagger
│  ├─javayh-demo-common         
│  ├─javayh-demo-config                 nacos 多环境配置
│  ├─javayh-demo-mail                   mail模板
│  └─javayh-demo-feign                  feign
├─javayh-monitor-center                 监控中心
│  ├─javayh-zipkin-center               zipkin链式调用服务监控
├─javayh-plugins                        插件服务
│  ├─javayh-generator                   代码生成器
│  ├─javayh-heartbeat-server            心跳检测服务端
├─javayh-route                          路由分发
│  └─javayh-api-gateway                 gateway
├─javayh-sso                            oauth
│  ├─javayh-resource                    资源服务器
│  └─javayh-server                      认证服务器
└─javayh-dependencies                   核心依赖
│   ├─javayh-common-starter             内部支撑
│   ├─javayh-data-sources-starter       关系型数据库
│   ├─javayh-heartbeat-starter          心跳检测依赖支持
│   ├─javayh-nacos-starter              nacos依赖支持
│   ├─javayh-redis-starter              reids依赖支持
│   ├─javayh-kafka-starter              kafka依赖支持
│   ├─javayh-mail-starter               mail依赖支持
│   ├─javayh-mybatis-starter            mybatis依赖支持
│   ├─javayh-oauth-starter              oauth2依赖支持
│   ├─javayh-log-starter                log日志依赖支持
└─  └─javayh-swagger-starter            Api文档支持
```
