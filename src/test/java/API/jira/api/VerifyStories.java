package API.jira.api;

import Utils.BrowserUtils;
import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VerifyStories {
    HttpClient httpClient;
    URIBuilder uriBuilder;
    @Before
    public void setUp(){
        httpClient= HttpClientBuilder.create().build();
        uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api2/search");
    }
    @Test
    public void verifyNumberOfStories() throws URISyntaxException, IOException {
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Content-Type","application/json");
        httpGet.setHeader("Accept","application/json");
        httpGet.setHeader("Cookie", PayloadUtil.JSessionCookie());
        HttpResponse httpResponse=httpClient.execute(httpGet);
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> parsedResponse=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<>() {
                });
        List<Map<String,Object>> issueList=(List<Map<String,Object>>)parsedResponse.get("Issues");
        Assert.assertEquals(5, issueList.size());
        Set<String> issueKeys=new HashSet<>();
        for (Map<String,Object> map:issueList){
            issueKeys.add((String)map.get("key"));
        }
        Assert.assertEquals(5,issueKeys.size());
    }
}
