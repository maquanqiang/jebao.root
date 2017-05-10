echo mvn package begin
call mvn clean && mvn compile && mvn install -DskipTests
echo mvn package end