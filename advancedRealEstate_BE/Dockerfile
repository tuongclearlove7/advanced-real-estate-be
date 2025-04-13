# ---------- STAGE 1: Build ứng dụng với Maven ----------
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Đặt thư mục làm việc
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . .

# Build ra file .jar
RUN mvn clean package -DskipTests


# ---------- STAGE 2: Tạo image chạy thật sự ----------
FROM openjdk:21-jre-slim

# Thư mục chạy ứng dụng
WORKDIR /app

# Copy file .jar đã build từ stage 1
COPY --from=build /app/target/*.jar app.jar

# Mở port (quan trọng cho Render!)
EXPOSE 8080

# Lệnh chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
