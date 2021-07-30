# Liquibase

see https://reflectoring.io/database-migration-spring-boot-liquibase/



Start a MariaDB container:

    docker run \
        --name mariadb \
        -e MYSQL_ROOT_PASSWORD=123456 \
        -e MYSQL_DATABASE=my_db \
        -e MYSQL_USER=my_db_user \
        -e MYSQL_PASSWORD=123456 \
        -d \
        -p 3306:3306 \
        mariadb --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

