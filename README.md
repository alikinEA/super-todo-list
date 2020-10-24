# Super-todo-list
### Description
Project for testing various technologies and approaches.

Implemented:
- spring security JWT based authentication and authorization
- rate limit on redis buckets
- flyway + postgres

### run:
1. mvn clean install
2. docker-compose up --force-recreate --remove-orphans --build

### Db migration:
mvn flyway:migrate -Dflyway.locations=filesystem:flyway -Dflyway.user=postgres -Dflyway.password=password -Dflyway.url=jdbc:postgresql://localhost:5432/postgres -f pom.xml