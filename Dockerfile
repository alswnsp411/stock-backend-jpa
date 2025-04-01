FROM openjdk:21-jdk-slim

WORKDIR /app

# JAR 파일 복사
COPY build/libs/stock-backend-0.0.1-SNAPSHOT.jar skala-stock.jar

# 포트 노출 (기본 Spring Boot 포트)
EXPOSE 8080

# 환경 변수 설정 (선택 사항)
# ENV SPRING_PROFILES_ACTIVE=prod

# 컨테이너 실행 시 실행될 명령어
ENTRYPOINT ["java", "-jar", "/app/skala-stock.jar"]