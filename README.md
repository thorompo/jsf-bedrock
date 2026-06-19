# jsf-bedrock

How to run:
1. ```mvn clean package```
2. ```docker build -t jsf-bedrock-app .```
3. ```docker run -d -p 8080:8080 --name my-jee-server jsf-bedrock-app```

Remove the container:
```docker rm -f my-jee-server```