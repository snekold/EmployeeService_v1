FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR файл в контейнер
COPY UniversityTime-0.0.1-SNAPSHOT.jar /app/web-project.jar

# Копируем сертификаты из папки ssh в контейнер
COPY ssh /app/ssh

# Открываем порт для приложения
EXPOSE 8443

# Устанавливаем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "/app/web-project.jar"]
