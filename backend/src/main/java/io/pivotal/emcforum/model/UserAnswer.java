package io.pivotal.emcforum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by derrickwong on 21/9/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {
    private String email;
    private String name;
    private String q1,q2,q3,q4,q5;
//            ,q6,q7;
    private boolean lucky=false;
}