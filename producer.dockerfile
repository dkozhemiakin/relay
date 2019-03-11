FROM maven:3.6.0-jdk-8-alpine AS compiler
WORKDIR /build
COPY producer producer/
COPY model model/
COPY pom.xml .
RUN mvn package -P producer

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=compiler /build/producer/target/producer-1.0-SNAPSHOT.jar ./producer.jar
ENTRYPOINT java -Ddevice.amount=$DEVICES_AMOUNT \
                -Ddevice.timeout-seconds=$DEVICES_TIMEOUT \
                -Dkafka.bootstrap-servers=$KAFKA_SERVERS \
                -Dkafka.topic=$KAFKA_TOPIC \
                -jar producer.jar