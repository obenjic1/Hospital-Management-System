
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY target/billing.war app.war


EXPOSE 8080


CMD ["java", "-jar", "app.war"]
