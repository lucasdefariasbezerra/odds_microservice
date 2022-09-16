FROM openjdk:11

EXPOSE 8080

ENV ODDS_DB_URL=jdbc:mysql://mysql8:3306/teams_db?allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=UTC
ENV ODDS_DB_USER=user_odds
ENV ODDS_DB_PASSWORD=*********

ENV APP_HOME=/app/
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
