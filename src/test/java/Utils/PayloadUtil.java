package Utils;

import API.Pojo.CookieAuthPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadUtil {
    public static String getPetPayLoad(int id, String name, String status){
        return "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+status+"\"\n" +
                "}";
    }
    public static String generateStringFromResource(String path) throws IOException {
        String petPayLoad=new String(Files.readAllBytes(Paths.get(path)));
        return petPayLoad;
    }
    public static String cookieAuthPayload(){
        return "{\n" +
                "    \"username\":\"berdicool\",\n" +
                "    \"password\":\"jKv4wZz7Zdwk4c3\"\n" +
                "}";
    }
    public static String getJiraIssuePayload(String summary,String description,String issueTypeName){
        return "{\n" +
                "    \"fields\":{\n" +
                "        \"project\":{\n" +
                "            \"key\":\"TEC\"\n" +
                "        },\n" +
                "        \"summary\":\""+summary+"\",\n" +
                "        \"description\": \""+description+"\",\n" +
                "        \"issuetype\":{\n" +
                "            \"name\":\""+issueTypeName+"\"\n" +
                "        }\n" +
                "    }\n" +
                "}  ";
    }
    public static String JSessionCookie() throws URISyntaxException, IOException {
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
        String cookieName=cookieAuthPojo.getSession().get("name").toString();
        String cookieValue=cookieAuthPojo.getSession().get("value").toString();
        return String.format("%s=%s",cookieName,cookieValue);
    }
}
