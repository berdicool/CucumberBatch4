package API.RestAssured;

import API.Pojo.CompetitionPojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredAdvanced {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    @Before
    public void setup(){
        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";
        requestSpecification=new RequestSpecBuilder().setAccept(ContentType.JSON).build();
        responseSpecification=new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        response=given().spec(requestSpecification).when().get().then().spec(responseSpecification)
                .extract().response();
    }
    @Test
    public void test1(){
        CompetitionPojo competitionParsed=response.getBody().as(CompetitionPojo.class);
        System.out.println(competitionParsed.getCompetitions().get(0).get("name"));
        int count=0;
        for (int i=0;i<competitionParsed.getCompetitions().size();i++){
            if ((int)competitionParsed.getCompetitions().get(i).get("id")>2100){
                System.out.println(competitionParsed.getCompetitions().get(i).get("name"));
            }
            count++;
        }
        System.out.println(count);
    }
    @Test
    public void test2(){
        JsonPath jsonPath=response.jsonPath();
        List<Map<String,Object>> competitions=jsonPath.getList("competitions");
        for (Map<String,Object> map:competitions){
            int id=(int)map.get("id");
            if(id>2100){
                System.out.println(map.get("name"));
            }
        }
    }
    @Test
    public void test3(){
        List<String> competitionList=response.path("competitions.findAll {it.id > 2100}.name");
        System.out.println(competitionList);
    }
    @Test
    public void test4(){
        List<String> competitionList=response.path("competitions.findAll {it.area.name == 'Mexico'}.name");
        System.out.println(competitionList);
    }

}
