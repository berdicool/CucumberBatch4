package StepDefinitions.API;

import API.Pojo.PetPojo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class PetStoreSteps {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Given("contentType head is set to {string}")
    public void contenttype_head_is_set_to(String requestHeader) {
        requestSpecification=given().header("accept", requestHeader);
    }

    @When("user executes {string} request")
    public void user_executes_GET_request(String requestType) {
        response=requestSpecification.request(requestType,"https://petstore.swagger.io/v2/pet/10");
    }

    @Then("contentType header is {string}")
    public void contenttype_header_is(String responseHeader) {
        validatableResponse=response.then().contentType(responseHeader);
    }

    @Then("users verified {int} {string} {int} size")
    public void users_verified_doggie_size(Integer id, String name, Integer tagsSize) {
        PetPojo petPojo=validatableResponse.extract().as(PetPojo.class);
        Assert.assertEquals((int)id, petPojo.getId());
        Assert.assertEquals(name, petPojo.getName());
        Assert.assertEquals((int)tagsSize, petPojo.getTags().size());
    }
}
