@echo off

cd configserver && call gradlew.bat assemble --info
cd .. && call gradlew.bat assemble --info
docker-compose -f fleet-gps-track.yml up
pause