FROM maven:3.6.0-jdk-8-alpine AS compiler
WORKDIR /build
COPY consumer consumer/
COPY cassandra cassandra/
COPY model model/
COPY pom.xml .
RUN mvn package -P consumer

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=compiler /build/consumer/target/consumer-1.0-SNAPSHOT.jar ./consumer.jar
ENTRYPOINT java -Dkafka.bootstrap-servers=$KAFKA_SERVERS \
                -Dkafka.topic=$KAFKA_TOPIC \
                -Dcassandra.servers=$CASSANDRA_SERVERS \
                -Dcassandra.port=$CASSANDRA_PORT \
                -Dcassandra.keyspace=$CASSANDRA_KEYSPACE \
                -jar consumer.jar