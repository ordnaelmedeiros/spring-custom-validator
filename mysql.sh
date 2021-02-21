docker rm --force mysql
docker run \
    -d \
    --name mysql \
    -p 3306:3306 \
    -e MYSQL_DATABASE=teste \
    -e MYSQL_ROOT_PASSWORD=teste \
    -e MYSQL_USER=teste \
    -e MYSQL_PASSWORD=teste \
    -v $PWD/data:/var/lib/mysql \
    mysql:8.0.23