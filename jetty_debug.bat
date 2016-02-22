SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
SET MAVEN_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y
call mvn -DskipTests -Dmaven.test.skip clean install
cd hr2-e2etest
start mvn -DskipTests -Dmaven.test.skip jetty:run-war -Dhr_environment=jetty
cd ..
start http://localhost:8080/