
curl -i -X GET -u reng:123456 http://localhost:8080/projects

curl -i -X GET -u reng:123456 http://localhost:8080/projects/5

curl -X  POST -u reng:123456 -H "Content-type: application/json" -d {\"id\":6,\"name\":\"��ĿF\",\"description\":\"������ĿF������\"} http://localhost:8080/projects/add

curl -X PUT -u reng:123456 -H "Content-type: application/json" -d {\"id\":6,\"name\":\"��ĿF\",\"description\":\"������ĿF���������޸�\"} http://localhost:8080/projects/update

curl -X DELETE -u reng:123456 -H "Content-type: application/json" -d {\"id\":6,\"name\":\"��ĿF\",\"description\":\"������ĿF������\"} http://localhost:8080/projects/delete


pause