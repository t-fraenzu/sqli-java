# Excercises voulenaribilty java - jakarta EE

Needed Software:

IDE:  IntelliJ V2022.2
Docker: podman / docker 
JDK: 17 (any openJDK)
Wildfly: 26.1.2

### Installing Wildfly
    download distribution https://www.wildfly.org/downloads/
	unzip into /sqli-java/wildfly

start service

    sqli-java/wildfly/bin/standalone.bat

open jboss-cli 

    jboss-cli -c

within jboss-cli add jdbcdriver module **(replace driver resource with your own path)**

    module add --name=com.mysql.driver8  --dependencies=javax.api,javax.transaction.api --resources=C:\git\sqli-java\db\mysql\mysql-connector-java-8.0.30.jar

restart wildfly -> create driver

    /subsystem=datasources/jdbc-driver=mysql/:add(driver-module-name=com.mysql.driver8,driver-name=mysql,driver-class-name=com.mysql.jdbc.Driver)

create datasource -> **password & target mysql service ist passed here**

    data-source add --jndi-name=java:/MySqli --name=MySqlPool --connection-url=jdbc:mysql://localhost:3307/jakartajdbc --driver-name=mysql --user-name=jakartaUser --password=jakartaPassword
     data-source add --jndi-name=java:/MySqliJpa --name=MySqlPool2 --connection-url=jdbc:mysql://localhost:3307/jakartajpa --driver-name=mysql --user-name=jakartaUser --password=jakartaPassword

leave jboss-cli
create management console user for verify datasource
    
    pwd -> wildfly/bin

add admin user to access / verify over management console (user / password)

    add-user.bat admin admin
    
    http://127.0.0.1:9990/console


### Installing mysql container

see configuration of mysql service  myphpAdmin in mysql_stack.yml

    cd /sqli-java/db
    docker network create -d overlay --attachable sqli-network
    docker stack deploy -c mysql_stack.yml sqldeploy


### setup wilfly run config in intellij

![config1_setup](/doc/startupConfig.PNG)
![config1_wilfly](/doc/wilfly_config.PNG)
![config1_deployment](/doc/deployment_config.PNG)
![config1_start](/doc/local_debugging.PNG)


### Sources of instruction and setup:

wildfly: https://reachmnadeem.wordpress.com/2021/05/13/install-and-configure-mysql-jdbc-driver-on-jboss-wildfly/
mysql image: > https://towardsdatascience.com/how-to-run-mysql-and-phpmyadmin-using-docker-17dfe107eab7
JEE: https://www.digitalocean.com/community/tutorials/java-datasource-jdbc-datasource-example#jdbc-datasource
