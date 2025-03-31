# Java Code Upload API (for Coze Plugin)

Spring Boot service to receive `.java` file uploads and return the file content. Can be deployed to Railway for integration with Coze AI agent plugins.

## API

- `POST /api/file/upload`  
  Content-Type: `multipart/form-data`  
  Param: `file` (.java)

## Build

```bash
mvn clean package
```

## Run locally

```bash
java -jar target/java-code-upload-api-1.0.0.jar
```

## Deploy to Railway

- Push this repo to GitHub
- Import it into Railway
- Set build command: `mvn clean package`
- Set start command: `java -jar target/java-code-upload-api-1.0.0.jar`
