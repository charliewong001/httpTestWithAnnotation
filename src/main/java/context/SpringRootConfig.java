package context;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Controller;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "service", excludeFilters = {
        @Filter(Controller.class), @Filter(Configuration.class) })

public class SpringRootConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ResourcePropertySource hmacUtilsProperties() throws IOException {
        return new ResourcePropertySource("classpath:/hmac-utils.properties");
    }

    @Bean
    public ResourcePropertySource peripheralUrisProperties()
            throws IOException {
        return new ResourcePropertySource("classpath:/system.properties");
    }
}
