# ---------- STAGE 1: Build ứng dụng ----------
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Build ứng dụng, skip test để nhanh hơn
RUN mvn clean package -DskipTests


# ---------- STAGE 2: Chạy ứng dụng ----------
FROM openjdk:21-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Copy file JAR từ stage 1 sang stage 2
COPY --from=build /app/target/*.jar app.jar

# Mở cổng mặc định (hoặc Render sẽ tự điều phối)
EXPOSE 8080

# Khởi chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
