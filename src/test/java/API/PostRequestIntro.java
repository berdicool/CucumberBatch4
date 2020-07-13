package API;

import Utils.PayloadUtil;
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

public class PostRequestIntro {
    @Test
    public void createPet() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");
        HttpPost httpPost=new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type","application/json");
        httpPost.setHeader("Accept","application/json");
        HttpEntity entity=new StringEntity(PayloadUtil.getPetPayLoad(1356,"Rax","waiting"));
        httpPost.setEntity(entity);
        HttpResponse response=httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
    }
}