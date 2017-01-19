package io.pivotal.emcforum.service;

import io.pivotal.emcforum.model.UserAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by derrickwong on 24/9/2016.
 */
@Service
public class RepositoryFallback implements RepositoryFeignClient {

    private static Logger logger = LoggerFactory.getLogger(RepositoryFallback.class);


    public List<UserAnswer> findAllByAnswers(Map<String, String> answers){
        logger.info("fallback: findAllByAnswers");
        return null;
    }


    public Long countByAnswers( Map<String, String> answers){
        logger.info("fallback: countByAnswers");
        return 0L;
    }


    public List<String> findAllLucky(){
        logger.info("fallback: findAllLucky");
        return null;
    }

    public void resetAllLucky(){
        logger.info("fallback: resetAllLucky");
    }

    public void setLucky(String email){
        logger.info("fallback: setLucky");
    }


}
