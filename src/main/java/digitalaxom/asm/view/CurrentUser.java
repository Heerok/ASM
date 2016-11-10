package digitalaxom.asm.view;


import digitalaxom.asm.db.UserAccount;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    private UserAccount user;
    private String token;

    public CurrentUser(UserAccount user, String[] roles) {
        super(user.getUsername(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(roles));
        this.user = user;
    }

    public UserAccount getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


    @Override
    public String toString() {
        return user.toString();
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

}