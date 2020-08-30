package API.jira.api;


import API.Pojo.CookieAuthPojo;
import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class CookieAuth {

    @Test
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/auth/1/session");
        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Accept","application/json");
        HttpEntity httpEntity=new StringEntity(PayloadUtil.cookieAuthPayload());
        httpPost.setEntity(httpEntity);
        HttpResponse httpResponse=httpClient.execute(httpPost);
        ObjectMapper objectMapper=new ObjectMapper();
        CookieAuthPojo cookieAuthPojo=objectMapper.readValue(httpResponse.getEntity().getContent(), CookieAuthPojo.class);
        System.out.println(cookieAuthPojo.getSession().get("name"));
        System.out.println(cookieAuthPojo.getSession().get("value"));
    }
    @Test
    public void test2() throws IOException, URISyntaxException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/issue");
        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Accept","application/json");
        httpPost.setHeader("Cookie",PayloadUtil.JSessionCookie());
        HttpEntity httpEntity=new StringEntity(PayloadUtil.getJiraIssuePayload("Creating a bug from API",
                "Create a bug using back-end API call, and verify in UI",
                "Bug"));
        httpPost.setEntity(httpEntity);
        HttpResponse httpResponse=httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_CREATED,httpResponse.getStatusLine().getStatusCode());
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,String> responseParsed=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<>() {
                });
        for (String key:responseParsed.keySet()){
            System.out.println(key+" : "+responseParsed.get(key));
        }
    }
}
