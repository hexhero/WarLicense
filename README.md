# WarLicense 为JAVA-WEB工程war包签权工具  

> :monkey:小王接了个做网站的私活,活干完验收完了, 甲方立马改了服务器密码, 小王的款还没结清,甲方联系不上怎么办。  
不用怕, 只需要下面几个简单的命令即可帮你解决难题.

JavaWeb工程最终打成war包交付，此工具对交付的war包增加授权。(该项目还在继续优化,可靠性低:running:)

主要功能:
* 限制war包部署主机的IP地址和MAC地址
* war包授权使用时间范围(开始时间-结束时间)

## 内容目录
* [安装环境](#安装环境)
* [使用说明](#使用说明)

## 安装环境

安装Python3.5版本并配置环境变量

## 使用说明

### 步骤一：软件加权
给war包加权，加权后的war包需要导入证书启动后才能正常提供服务。
将要加权的war包放入该工具目录下,执行以下命令
```shell
python3 permission.py demo.war
```
当前目录下会生成一个License_\*.war文件.该文件就是已加权的war包  
测试将 License_demo.war 文件部署在Tomcat中启动访问任意地址测试,系统均返回'授权错误',表示war包已加权。

### 步骤二：生成授权证书
执行 `python key.py` 输入应用要部署的服务器IP地址和相应的MAC地址,证书的生效时间范围信息,生成证书。

```shell
$ python3 key.py 
IP: 192.168.10.9
MAC: EA-EB-AA-A9-8E-FF
Begin_date[yyyy-MM-dd]: 2019-06-01
End_date[yyyy-MM-dd]: 2020-01-01

192.168.10.9:EA-EB-AA-A9-8E-FF:2019-06-01:2020-01-01
证书已生成至 WEB-INF/classes/cert.cer
```


### 步骤三: 导入证书
 将证书导入加权的war文件 License_demo.war
 
```shell
python3 import_cert.py License_demo.war
```

### 步骤四: 完成
License_demo.war 项目部署启动,检测证书有效性,检测成功，提供服务，证书失效或检测不到，系统拒绝服务.

## License
© 2025 [yangb](https://github.con/yangb92)
