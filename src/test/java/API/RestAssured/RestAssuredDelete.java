package API.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredDelete {
    @Test
    public void test1(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
        given().accept(ContentType.JSON)
                .when().delete("123")
                .then().statusCode(200).contentType(ContentType.JSON)
                .log().body();
    }
}
