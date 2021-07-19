FROM adoptopenjdk/openjdk11:alpine

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/api.jar

EXPOSE 8080

CMD ["java","-jar","api.jar"]
