docker network create -d overlay --attachable sqli-network
docker stack deploy -c mysql_stack.yml sqldeploy