CLIClient.java : CLI客户端库
CLIResponse.java : 处理CLI命令结果的接口
CLIDemo.java : CLI客户端示例之命令执行
CLIDemo2.java : CLI客户端示例之文件上传
CLIDemoResponse.java : CLI客户端示例处理CLI命令结果的接口实现
cli.keystore : 密钥库文件示例
cli-test.private : 私钥文件示例（PEM格式）
cli-test.pub ： 公钥文件示例（PEM格式）

运行CLI客户端示例：
java CLIDemo <cli-server-name> <cli-service-port> <cli-command-1> <cli-command-1> ……
java CLIDemo2 <cli-server-name> <cli-service-port> <file-to-upload>

例如：
java CLIDemo 127.0.0.1 15000 "show version" "show cache info"
java CLIDemo2 127.0.0.1 15000 readme.txt

keytool工具生成密钥对请使用RSA算法（参数：-keyalg rsa）
--------------------------------------------------
从keystore导出私钥（PEM格式）：
1) keytool -v -importkeystore -srckeystore cli.keystore -destkeystore cli.pfx -deststoretype pkcs12
2) openssl pkcs12 -in cli.pfx -nocerts -nodes -out cli-test.private
--------------------------------------------------
从keystore导出证书（PEM格式）：
1) keytool -list -rfc -keystore cli.keystore
2) 复制“-----BEGIN CERTIFICATE-----”到“-----END CERTIFICATE-----”之间的数据（包括本身）到cli-test.pub
