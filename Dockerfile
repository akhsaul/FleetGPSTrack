FROM ghcr.io/graalvm/jdk-community:24

COPY build/libs/FleetGPSTrack-1.0.0.jar jars/FleetGPSTrack-1.0.0.jar

ENTRYPOINT ["java", "-jar", "jars/FleetGPSTrack-1.0.0.jar"]