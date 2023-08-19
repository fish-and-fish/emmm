FROM jdk:11
VOLUME /tmp
ADD userServer/target/userServer-1.0-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=prd"]


http://admin:11ba2d7fbd56743759319f0f18711e9a9b@192.168.50.105:8080/job/ziyou-build/build?token=build