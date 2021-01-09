# SsmDemo

spring springmvc 与 mybatis 做的关于一个表的增删改查(符合REST API),并且使用拦截器做了登录验证
和全局异常处理统一了异常回复

## 接口示范
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/54e791c6ba460624d463)

## 前端vue
使用vue cli 3 ,实现了登录拦截,登录后跳转。
使用axios拦截器做给全局请求添加验证头等
