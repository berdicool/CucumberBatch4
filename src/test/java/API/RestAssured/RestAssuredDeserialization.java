package API.RestAssured;

import API.Pojo.BBCharPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredDeserialization {
    @Before
    public void setUp(){
        RestAssured.baseURI="https://breakingbadapi.com";
        RestAssured.basePath="api/characters";
    }
    @Test
    public void test1(){
        List<BBCharPojo> characterResp=given().header("accept", ContentType.JSON)
                .when().get("50")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().as(new TypeRef<>() {});
        Assert.assertEquals("Juan Bolsa", characterResp.get(0).getName());
    }
    @Test
    public void test2(){
        List<BBCharPojo> response=given().header("accept", ContentType.JSON).when().get("35")
                .then().statusCode(200).and().contentType(ContentType.JSON).extract()
                .as(new TypeRef<>() {});
        Assert.assertEquals("Unknown", response.get(0).getBirthday());
        Assert.assertEquals(2, response.get(0).getAppearance().size());
        Assert.assertEquals("Dr. Delcavoli", response.get(0).getNickname());
    }

}
