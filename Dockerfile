FROM maven:3.6.1-jdk-8-alpine
COPY ./ /home/es/
WORKDIR /home/es
RUN mvn package
ENTRYPOINT ["java", "-jar", "target/elastic-0.0.1.jar"]
