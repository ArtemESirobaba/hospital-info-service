package net.kickit.hospitalinfoservice.config;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConf {

    @Bean
    public ObjectMapper objectMapper(){
        return JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .build();
    }
}
