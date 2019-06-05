# WarLicense  :monkey:
(该项目还在继续优化,可靠性低)  
JavaWeb项目添加使用授权。功能包括限制项目只能部署在单台机器和软件使用期限。

### 步骤一：软件加权:

1. 将打好的war包拷贝至当前目录
2. 运行命令 `python3 permission.py demo.war` 在当前目录下生成 License_demo.war 文件

License_demo.war 文件部署在Tomcat中启动访问任意地址测试,系统返回'授权错误'

### 步骤二：生成授权证书
执行 `python key.py` 输入应用要部署的服务器IP地址和相应的MAC地址,证书的生效时间范围信息,生成证书。

### 步骤三:  导入证书
执行命令`python3 import_cert.py License_demo.war` 将证书导入License_demo.war

### 完成
License_demo.war 项目部署启动,检测证书有效性,启动完成.
