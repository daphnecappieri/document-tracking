# Document Tracking Application

Document Tracking app that allows users to upload text documents, create users and join teams.

This is a pretty typical spring-boot microservice.  You can build and run it like:

```mvn clean install```

```mvn spring-boot:run```

### Database Config
Database configuration can be updated through the application.properties file


### Curls Examples

###### Create user:
```
curl --location --request POST 'localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName":"dave",
"lastName":"dave",
"email":"dave@dave.com",
"team":"apples"
}'
```
###### Update users teams:
```
curl --location --request PUT 'localhost:8080/changeTeam?email=kate@kate.com&teamName=apples'
```
######  Upload document:
```
curl --location --request POST 'localhost:8080/upload?email=dave@dave.com' \
--form 'file=@"/C:/path/to/document/test.txt"'
```
###### Find word frequency of a document:
```
curl --location --request POST 'localhost:8080/wordFrequency' \
--form 'file=@"/C:/path/to/document/test.txt"'
```
