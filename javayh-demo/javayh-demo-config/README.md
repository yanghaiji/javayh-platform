## javayh-demo-config

这里主要讲解nacos分布式配置中心多环节的配置
###
```
  cloud:
   nacos:
     discovery:
       server-addr: 127.0.0.1:8848
       cluster-name: javayh-nacos
     config:
       group: haiji_dev
       server-addr: 127.0.0.1:8848
       prefix: javayh-demo
       file-extension: yml
       namespace: d7503bcd-e44c-475a-88d4-d28cbfb444fc
       shared-dataids: redis.yml
```
**配置中心实例**
![full stack developer tutorial](../../doc/config.png)

`spring.cloud.nacos.config.group`: 分组信息，默认是 DEFAULT_GROUP
`spring.cloud.nacos.config.namespace`: 环境的唯一id
`spring.cloud.nacos.config.shared-dataids`: 需要加载的共享资源 ，这里对用配置文中心的`Data ID`
`spring.cloud.nacos.config.refreshable-dataids`: 与shared-dataids效果一样，但是支持自动刷新
