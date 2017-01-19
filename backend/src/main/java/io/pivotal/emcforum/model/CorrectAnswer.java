package io.pivotal.emcforum.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by derrickwong on 21/9/2016.
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "answer")
@Data
public class CorrectAnswer {
    private String q1,q2,q3,q4,q5;
//            ,q6,q7;
}
