package co.da.nw;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
@Configuration
@ComponentScan(basePackages = {"co.da.nw.controller", "co.da.nw.service"})
@EnableTransactionManagement
@EnableWebMvc
@WebAppConfiguration
public class WebContext extends WebMvcConfigurerAdapter {
    
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

        return viewResolver;
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry aRegistry)
    {
        aRegistry.addViewController("/").setViewName("default");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry aRegistry)
    {
        ResourceHandlerRegistration _res = 
            aRegistry.addResourceHandler("/static/**");
        _res.addResourceLocations(
            "/static/");
    }
    
    public static void main(String[] args) {
        AbstractApplicationContext appContext = new AnnotationConfigApplicationContext(Application.class);
        AbstractApplicationContext webContext = new AnnotationConfigApplicationContext(WebContext.class);
    }

}
*/
