FROM maven:latest
COPY . /build
WORKDIR /build
RUN mvn clean package
RUN ls -al target
FROM java:8
WORKDIR /opt/todo
COPY --from=0 /build/target/banking-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]

