FROM adoptopenjdk as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
RUN mkdir gac.json
COPY ${GOOGLE_APPLICATOIN_CREDENTIALS} gac.json
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
ENV GOOGLE_APPLICATION_CREDENTIALS gac.json
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]