

nohup java -server -Xmx50m -Xms250m -jar ./target/userServer-1.0-SNAPSHOT.jar  --spring.cloud.bootstrap.location=config --spring.profiles.active=release --server.port=8092 & 2>1



#nohup java -server -Xmx500m -Xms250m -jar /Users/linglong/Documents/code/fishCloud/userServer/target/userServer-1.0-SNAPSHOT.jar --server.port=8092 >> start.out &

#java -server -Xmx500m -Xms250m -jar /Users/linglong/Documents/code/fishCloud/userServer/target/userServer-1.0-SNAPSHOT.jar --server.port=8092