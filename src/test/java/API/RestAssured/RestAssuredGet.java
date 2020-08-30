package API.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredGet {
    @Before
    public void setup(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
    }
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .when().get("123")
                .then().contentType(ContentType.JSON).statusCode(200)
                .log().body();
    }
    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .when().get("{id}","123")
                .then().contentType(ContentType.JSON).statusCode(200)
                .log().body();
    }
    @Test
    public void test3(){
        given().accept(ContentType.JSON)
                .when().request("GET","123")
                .then().statusCode(200)
                .log().body();
    }
    @Test
    public void test4(){
        given().accept(ContentType.JSON)
                .when().request("GET","{id}","123")
                .then().statusCode(200)
                .log().body();
    }
}
