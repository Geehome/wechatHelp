echo "mvn starting packaging........."

cd wechatHelp
mvn clean install -Dmaven.test.skip=true

echo "executing springboot project........"
java -jar target/demo-0.0.1-SNAPSHOT.jar