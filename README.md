# plugin-template-springboot

## Build
```shell
./mvnw package
```

## Run
```shell
./mvnw spring-boot:run
```
or (requires build):
```shell
java -jar target/plugin-template-0.0.1-SNAPSHOT.jar
```

## How to create your own plugin based on this template
* Copy the files or clone the repository to a new directory
* Remove the .git folder if present or change the origin repository
* In the pom.xml change the artifactId, name and description
* Rename the package from plugintemplate to the new name, both under main and test (use a tool, which is able to automatically update the package specification in the source code files accordingly)
* Rename the PluginTemplateApplication.java
* Rename the PluginTemplateApplicationTests.java
* In the application.properties, set the values for the plugin.technology and the plugin.analysis-type and change the server.port
* change this Readme
* add the analysis and transformation logic in the AnalysisService

