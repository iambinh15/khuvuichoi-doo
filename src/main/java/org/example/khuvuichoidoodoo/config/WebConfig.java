// File: src/main/java/org/example/khuvuichoidoodoo/config/WebConfig.java

package org.example.khuvuichoidoodoo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/hoa-don-pdf/**")
                .addResourceLocations("file:exports/hoa-don/");
    }
}
