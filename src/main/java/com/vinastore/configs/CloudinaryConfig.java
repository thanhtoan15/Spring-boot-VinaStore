package com.vinastore.configs;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME ="dsc5leown";

    private final String API_KEY ="839961147671239";

    private final String API_SECRET ="IRUGW84ctd3B_U0EbhwjMl9Yp";

    @Bean
    public Cloudinary getCloudinary(){
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);
        return new Cloudinary(config);
    }
}
