### Falcon Task

##### Requirements:
    Java 11, Docker, Docker compose

##### How to start application:

```text
1. docker-compose up
2. Await for containers to get up
3. Go to localhost in browser
4. Login with user:password
5. After authenticating copy <SESSION_ID>
6. Open Postman or use cURL requests below and POST to localhost/v1/api/message with:
   Cookie: <SESSION_ID> as header and such JSON payload: 
   { "message" : "some-message" }
```
   cURL example for posting messages:

   ```shell script
   curl --location --request POST 'localhost/v1/api/message' \
    --header 'Cookie: SESSION=NzJiOTU4ODgtMTlmMS00ZDBiLTg2MjItZTg0ZWIyNzhkODU5' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "message" : "some-message"
    }'
   ``` 
   
   cURL example for getting messages:
   
   ```shell script
    curl --location --request GET 'localhost/v1/api/message' \
    --header 'Cookie: SESSION=NzJiOTU4ODgtMTlmMS00ZDBiLTg2MjItZTg0ZWIyNzhkODU5'
  ```