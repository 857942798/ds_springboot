CLIClient.java : CLI�ͻ��˿�
CLIResponse.java : ����CLI�������Ľӿ�
CLIDemo.java : CLI�ͻ���ʾ��֮����ִ��
CLIDemo2.java : CLI�ͻ���ʾ��֮�ļ��ϴ�
CLIDemoResponse.java : CLI�ͻ���ʾ������CLI�������Ľӿ�ʵ��
cli.keystore : ��Կ���ļ�ʾ��
cli-test.private : ˽Կ�ļ�ʾ����PEM��ʽ��
cli-test.pub �� ��Կ�ļ�ʾ����PEM��ʽ��

����CLI�ͻ���ʾ����
java CLIDemo <cli-server-name> <cli-service-port> <cli-command-1> <cli-command-1> ����
java CLIDemo2 <cli-server-name> <cli-service-port> <file-to-upload>

���磺
java CLIDemo 127.0.0.1 15000 "show version" "show cache info"
java CLIDemo2 127.0.0.1 15000 readme.txt

keytool����������Կ����ʹ��RSA�㷨��������-keyalg rsa��
--------------------------------------------------
��keystore����˽Կ��PEM��ʽ����
1) keytool -v -importkeystore -srckeystore cli.keystore -destkeystore cli.pfx -deststoretype pkcs12
2) openssl pkcs12 -in cli.pfx -nocerts -nodes -out cli-test.private
--------------------------------------------------
��keystore����֤�飨PEM��ʽ����
1) keytool -list -rfc -keystore cli.keystore
2) ���ơ�-----BEGIN CERTIFICATE-----������-----END CERTIFICATE-----��֮������ݣ�����������cli-test.pub
