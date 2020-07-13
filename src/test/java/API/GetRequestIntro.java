package API;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class GetRequestIntro {

    @Test
    public void getRequest() throws URISyntaxException, IOException {
        /*
        Steps to execute a API call/request:
        Open/ launch client (open POSTMAN)
        Specify the method type (GET/POST/PUT/DELETE)
        Specify the URL/URI( Uniform Resource locator)/(Uniform Resource Identifier) URL == URI
        Execute (click on send button)
        Check the response status code
        Check the response body
         */
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder builder=new URIBuilder();
        builder.setScheme("https");
        builder.setHost("petstore.swagger.io");
        builder.setPath("v2/pet/135666");
        HttpGet get=new HttpGet(builder.build());
        get.setHeader("Accept","application/json");
        HttpResponse response=client.execute(get);
        Assert.assertEquals(200,response.getStatusLine().getStatusCode());
        Assert.assertEquals("application/json",response.getEntity().getContentType().getValue());
    }
    @Test
    public void getRequest1() throws URISyntaxException, IOException {
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder builder=new URIBuilder();
        builder.setScheme("https");
        builder.setHost("petstore.swagger.io");
        builder.setPath("v2/pet/findByStatus");
        builder.setCustomQuery("status=sold");
        HttpGet get=new HttpGet(builder.build());
        get.setHeader("Accept","application/json");
        HttpResponse response=client.execute(get);
        Assert.assertEquals(200,response.getStatusLine().getStatusCode());
        Assert.assertEquals("application/json",response.getEntity().getContentType().getValue());
    }
}
