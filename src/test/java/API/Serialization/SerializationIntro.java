package API.Serialization;

import Utils.PayloadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerializationIntro {
    @Test
    public void serialize1() throws IOException {
        Pet pet=new Pet("hatiko","wating",3);
        pet.setId(1);
        pet.setPhotoUrl("https://s3.petpics.amazon.com");
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(new File("target/pet.json"),pet);
    }
    @Test
    public void serialize2() throws IOException {
        Car car = new Car("BMW", 1980, 12000);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/car.json"),car);
    }
    @Test
    public void createPet() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("v2/pet");
        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept","application/json");
        httpPost.setHeader("Content-Type","application/json");
        String petPayLoad= PayloadUtil.generateStringFromResource("target/pet.json");
        HttpEntity httpEntity=new StringEntity(petPayLoad);
        httpPost.setEntity(httpEntity);
        HttpResponse httpResponse=httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode());
    }
}
