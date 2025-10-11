# If you installed Temurin 21 via Chocolatey, this is the typical path:
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

# Verify:
java -version
mvn -v
