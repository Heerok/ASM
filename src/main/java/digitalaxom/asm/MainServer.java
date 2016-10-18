package digitalaxom.asm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Heerok on 24-09-2016.
 */
@SpringBootApplication
public class MainServer extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(MainServer.class);

    public static void main(String[] args) throws Exception{
        SpringApplication.run(MainServer.class,args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainServer.class);
    }

}
