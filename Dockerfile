FROM adoptopenjdk as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${GOOGLE_APPLICATION_CREDENTIALS} /
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk
WORKDIR application
COPY --from=builder application/dependencies/ ./
RUN true
COPY --from=builder application/snapshot-dependencies/ ./
RUN true
COPY --from=builder application/spring-boot-loader/ ./
RUN true
COPY --from=builder application/application/ ./
COPY --from=builder ${GOOGLE_APPLICATION_CREDENTIALS} ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]