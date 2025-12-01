#FROM openjdk:17-jdk-slim
FROM openjdk:26-ea-jdk-slim
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
RUN mvn clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/stocks-analysis-1.0.0.jar"]