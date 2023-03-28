# spring-native to use jasperreport


build jar
```
mvn -Pnative clean package
```

use tracing agent
```
# run with agent
java -Dspring.aot.enabled=true -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image/ -jar target/native-jasperreport-0.0.1-SNAPSHOT.jar
# then call api to generate hint files
curl localhost:8080/getpdf
```

can use nativeTest to test
```
mvn -PnativeTest test
```

build executable file
```
mvn -Pnative native:compile
```
use distroless as base image build
```
docker build -t native-jasperreport:0.0.1 .
```
run container
```
docker run -itd -p 8080:8080 native-jasperreport:0.0.1
```
check service status
```
curl localhost:8080/getpdf
# will get test pdf
```
