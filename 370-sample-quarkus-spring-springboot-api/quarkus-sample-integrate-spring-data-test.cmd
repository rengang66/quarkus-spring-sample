
curl -i -X GET -u reng:123456 http://localhost:8080/projects

curl -i -X GET -u reng:123456 http://localhost:8080/projects/5

curl -X  POST -u reng:123456 -H "Content-type: application/json" -d {\"id\":6,\"name\":\"项目F\",\"description\":\"关于项目F的描述\"} http://localhost:8080/projects/add

curl -X PUT -u reng:123456 -H "Content-type: application/json" -d {\"id\":6,\"name\":\"项目F\",\"description\":\"关于项目F的描述的修改\"} http://localhost:8080/projects/update

curl -X DELETE -u reng:123456 -H "Content-type: application/json" -d {\"id\":6,\"name\":\"项目F\",\"description\":\"关于项目F的描述\"} http://localhost:8080/projects/delete


pause