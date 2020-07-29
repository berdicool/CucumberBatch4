package API.RestAssured;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;

public class RestAssuredPost {
    @Test
    public void createPet(){
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(sessionId);
    }
}
