### Falcon Task

##### How to start application:

```shell script
1. docker-compose up
2. Await for containers to get up
3. Go to localhost in browser
4. Login with user:password
5. After authenticating copy <SESSION_ID>
6. Open Postman and POST to localhost/v1/api/message with:
   Cookie: <SESSION_ID> as header and such JSON payload: 
   { "message" : "some-message" }
7. Your message will appear in the browser
```
