package API.RestAssured;

import API.Pojo.AllPojo;
import API.Pojo.CatFactPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CatFacts {
    @Before
    public void setup(){
        RestAssured.baseURI="http://cat-fact.herokuapp.com";
        RestAssured.basePath="facts";
    }
    @Test
    public void test1(){
        Response response=given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get()
                .then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                .extract().response();
        AllPojo catParsed=response.as(AllPojo.class);
        Map<String,String> nameObject=(Map<String, String>)catParsed.getAll().get(24).getUser().get("name");
        System.out.println(catParsed.getAll().get(24).get_id());
        System.out.println(catParsed.getAll().get(24).getText());
        System.out.println(nameObject.get("first"));
        System.out.println(nameObject.get("last"));
    }
}
