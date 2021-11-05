# Liquibase

* https://reflectoring.io/database-migration-spring-boot-liquibase/
* https://blog.orbit.de/2014/10/20/liquibase-das-tool-fuer-agiles-database-deployment/


Start a MariaDB container for testing purposes:

    docker run \
        --name mariadb \
        -e MYSQL_ROOT_PASSWORD=123456 \
        -e MYSQL_DATABASE=my_database \
        -e MYSQL_USER=my_db_user \
        -e MYSQL_PASSWORD=123 \
        -d \
        -p 3306:3306 \
        mariadb --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

