package co.da.nw.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.da.nw.service.CategoryService;

@Configuration
public class TestContext {
    
    @Bean
    public CategoryService categoryService() {
        return Mockito.mock(CategoryService.class);
    }

}
