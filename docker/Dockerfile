# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
ARG PROFILE=dev
WORKDIR /app
COPY --from=build /build/target/book-network-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8088
ENV DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book-social-network
ENV ACTIVE_PROFILE=${PROFILE}

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} book-network-0.0.1-SNAPSHOT.jar
