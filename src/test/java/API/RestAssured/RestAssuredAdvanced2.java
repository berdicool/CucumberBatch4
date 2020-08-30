package API.RestAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestAssuredAdvanced2 {
    @Test
    public void test1(){
        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "v2/competitions/2000/scorers";
        RestAssured.requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON)
                .addHeader("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        Response response=given().spec(requestSpecification).when().get().then().spec(responseSpecification)
                .extract().response();
        Map<String, ?> map=response.as(Map.class);
        System.out.println(map.get("count"));
    }
    //executing from terminal
//    curl.exe -X GET "http://api.football-data.org/v2/competitions/2000/scorers"
//        -H "X-Auth-Token:c55b7a64e8424d46a52051bce36d1c0a" | jq.exe ".scorers[].player.name"
}
