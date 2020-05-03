# For Java 8
FROM openjdk:8-jdk-alpine

# Maven build
ARG JAR_FILE=target/product-cart-backend-0.0.1-SNAPSHOT.jar

# cd /opt/app
# WORKDIR /target/

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} product-backend.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","product-backend.jar", "--spring.profiles.active=dev"]
