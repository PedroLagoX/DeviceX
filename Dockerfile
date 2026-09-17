# =========================================
# ETAPA 1 — BUILD
# =========================================

FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia os arquivos de configuração primeiro
# para aproveitar o cache do Docker.
COPY pom.xml .

# Baixa as dependências do projeto.
RUN mvn dependency:go-offline -B

# Copia o código-fonte.
COPY src ./src

# Compila e gera o JAR.
RUN mvn clean package -DskipTests -B


# =========================================
# ETAPA 2 — EXECUÇÃO
# =========================================

FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR gerado na etapa anterior.
COPY --from=builder /app/target/*.jar app.jar

# Render fornece a porta através da variável PORT.
# Caso PORT não exista, usamos 8080 localmente.
EXPOSE 8080

# Inicia o Spring Boot.
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080} --server.address=0.0.0.0"]