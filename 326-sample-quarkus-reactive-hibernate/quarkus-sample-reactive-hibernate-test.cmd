
curl http://localhost:8080/projects

curl http://localhost:8080/projects/1

curl -X POST  -H "Content-type: application/json" -d {\"id\":6,\"name\":\"��ĿF\"} http://localhost:8080/projects


curl -X PUT -H "Content-type: application/json" -d {\"id\":4,\"name\":\"Project4\"} http://localhost:8080/projects/4 -v

curl -X DELETE http://localhost:8080/projects/6  -v

pause



