package digitalaxom.asm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Heerok on 29-11-2016.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public CharacterEncodingFilter characterEncodingFilter(){
        return new CharacterEncodingFilter();
    }

}
