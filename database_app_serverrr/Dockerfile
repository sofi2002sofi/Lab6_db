FROM alpine/git as clone
WORKDIR /app
RUN head -c 5 /dev/random > random_bytes && git clone https://github.com/VKasaraba/java_spring-boot_app.git
WORKDIR /app/java_spring-boot_app

FROM ubuntu/mysql:latest as ubuntu
RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install openjdk-11-jdk git maven vim apt-utils systemctl sudo mysql-server

RUN chown -R mysql:root /var/lib/mysql/

RUN echo "Created a new file in Docker" > newfile.txt

EXPOSE 8080
WORKDIR /app
COPY --from=clone /app/java_spring-boot_app /app

COPY script.sh script.sh
CMD chmod +x script.sh && ./script.sh
