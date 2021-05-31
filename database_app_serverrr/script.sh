/etc/init.d/mysql start

cd /app
mvn install
mvn clean package
timeout 40s mvn spring-boot:run

export MYSQL_PWD=password
/usr/bin/mysql -u root -e "CREATE DATABASE IF NOT EXISTS kasaraba_lab5;"
/usr/bin/mysql -u root -e "FLUSH PRIVILEGES;"
/usr/bin/mysql -u root kasaraba_lab5 < src/main/resources/script_lab5.sql

mvn spring-boot:run