package API;

import API.Pojo.PetPojo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;

public class PetReq {
    @Test
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/12");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        HttpResponse httpResponse=httpClient.execute(httpGet);
        ObjectMapper objectMapper=new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        PetPojo deserializedObject=objectMapper.readValue(httpResponse.getEntity().getContent(), PetPojo.class);
        System.out.println(deserializedObject.getId());
    }
}
