FROM mcr.microsoft.com/java/jre:8u202-zulu-alpine

WORKDIR /app

COPY ./target/newsletter-*.jar /app/newsletter.jar

CMD ["java", "-jar", "/app/newsletter.jar"]