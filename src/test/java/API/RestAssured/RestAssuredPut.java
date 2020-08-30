package API.RestAssured;

import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredPut {
    @Test
    public void test1(){
        File updatePetPayload = new File("target/pet.json");
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(updatePetPayload)
                .when().put("https://petstore.swagger.io/v2/pet")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .and().body(RestAssuredPost.NAME, Matchers.equalTo("hatiko"))
                .and().body(RestAssuredPost.STATUS, Matchers.is("waiting"))
                .log().body();
    }
    @Test
    public void test2(){
        Map<String,Object> updatePetPayload=new HashMap<>();
        updatePetPayload.put(RestAssuredPost.NAME,"Pretzel");
        updatePetPayload.put("id","123");
        updatePetPayload.put(RestAssuredPost.STATUS, "available");
        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(updatePetPayload)
                .when().put("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).and().contentType(ContentType.JSON)
                .and().body(RestAssuredPost.NAME,Matchers.equalTo(updatePetPayload.get(RestAssuredPost.NAME)))
                .and().body(RestAssuredPost.STATUS, Matchers.is(updatePetPayload.get(RestAssuredPost.STATUS)))
                .log().body();
    }
}
