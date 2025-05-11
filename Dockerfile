FROM ubuntu/jre:21-24.04
LABEL maintainer=Akhsaul

WORKDIR /app
COPY build/libs/FleetGPSTrack-1.0.0.jar ./jars/FleetGPSTrack-1.0.0.jar

ENTRYPOINT ["java", "-jar", "./jars/FleetGPSTrack-1.0.0.jar"]
EXPOSE 8080