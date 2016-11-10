package digitalaxom.asm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import digitalaxom.asm.view.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
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

}