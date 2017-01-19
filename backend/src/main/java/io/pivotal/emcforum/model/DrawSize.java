package io.pivotal.emcforum.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by derrickwong on 23/9/2016.
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "DrawSize")
@Data
public class DrawSize {
    private Integer size;
}
