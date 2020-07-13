package API;

import API.Pojo.SWPojo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class StarWars {
    @Test
    public void getStarShips() throws URISyntaxException, IOException {
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder builder=new URIBuilder();
        builder.setScheme("https");
        builder.setHost("swapi.dev");
        builder.setPath("api/starships");
        HttpGet get=new HttpGet(builder.build());
        get.setHeader("Accept","application/json");
        HttpResponse response=client.execute(get);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
        SWPojo swPojo=objectMapper.readValue(response.getEntity().getContent(), SWPojo.class);
        System.out.println(swPojo.getResults().get(0).getMGLT());
    }
}
