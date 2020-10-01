FROM openjdk:11
ADD /target/super-todo-list-0.0.1.jar super-todo-list-0.0.1.jar
ENV JAVA_OPTS="-server"
ENTRYPOINT exec java $JAVA_OPTS -jar /super-todo-list-0.0.1.jar