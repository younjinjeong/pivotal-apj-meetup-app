package io.pivotal.emcforum.service;

import io.pivotal.emcforum.model.CorrectAnswer;
import io.pivotal.emcforum.model.UserAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by derrickwong on 21/9/2016.
 */
@Service
@EnableBinding(Source.class)
public class CheckingService {


    @Autowired
    private Source channel;
    @Autowired
    RepositoryFeignClient repositoryFeignClient;
    @Autowired
    RepositoryFallback repositoryFallback;
    @Autowired
    CorrectAnswer correctAnswer;

    @Value("${DrawSize.size}")
    Integer drawingSize;

    private static Logger logger = LoggerFactory.getLogger(CheckingService.class);

    private Map<String, String> getCorrectAnswersMap(){
        Map<String, String> correctAnswerMap = new HashMap<>();
        correctAnswerMap.put("q1", correctAnswer.getQ1());
        correctAnswerMap.put("q2", correctAnswer.getQ2());
        correctAnswerMap.put("q3", correctAnswer.getQ3());
        correctAnswerMap.put("q4", correctAnswer.getQ4());
        correctAnswerMap.put("q5", correctAnswer.getQ5());
//        correctAnswerMap.put("q6", correctAnswer.getQ6());
//        correctAnswerMap.put("q7", correctAnswer.getQ7());
        return correctAnswerMap;
    }

    public Long numberOfAllCorrect(){
        return repositoryFeignClient.countByAnswers(getCorrectAnswersMap());
    }

    public List<String> getAllEmailOfAllCorrect(){
        return repositoryFeignClient.findAllByAnswers(getCorrectAnswersMap())
                .stream()
                .map(ua -> ua.getEmail())
                .collect(Collectors.toList());
    }

    public List<String> draw(){
        // set all lucky to false first
        logger.info("Resetting lucky to false");
        repositoryFeignClient.resetAllLucky();


        // find all correct
        logger.info("Finding all with correct answer");
        List<UserAnswer> ua = repositoryFeignClient.findAllByAnswers(getCorrectAnswersMap());
        if(ua == null || ua.size() == 0 ){
            logger.info("No one with correct answer");
            return null;
        }


        // draw
        logger.info("Start drawing, total size is " + ua.size());
        if(ua.size() <= drawingSize){
            logger.info("People with correct answer smaller or equal to " + drawingSize);
            ua.forEach(a -> repositoryFeignClient.setLucky(a.getEmail()));
            return ua.stream().map(u -> u.getEmail()).collect(Collectors.toList());
        }

        List<UserAnswer> lucky = new ArrayList<>();
        while(true) {

            UserAnswer t = ua.get(new Random().nextInt(ua.size()));
            if(!lucky.contains(t)) {
                lucky.add(t);
                t.setLucky(true);
                logger.info("Lucky: " + t.getEmail());
                repositoryFeignClient.setLucky(t.getEmail());
            }
            if(lucky.size()==drawingSize) break;
        }
        return lucky.stream().map(u -> u.getEmail()).collect(Collectors.toList());
    }

    public void emailAllCorrect(){
        getAllEmailOfAllCorrect().forEach(email -> {
            logger.info("Email: " + email);
            sendMessage(email,"application/text");
        });

    }

    public void emailLucky(){
        repositoryFeignClient.findAllLucky()
                .forEach(ua -> {
                    logger.info("Email: " + ua);
                    sendMessage(ua,"application/text");
                } );
    }

    private void sendMessage(Object body, Object contentType) {

        channel.output().send(MessageBuilder.createMessage(body,
                new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, contentType))));
    }

}
