package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class CatFacts {
    @Test
    public void countCatFacts() throws IOException, URISyntaxException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("cat-fact.herokuapp.com");
        uriBuilder.setPath("facts");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        HttpResponse httpResponse=httpClient.execute(httpGet);
        //Deserialization
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,List> parsedResponse=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, List>>() {
                });
        parsedResponse.get("all");
        System.out.println(parsedResponse.get("all").size());
    }
    @Test
    public void countNonCatFacts() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("cat-fact.herokuapp.com");
        uriBuilder.setPath("facts");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        HttpResponse httpResponse=httpClient.execute(httpGet);
        //Deserialization
        ObjectMapper objectMapper=new ObjectMapper();
//        Map<String,List> parsedResponse=objectMapper.readValue(httpResponse.getEntity().getContent(),
//                new TypeReference<Map<String, List>>() {
//                });
        Map<String,List<Map<String,Object>>> parsedResponse1=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, List<Map<String, Object>>>>() {
                });
        System.out.println(parsedResponse1.get("all").get(0).get("text"));
        List<Map<String,Object>> mapList = parsedResponse1.get("all");
        int index=0;
        for (Map<String,Object> map:mapList){
            index++;
        }
        System.out.println(index);
    }
    @Test
    public void reqresUsers() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        HttpResponse httpResponse=httpClient.execute(httpGet);
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, Object> usersInfo=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        System.out.println(usersInfo.get("page"));
        System.out.println(usersInfo.get("per_page"));
        List<Map<String,Object>> users = (List<Map<String, Object>>) usersInfo.get("data");
        System.out.println(users);
        for (int i=0;i<users.size();i++){
            System.out.println(users.get(i).get("first_name"));
        }

    }
}
