

curl http://localhost:8080/projects

curl http://localhost:8080/projects/1

curl -X POST  -H "Content-type: application/json" -d {\"id\":6,\"name\":\"��ĿF\"} http://localhost:8080/projects


curl -X PUT -H "Content-type: application/json" -d {\"id\":5,\"name\":\"Project5\"} http://localhost:8080/projects/5 -v

curl -X DELETE http://localhost:8080/projects/6  -v

curl -X DELETE http://localhost:8080/projects/1

pause

