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

public class UsersVerification {
    @Test
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("itunes.apple.com").setPath("search")
                .setCustomQuery("term=Imagine Dragons");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        HttpResponse httpResponse=httpClient.execute(httpGet);
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> jsObject = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});
        List<Map<String, Object>> data = (List<Map<String, Object>>)jsObject.get("results");
        for (Map<String,Object> n:data){
            String temp=""+n.get("artistName");
            Assert.assertTrue(temp.contains("Imagine Dragons"));
        }

    }
}
