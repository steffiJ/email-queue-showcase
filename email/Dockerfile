FROM mcr.microsoft.com/java/jre:8u202-zulu-alpine

WORKDIR /app

COPY ./target/email-*.jar /app/email.jar

CMD ["java", "-jar", "/app/email.jar"]