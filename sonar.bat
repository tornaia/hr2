SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
SET MAVEN_OPTS=-server -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=256m -XX:+DisableExplicitGC
mvn -Dhr_environment=test clean test verify sonar:sonar