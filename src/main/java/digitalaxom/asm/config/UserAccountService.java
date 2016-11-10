package digitalaxom.asm.config;


import digitalaxom.asm.Repository.UserRepository;
import digitalaxom.asm.db.UserAccount;
import digitalaxom.asm.view.CurrentUser;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserAccountService implements UserDetailsService {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserAccountService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userRepository.findOneByUsername(username);
        if (user == null) {
            logger.warn("user not found {}", username);
            throw new UsernameNotFoundException(String.format("User %s was not found", username));
        }

        /*List<RoleWithUser> userRoles = userRoleService.findByOrgAndUser(user.getOrg(), user);
        Set<String> strroles = new HashSet<>();
        int i = 0;
        for (RoleWithUser role : userRoles) {
            logger.debug("user {}, role {}", username, role.getRole().getName());
            strroles.add("ROLE_"+role.getRole().getName());
        }*/
        CurrentUser currentUser = new CurrentUser(user,null);
        return currentUser;
    }

    public Date getAuthExpiration(int ttlhours) {
        return new DateTime().plusHours(ttlhours).toDate();
    }

}