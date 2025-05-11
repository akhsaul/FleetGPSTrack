FROM azul/zulu-openjdk:21-jre
LABEL maintainer=Akhsaul

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY build/libs/ConfigServer-1.0.0.jar ./jars/FleetGPSTrack-ConfigServer-1.0.0.jar

ENTRYPOINT ["java", "-jar", "./jars/FleetGPSTrack-ConfigServer-1.0.0.jar"]
EXPOSE 8888