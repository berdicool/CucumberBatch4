package API;

import API.Pojo.UserPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class UserCall {
    @Test
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/7");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        HttpResponse httpResponse=httpClient.execute(httpGet);
        ObjectMapper objectMapper=new ObjectMapper();
        UserPojo deserializedObject=objectMapper.readValue(httpResponse.getEntity().getContent(), UserPojo.class);
        System.out.println(deserializedObject.getData().getAvatar());
        System.out.println(deserializedObject.getData().getFirst_name());
    }
}
