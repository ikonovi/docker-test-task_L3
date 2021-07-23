### Deployment Manually

1. Install RabbitMQ on Windows. Use administrator privileges during installation.
1. Create file `%APPDATA%\AppData\Roaming\RabbitMQ\rabbitmq.conf` with contents:
   ```
   # Ports on which to listen for "plain" AMQP 0-9-1 and AMQP 1.0 connections (without TLS).
   #listeners.tcp.default = 5672
   listeners.tcp.default = 8080
   ```
1. Restart RabbitMQ Windows service.
1. In RabbitMQ Command Prompt, execute commands:
   ```
   rabbitmqctl add_user usr
   rabbitmqctl set_permissions -p "/" "usr" ".*" ".*" ".*"
    ```
1. Install Tomcat
1. Open file `%CATALINA_HOME%/conf/server.xml` and change connector port `8080` to `8081` in line
   `<Connector port="8080" protocol="HTTP/1.1"`
1. Get project source from remote repo on local disk
   ```
   git clone http://qa-gitlab.itransition.corp/automation/docker-test-task.git
   ```
1. Build app by command from the root of project:
   ```
   mvn clean package
   ```
1. Copy file `<project-root>/target/docker-task-1.0.war` to `%CATALINA_HOME%/webapps/`
1. Open URL http://localhost:8081/docker-task-1.0/
