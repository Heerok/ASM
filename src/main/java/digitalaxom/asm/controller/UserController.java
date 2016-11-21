package digitalaxom.asm.controller;

import digitalaxom.asm.config.UserAccountService;
import digitalaxom.asm.view.LoginResult;
import digitalaxom.asm.view.UserLoginDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Heerok on 10-11-2016.
 */
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/userlogin",method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT})
    public LoginResult login(@RequestBody UserLoginDTO login){
        return new LoginResult(UUID.randomUUID().toString());
    }
}
