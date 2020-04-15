#  javayh-platform

<p align="center">
  <img src='https://img.shields.io/badge/license-Apache%202-borightgreen' alt='License'/>
  <img src="https://img.shields.io/badge/Spring%20Boot-2.2.6.RELEASE-borightgreen" alt="SpringBoot"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR3-borightgreen" alt="SpringCloud"/>
  <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.1.1.RELEASE-borightgreen" alt="SpringCloudAlibaba"/>
</p>

#### 如果您觉得有帮助，请点右上角 "Star" 支持一下谢谢
本项目处于开发阶段，想加入开发的小伙伴添加`微信:372787553 备注进群或者开发`
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
├─javayh-plugins                        插件
│  ├─javayh-generator                   代码生成器
│  ├─javayh-heartbeat-detection         服务心跳检测
│  │  ├─javayh-heartbeat-server         心跳检测服务端
│  └─ └─javayh-heartbeat-client         心跳检测客户端
├─javayh-route                          路由分发
│  └─javayh-api-gateway                 gateway
├─javayh-sso                            oauth
│  ├─javayh-resource                    资源服务器
│  └─javayh-server                      认证服务器
└─javayh-dependencies                   核心依赖
│   ├─javayh-common-starter             内部支撑
│   ├─javayh-db-starter                 关系型数据库
│   ├─javayh-nacos-starter              nacos依赖支持
│   ├─javayh-redis-starter              reids依赖支持
│   ├─javayh-kafka-starter              kafka依赖支持
│   ├─javayh-mail-starter               mail依赖支持
│   ├─javayh-mybatis-starter            mybatis依赖支持
└─  └─javayh-swagger-starter            Api文档支持
```
## 使用说明
项目使用说明 <a>https://github.com/Dylan-haiji/javayh-platform/wiki</a>
