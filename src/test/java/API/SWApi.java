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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SWApi {
    @Test
    public void test1() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("swapi.dev").setPath("api/planets");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");
        HttpResponse httpResponse=httpClient.execute(httpGet);
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> parsedResponse = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String,Object>>() {});
        List<Map<String,Object>> results= (List<Map<String, Object>>) parsedResponse.get("results");
        Map<String,String> planetsPopulationMap=new HashMap<>();
        for (int i=0;i<results.size();i++){
            String planet=results.get(i).get("name").toString();
            String population=null;
            try {
                population = results.get(i).get("population").toString();
            }catch (NumberFormatException e){
                System.out.println("Population is not numeric "+e);
            }
            planetsPopulationMap.put(planet,population);
        }
        System.out.println(planetsPopulationMap);
    }
}
