package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class UserVerification {
    @Test
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users/");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        HttpResponse httpResponse=httpClient.execute(httpGet);
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> parsedResponse=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<>() {
                });
        List<Map<String,Object>> users = (List<Map<String, Object>>) parsedResponse.get("data");
        Assert.assertEquals(parsedResponse.get("per_page"),users.size());
        int sum=0;
        for (Map<String, Object> user:users){
            sum+=(int)user.get("id");
        }
        System.out.println(sum);
    }
}
