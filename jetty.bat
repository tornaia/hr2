SET JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8
call mvn -Djetty.skip=true -DskipTests -Dmaven.test.skip clean install
cd hr2-e2etest
start mvn -DskipTests -Dmaven.test.skip jetty:run-war -Dhr_environment=jetty
cd ..
start http://localhost:8080/