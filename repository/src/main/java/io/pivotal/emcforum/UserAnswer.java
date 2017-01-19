package io.pivotal.emcforum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by derrickwong on 21/9/2016.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {
    @Id
    private String email;
    private String name;
    private String q1,q2,q3,q4,q5;
    private boolean lucky=false;
}