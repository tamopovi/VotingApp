# VotingApp
Simplified president election application backend implemented in Java with Springboot, REST and h2 database.
## Requirements
  - JDK 11
  
## API Documentation
API documentation can be found in swagger-ui.

You can see and interact with implemented endpoints in: `http://localhost:8080/swagger-ui.html`

## H2 database
You should be able to open the h2 database via url: 
```
http://localhost:8080/h2-console
   ```

In this window your JDBC URL field should look like the one in the field `spring.datasource.url`

```
src/main/resources/application.properties
```
## Entities
- Candidate
- Voter