package API.Pojo;

import java.util.Map;

public class CookieAuthPojo {
    private Map<String,Object> session;
    private Map<String,Object> loginInfo;

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map<String, Object> getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(Map<String, Object> loginInfo) {
        this.loginInfo = loginInfo;
    }
}
