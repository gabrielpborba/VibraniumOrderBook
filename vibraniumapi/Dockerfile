# Use uma imagem base com o Java 17
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR da aplicação para o contêiner
COPY target/*.jar vibraniumapi.jar

# Defina o comando de inicialização da aplicação
CMD ["java", "-jar", "vibraniumapi.jar"]