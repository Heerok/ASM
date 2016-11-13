package digitalaxom.asm.controller;

import digitalaxom.asm.service.UserMenuService;
import digitalaxom.asm.view.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Heerok on 10-11-2016.
 */
@RestController
public class UserMenuController {

    @Autowired
    private UserMenuService userMenuService;

    @RequestMapping(value = "/api/v2/menu")
    public Menu getMenu(Authentication auth) {
        return userMenuService.getCurrentUserMenu();
    }

}
