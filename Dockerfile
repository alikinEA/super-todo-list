FROM openjdk:11
ADD /target/super-todo-list-0.0.1.jar super-todo-list-0.0.1.jar
ENV JAVA_OPTS="-DredisHost=192.168.0.27 -DredisPort=6379  -Dhost=192.168.0.27 -Duser=postgres -Dpassword=password -DdatabaseName=postgres -DportNumber=5432 -server"
ENTRYPOINT exec java $JAVA_OPTS -jar /super-todo-list-0.0.1.jar