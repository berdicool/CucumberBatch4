package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class DeserializationIntro {
    @Test
    public void deserialization1() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/1");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        HttpResponse httpResponse=httpClient.execute(httpGet);
        if(httpResponse.getStatusLine().getStatusCode()!=200){
            Assert.fail();
        }
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> deserializedRespones = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<>() {
        });
        System.out.println(deserializedRespones.get("id"));
        Map<String,Object> category = (Map<String,Object>) deserializedRespones.get("category");
        System.out.println(category.get("name"));
    }
    @Test
    public void deserialization2()throws URISyntaxException, IOException{
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/2");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        HttpResponse httpResponse=httpClient.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode());
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> deserializedRespone = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<>() {
                });
        Map<String,String> ad =(Map<String,String>) deserializedRespone.get("ad");
        System.out.println(ad.get("company"));
    }
}
