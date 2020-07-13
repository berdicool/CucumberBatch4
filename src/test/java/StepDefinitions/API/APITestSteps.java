package StepDefinitions.API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Utils.PayloadUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class APITestSteps {
    HttpClient client;
    URIBuilder uriBuilder;
    HttpResponse response;
    String name;
    String status;
    int id;
    @When("user creates a pet with id, name, status")
    public void user_creates_a_pet_with_id_name_status() throws URISyntaxException, IOException {
        System.out.println("Constructing request");
        client = HttpClientBuilder.create().build();
        uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");
        HttpPost post = new HttpPost(uriBuilder.build());
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        name = "hatiko";
        status = "waiting";
        id = 1956;
        System.out.println("Building request body");
        HttpEntity entity = new StringEntity(PayloadUtil.getPetPayLoad(id, name, status));
        post.setEntity(entity);
        System.out.println("Started POST method execution");
        response = client.execute(post);
        System.out.println("Finished POST method execution");
    }

    @Then("the status code is OK")
    public void the_status_code_is_OK() {
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Then("pet with id, name, status is created")
    public void pet_with_id_name_status_is_created() throws IOException, URISyntaxException {
        System.out.println("Mapping response body with Java object");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
        });
        int actualId = (int) parseResponse.get("id");
        String actualName = parseResponse.get("name").toString();
        String actualStatus = parseResponse.get("status").toString();
        Assert.assertEquals(id, actualId);
        Assert.assertEquals(name, actualName);
        Assert.assertEquals(status, actualStatus);
        //Execute GET request and do verifications
        uriBuilder.setPath("v2/pet/" + id);
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        System.out.println("Started executing GET request");
        response = client.execute(get);
        System.out.println("Finished executing GET request");
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        Assert.assertEquals("application/json", response.getEntity().getContentType().getValue());
        System.out.println("Deserializing response");
        parseResponse = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        Assert.assertEquals(id, parseResponse.get("id"));
        Assert.assertEquals(name, parseResponse.get("name"));
        Assert.assertEquals(status, parseResponse.get("status"));
    }

}
