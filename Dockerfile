FROM openjdk:11
COPY . /app

EXPOSE 8080

ENV ODDS_DB_URL=jdbc:mysql://mysql8:3306/odds?allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=UTC
ENV ODDS_DB_USER=user_odds
ENV ODDS_DB_PASSWORD=*********

RUN cd /app/ && chmod +x gradlew && ./gradlew build -x test

CMD ["java", "-jar", "/app/build/libs/oddsproject-0.0.1-SNAPSHOT.jar"]