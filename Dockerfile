# build
FROM maven
WORKDIR application
COPY pom.xml .
RUN mvn -f ./pom.xml clean package -q
#RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.1:go-offline
#COPY . .
#RUN mvn -B -e -o -T 1C verify

FROM adoptopenjdk as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/application/ ./
ENV GOOGLE_APPLICATION_CREDENTIALS BOOT-INF/classes/golfwithmeservice-firebase.json
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]