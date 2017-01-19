package io.pivotal.emcforum;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by derrickwong on 28/9/2016.
 */
@Data
@AllArgsConstructor
public class AnswerCount{
    String answer;
    Long count;
}
