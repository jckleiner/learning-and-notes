
# Spring Security

* Spring Security Architecture: https://spring.io/guides/topicals/spring-security-architecture
* Spring Security Filters Chain: https://www.javadevjournal.com/spring-security/spring-security-filters/




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