FROM amazoncorretto:17-alpine
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven
RUN mvn clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/stocks-analysis-1.0.0.jar"]