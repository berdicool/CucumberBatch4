package API;

import Utils.PayloadUtil;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

public class PostRequestWithVerification {
    @Test
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");
        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Accept","application/json");
        String name="hatiko";
        String status="waiting";
        int id=1956;
        HttpEntity entity=new StringEntity(PayloadUtil.getPetPayLoad(id,name,status));
        httpPost.setEntity(entity);
        HttpResponse httpResponse=httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode());
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, Object> parsedResponse=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<>() {
                });
        int actualId=(int)parsedResponse.get("id");
        String actualName=parsedResponse.get("name").toString();
        String actualStatus=parsedResponse.get("status").toString();
        Assert.assertEquals(id,actualId);
        Assert.assertEquals(name,actualName);
        Assert.assertEquals(status,actualStatus);

        uriBuilder.setPath("v2/pet/"+id);
        HttpGet httpGet =new HttpGet(uriBuilder.build());
        httpResponse=httpClient.execute(httpGet);
        Assert.assertEquals(HttpStatus.SC_OK,httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals("application/json",httpResponse.getEntity().getContentType().getValue());
        parsedResponse=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<>() {
                });
        Assert.assertEquals(id,parsedResponse.get("id"));
        Assert.assertEquals(name,parsedResponse.get("name"));
        Assert.assertEquals(status,parsedResponse.get("status"));
    }
}
