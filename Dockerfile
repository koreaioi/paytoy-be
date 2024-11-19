# open jdk 17 버전의 환경을 구성
FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/*.jar 
COPY ${JAR_FILE} application.jar
# 운영 환경으로 실행
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/application.jar"]
