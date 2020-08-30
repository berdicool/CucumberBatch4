package API.Pojo;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter @Setter
public class CatFactPojo {
    private String _id;
    private String text;
    private String type;
    private Map<String, Object> user;
    private int upvotes;
    private String userUpvoted;
}
