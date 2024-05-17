build:
	mvn compile

package:
	mvn package

start:
	mvn spring-boot:start

run:
	mvn spring-boot:run

unit-test:
	mvn test

integration-test:
	mvn test -P integration-test

system-test:
	mvn test -P system-test

performance-test:
	mvn gatling:test -P performance-test

test: unit-test integration-test system-test

docker-build: package
	docker build -t reserva:latest -f ./Dockerfile .

docker-start:
	docker compose -f docker-compose.yaml up -d

docker-stop:
	docker compose -f docker-compose.yaml down