package com.userimran.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "product-service")
@Getter
@Setter
public class AppProps {
    private Map<String, String> messages = new HashMap<>();
}
