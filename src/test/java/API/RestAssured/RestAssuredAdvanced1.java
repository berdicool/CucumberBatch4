package API.RestAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class RestAssuredAdvanced1 {
    Response response;
    @Before
    public void setup() {
        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "v2/competitions/2000/scorers";
        RestAssured.requestSpecification = new RequestSpecBuilder().setAccept(ContentType.JSON)
                .addHeader("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        response = given().spec(requestSpecification).when().get().then().spec(responseSpecification)
                .extract().response();
    }
    @Test
    public void test1(){
        int numberOfGoalsHarryKane=response.path("scorers.find {it.player.name=='Harry Kane'}.numberOfGoals");
        Assert.assertEquals(6,numberOfGoalsHarryKane);
    }
    @Test
    public void test2(){
        String countryCheryshev=response.path("scorers.find {it.player.name" +
                "=='Denis Cheryshev'}.player.nationality");
        Assert.assertEquals("Russia", countryCheryshev);
    }
    @Test
    public void test3(){
        String highestScorer=response.path("scorers.max {it.numberOfGoals}.player.name");
        System.out.println(highestScorer);
        String minimumScorer=response.path("scorers.min {it.numberOfGoals}.player.name");
        System.out.println(minimumScorer);
    }
    @Test
    public void test4(){
        int minGoals=response.path("scorers.min {it.numberOfGoals}.numberOfGoals");
        List<String> minimumScorers=response.path("scorers.findAll {it.numberOfGoals=="+minGoals+"}.player.name");
        System.out.println(minimumScorers);
    }
    @Test
    public void test5(){
        response.then().body("scorers.find {it.player.name=='Harry Kane'}.numberOfGoals", Matchers.is(6));
    }
    @Test
    public void test6(){
        response.then().assertThat().body("scorers.findAll {it.team.name=='Russia'}.size()",
                Matchers.equalTo(2));
    }
    @Test
    public void test7(){
        response.then().body("scorers.collect {it.team.name}", Matchers.hasItems("England", "Portugal"));
    }
}
