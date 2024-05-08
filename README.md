# rds-orm-api
一个将关系型数据库操作使用http接口代替的的服务|A service that replaces relational database operations with http interfaces
方便对于无法快速接入数据库服务的开发者使用,
可以使用http 接口快速插入数据。

# 注意：
目前项目没有做任何的防SQL注入处理，请谨慎使用

# quick start
## 部署
### docker (推荐)
### 手动编译部署
```shell
git clone https://github.com/DeanWanghewei/rds-orm-api.git
cd rds-orm-api
mvn clean package -Dmaven.test.skip=true
java -jar target/rds-orm-api.jar
```
## 使用
1. 注册数据库连接账号