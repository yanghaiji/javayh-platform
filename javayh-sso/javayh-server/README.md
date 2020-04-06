## javayh-server

## 服务使用流程

- 初始化数据脚本 

  `doc/sql/sso/user.sql`

  `doc/sql/sso/oauth.sql`

- 启动服务服务获取code

  访问：[http://localhost:9090/oauth/authorize?client_id=client&response_type=code](http://localhost:9090/oauth/authorize?client_id=client&response_type=code)

  初次需要登录，如下图
  
   **登录名：admin ,  密码：123456**
   
![full stack developer tutorial](../../doc/img/oauthLogin.JPG)

 **这时我们选择统一授权，获取code**
 
![full stack developer tutorial](../../doc/img/code.JPG)


- 获取token

这里我们使用postman获取token

访问：[http://client:secret@localhost:9090/oauth/token](http://client:secret@localhost:9090/oauth/token)

如下图：
 
![full stack developer tutorial](../../doc/img/token.JPG)
