FROM adoptopenjdk as builder
WORKDIR application
#ARG JAR_FILE=
COPY target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk
WORKDIR application
ENV GOOGLE_APPLICATION_CREDENTIALS=./creds.json
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/application/ ./
ENV GOOGLE_APPLICATION_CREDENTIALS BOOT-INF/classes/golfwithmeservice-firebase.json
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]