package digitalaxom.asm;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Heerok on 29-11-2016.
 */
@Component
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---------------------");
        System.out.println("---------------------");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("---------------------");
        servletRequest.getLocalAddr();
    }

    @Override
    public void destroy() {

    }
}
