package net.kickit.hospitalinfoservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
@ConfigurationProperties("datasource")
public class DataSourceProperties {
    @NotBlank
    private String database;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
