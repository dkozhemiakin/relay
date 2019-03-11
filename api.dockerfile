FROM maven:3.6.0-jdk-8-alpine AS compiler
WORKDIR /build
COPY api api/
COPY cassandra cassandra/
COPY pom.xml .
RUN mvn package -P api

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=compiler /build/api/target/api-1.0-SNAPSHOT.jar ./api.jar
ENTRYPOINT java -Dcassandra.servers=$CASSANDRA_SERVERS \
                -Dcassandra.port=$CASSANDRA_PORT \
                -Dcassandra.keyspace=$CASSANDRA_KEYSPACE \
                -jar api.jar