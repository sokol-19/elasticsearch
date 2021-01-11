FROM maven:3.6.1-jdk-8-alpine
VOLUME /home/es
COPY target/elastic-0.0.1.jar /home/es/
WORKDIR /home/es
ENTRYPOINT ["java", "-jar", "elastic-0.0.1.jar"]