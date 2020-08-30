package API.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssured2 {
    @Test
    public void test1(){
        given().header("accept", ContentType.JSON)
                .when().get("https://breakingbadapi.com/api/characters/50")
                .then().log().ifValidationFails().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .body("[0].name", Matchers.equalToIgnoringCase("juan bolsa"))
                .and().body("[0].occupation", Matchers.hasSize(1));
    }
    @Test
    public void test2(){
        given().header("accept", ContentType.JSON)
                .when().get("https://breakingbadapi.com/api/characters/50")
                .then().log().all().statusCode(200).contentType(ContentType.JSON)
                .and().rootPath("[0]").body("status",Matchers.is("Deceased"))
                .and().body("nickname",Matchers.equalTo("Don Juan"))
                .and().body("appearance.size()",Matchers.greaterThan(1))
                .and().body("appearance[1]",Matchers.is(4));
    }
    @Test
    public void test3(){
        RestAssured.baseURI="https://api.got.show";
        RestAssured.basePath="api/map/characters/byId/5cc0743504e71a0010b852d9";
        given().header("accept", ContentType.JSON).when().get()
                .then().log().ifStatusCodeIsEqualTo(500).rootPath("data")
                .assertThat().statusCode(200).contentType(ContentType.JSON)
                .body("books",Matchers.hasItem("A Storm of Swords"))
                .body("dateOfBirth", Matchers.equalTo(283))
                .body("house", Matchers.is("House Stark"))
                .body("male",Matchers.isA(Boolean.class));
    }
}
