package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {

    private static RequestSpecification requestSpecification;
    public RequestSpecification getRequestSpecification() throws IOException {
        if(requestSpecification==null) {
            Path path = Paths.get("logging.txt");
            PrintStream printStream = new PrintStream(Files.newOutputStream(path));
            requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .setContentType(ContentType.JSON).build();
        }
        return requestSpecification;
    }

    public ResponseSpecification getResponseSpecification(){
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectHeader("Server", "Apache/2.4.41 (Ubuntu)").build();
        return responseSpecification;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")
        +"//src//test//resources//global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public String getJsonValue(Response response, String key){
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.get(key).toString();
    }
}
