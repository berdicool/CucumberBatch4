package API.RestAssured;

import API.Pojo.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredDeserialization2 {
    @Test
    public void test1(){
        UserPojo parsedUser=given().header("accept", ContentType.JSON)
                .when().request("GET","https://reqres.in/api/users/2")
                .then().statusCode(200).and().contentType(ContentType.JSON)
                .extract().as(UserPojo.class);
        Assert.assertEquals("janet.weaver@reqres.in", parsedUser.getData().getEmail());
    }
    @Test
    public void test2(){
        //broke down first test in smaller cases
        //prerequisite
        RequestSpecification requestSpecification=given().header("accept",ContentType.JSON);
        //action-getting response
        Response response=requestSpecification.when().get("https://reqres.in/api/users/2");
        //deserialization
        ValidatableResponse validatableResponse=response.then().statusCode(200).and().contentType(ContentType.JSON);
        //validation2
        UserPojo deserializedUser=validatableResponse.log().all().extract().as(UserPojo.class);
        Assert.assertEquals("Janet",deserializedUser.getData().getFirst_name());
    }
}
