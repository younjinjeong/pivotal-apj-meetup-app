package io.pivotal.emcforum;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by derrickwong on 23/9/2016.
 */
@RefreshScope
@Component
@Data
@ConfigurationProperties(prefix = "email")
public class Email {
    private boolean reallySend;
    private String subject;
    private String body;
}
