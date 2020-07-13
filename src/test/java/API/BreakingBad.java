package API;

import API.Pojo.BBCharPojo;
import API.Pojo.BreakingBadPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakingBad {
    private static final String ALIVE="alive";
    private static final String DECEASED="deceased";
    private static final String PRESUMED_DEAD="presumed dead";
    private static final String UNKNOWN_STATUS="unknown";
    @Test
    public void getCharacters() throws URISyntaxException, IOException {
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder builder=new URIBuilder();
        builder.setScheme("https");
        builder.setHost("breakingbadapi.com");
        builder.setPath("api/characters");
        HttpGet get=new HttpGet(builder.build());
        get.setHeader("Accept","application/json");
        HttpResponse response=client.execute(get);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
        List<BreakingBadPojo> breakingBadPojo=objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<List<BreakingBadPojo>>() {
                });
        Map<String,List<String>> charByCategory=new HashMap<>();
        List<String> alive=new ArrayList<>();
        List<String> deceased=new ArrayList<>();
        List<String> presumedDead=new ArrayList<>();
        List<String> unknownStatus=new ArrayList<>();
        for(int i=0;i<breakingBadPojo.size();i++){
            if(breakingBadPojo.get(i).getStatus().equalsIgnoreCase(ALIVE)){
                alive.add(breakingBadPojo.get(i).getName());
            }else if (breakingBadPojo.get(i).getStatus().equalsIgnoreCase(DECEASED)){
                deceased.add(breakingBadPojo.get(i).getName());
            }else if(breakingBadPojo.get(i).getStatus().equalsIgnoreCase(PRESUMED_DEAD)){
                presumedDead.add(breakingBadPojo.get(i).getName());
            }else{
                unknownStatus.add(breakingBadPojo.get(i).getName());
            }
        }
        charByCategory.put(ALIVE,alive);
        charByCategory.put(DECEASED,deceased);
        charByCategory.put(PRESUMED_DEAD,presumedDead);
        charByCategory.put(UNKNOWN_STATUS,unknownStatus);
        System.out.println("Alive Characters"+charByCategory.get(ALIVE));
        System.out.println("Deceased Characters"+charByCategory.get(DECEASED));
        System.out.println("Presumed dead Characters"+charByCategory.get(PRESUMED_DEAD));
        System.out.println("Unknown status"+charByCategory.get(UNKNOWN_STATUS));
    }
    @Test
    public void getCharById() throws IOException, URISyntaxException {
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder builder=new URIBuilder();
        builder.setScheme("https");
        builder.setHost("breakingbadapi.com");
        builder.setPath("api/characters/12");
        HttpGet get=new HttpGet(builder.build());
        get.setHeader("Accept","application/json");
        HttpResponse response=client.execute(get);
        ObjectMapper objectMapper=new ObjectMapper();
        List<BBCharPojo> bbCharPojo=objectMapper.readValue(response.getEntity().getContent(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, BBCharPojo.class));
        System.out.println(String.format("%s is %s portrayed by %s, in %s",bbCharPojo.get(0).getName(),bbCharPojo.get(0).getStatus(),
                bbCharPojo.get(0).getPortrayed(),bbCharPojo.get(0).getCategory()));


    }
}
