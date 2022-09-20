

jboss-cli -c

module add --name=com.mysql.driver8  --dependencies=javax.api,javax.transaction.api --resources=C:\gitSchule\sqli-java\db\mysql\mysql-connector-java-8.0.30.jar

/subsystem=datasources/jdbc-driver=mysql/:add(driver-module-name=com.mysql.driver8,driver-name=mysql,driver-class-name=com.mysql.jdbc.Driver)

data-source add --jndi-name=java:/MySqli --name=MySqlPool --connection-url=jdbc:mysql://localhost:3307/jakartajdbc --driver-name=mysql --user-name=jakartaUser --password=jakartaPassword
add-user.bat admin admin

docker network create -d overlay --attachable sqli-network
docker stack deploy -c mysql_stack.yml sqldeploy