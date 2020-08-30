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

public class GotCharacters {
    @Test
    public void getHouses() throws URISyntaxException, IOException {
        HttpClient httpClient= HttpClientBuilder.create().build();
        URIBuilder uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.got.show").setPath("api/map/characters");
        HttpGet httpGet=new HttpGet(uriBuilder.build());
        HttpResponse httpResponse=httpClient.execute(httpGet);
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> parsedResponse=objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<>() {
                });
        List<Map<String,Object>>dataValues= (List<Map<String, Object>>) parsedResponse.get("data");
        Map<String,Object> firstData=dataValues.get(0);
        System.out.println(firstData.get("house"));
        for(int i=0;i<dataValues.size();i++){
            System.out.println(dataValues.get(i).get("house"));
        }
    }
}
