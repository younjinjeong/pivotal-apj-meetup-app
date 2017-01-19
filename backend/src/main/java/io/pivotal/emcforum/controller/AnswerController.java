package io.pivotal.emcforum.controller;

import io.pivotal.emcforum.model.DrawSize;
import io.pivotal.emcforum.service.CheckingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by derrickwong on 21/9/2016.
 */
@RestController
public class AnswerController {

    @Autowired
    CheckingService cs;
    @Autowired
    DrawSize drawSize;

    private static Logger logger = LoggerFactory.getLogger(AnswerController.class);


    @RequestMapping(value = "/draw", method = RequestMethod.POST)
    public List<String> draw(){
        logger.info("Start drawing");
        List<String> ua = cs.draw();
        emailThem();
        return ua;
    }

    @RequestMapping("/emailOfAllCorrect")
    public List<String> getEmailOfAllCorrect(){
        return cs.getAllEmailOfAllCorrect();
    }

    @RequestMapping("/numberOfAward")
    public Integer getNumberOfAward(){
        return drawSize.getSize();
    }

    @RequestMapping("/numberOfAllCorrect")
    public Long getNumberOfAllCorrect(){
        return cs.numberOfAllCorrect();
    }

    @RequestMapping(value = "/emailAllCorrect", method = RequestMethod.POST)
    public void emailAllCorrect(){
        logger.info("Start notifying");
        cs.emailAllCorrect();
    }


    @RequestMapping(value = "/emailLucky", method = RequestMethod.POST)
    public void emailThem(){
        logger.info("Start notifying");
        cs.emailLucky();
    }



}