package digitalaxom.asm.view;

/**
 * Created by Heerok on 10-11-2016.
 */
public class LoginResult {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResult(String token) {
        this.token = token;
    }
}
