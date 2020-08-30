package API.RestAssured;

import API.Pojo.PetPojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredPost {

    public static final String NAME="name";
    public static final String STATUS="status";
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    @Before
    public void setup(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
        requestSpecification= new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON).build();
        responseSpecification=new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200).build();
    }
    @Test
    public void test1(){
        File petPayloadFile=new File("target/pet.json");
        given().spec(requestSpecification).body(petPayloadFile)
                .when().post()
                .then().spec(responseSpecification)
                .body(NAME, Matchers.equalTo("hatiko")).and()
                .body(STATUS, Matchers.is("waiting"));
    }
    @Test
    public void test2(){
        PetPojo petPojo=new PetPojo("hatiko","gone",1011);
        given().spec(requestSpecification).body(petPojo)
                .when().post()
                .then().spec(responseSpecification)
                .and().body(NAME, Matchers.is(petPojo.getName()))
                .and().body(STATUS, Matchers.is(petPojo.getStatus()));
    }
    @Test
    public void test3(){
        Map<String,Object> petPayload=new HashMap<>();
        petPayload.put(NAME,"hatiko");
        petPayload.put("age",7);
        petPayload.put(STATUS,"gone");
        petPayload.put("id",1012);
        petPayload.put("photoUrls",new String[]{});
        given().spec(requestSpecification).body(petPayload)
                .when().post()
                .then().spec(responseSpecification)
                .and().body(NAME, Matchers.is(petPayload.get(NAME)));
    }
}
