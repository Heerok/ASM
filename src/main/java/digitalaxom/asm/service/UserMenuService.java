package digitalaxom.asm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import digitalaxom.asm.db.UserAccount;
import digitalaxom.asm.view.CurrentUser;
import digitalaxom.asm.view.Menu;
import digitalaxom.asm.view.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class UserMenuService {

    private static final Logger logger = LoggerFactory.getLogger(UserMenuService.class);

    @Autowired
    private ResourceLoader resourceLoader;

    private Map<String, MenuItem> submenuMap = new HashMap<>();
    private Map<String, MenuItem> menuMap = new HashMap<>();
    private Map<String, List<String>> mapping = new HashMap<>();

    private ObjectMapper jsonMapper = new ObjectMapper();

    @PostConstruct
    public void init() {

        try {
            Resource[] resources  = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath*:/menu/*.json");

            for (Resource resource : resources) {
                logger.info("Loaded menu from {}", resource.getFilename());
                MenuItem item = jsonMapper.readValue(resource.getFile(), MenuItem.class);
                menuMap.put(item.getLabel(), item);

                for (MenuItem submenu : item.getSubmenu()) {
                    submenu.setParent(item.getLabel());
                    submenuMap.put(submenu.getLabel(), submenu);
                }
            }

        } catch (IOException e) {
            logger.error("Cannot load menu from disk!", e);
        }
    }

    // TODO - Prob: If we updating user privileges, than changes will not reflect because submenus are coming from cache.
    // TODO - Sol: We need to clear/ refresh cache when updating user privileges.
    public Menu getCurrentUserMenu() {
        UserAccount user = getUser();
/*        Menu menu = menuCache.getIfPresent(user.getEmail());
        if (menu == null) {
            menu = buildForUser(user);
            menuCache.put(user.getEmail(), menu);
        }
        return menu;*/
        return buildForUser(user);
    }

    public Menu buildForUser(UserAccount user) {
        Menu navigation = new Menu();
        for (MenuItem submenu : submenuMap.values()) {
            //if (user.hasPrivilege(submenu.getPrivilege())) {
                MenuItem parent = menuMap.get(submenu.getParent());                                                     // parent stands for menu Label/menu name.
                navigation.add(parent, submenu);
            //}
        }
        return navigation;
    }

    public UserAccount getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        if (!(authentication.getPrincipal() instanceof String)) {
            return ((CurrentUser) authentication.getPrincipal()).getUser();
        }
        return null;
    }

}