package io.pivotal.emcforum;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by derrickwong on 24/9/2016.
 */
@RestController
public class UserAnswerController {

    @Autowired
    private UserAnswerRepository uar;

    private static Logger logger = LoggerFactory.getLogger(UserAnswerController.class);


    @RequestMapping(value = "/findAllByAnswers", method = RequestMethod.POST, consumes = {"application/json"})
    List<UserAnswer> findAllByAnswers(@RequestBody Map<String, String> answers){
        logger.info("request for findAllByAnswers");
        return uar.findAllByQ1AndQ2AndQ3AndQ4AndQ5(
                answers.get("q1"),
                answers.get("q2"),
                answers.get("q3"),
                answers.get("q4"),
                answers.get("q5"));
    }

    @RequestMapping(value = "/countByAnswers", method = RequestMethod.POST, consumes = {"application/json"})
    Long countByAnswers(@RequestBody Map<String, String> answers){
        logger.info("request for countByAnswers");
        return uar.countByQ1AndQ2AndQ3AndQ4AndQ5(
                answers.get("q1"),
                answers.get("q2"),
                answers.get("q3"),
                answers.get("q4"),
                answers.get("q5"));
    }

    @RequestMapping("/isDrew")
    boolean isDrew(){
        return uar.countByLucky(true) > 0;
    }

    @RequestMapping("/findAllLucky")
    List<String> findAllLucky(){
        logger.info("request for findAllLucky");
        return uar.findAllByLucky(true)
                .stream()
                .map(ua -> ua.getEmail())
                .collect(Collectors.toList());
    }

    @RequestMapping("/resetAllLucky")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    void resetAllLucky(){
        logger.info("request for resetAllLucky");
        List<UserAnswer> ua = uar.findAllByLucky(true);
        ua.forEach(a -> a.setLucky(false));
        uar.save(ua);
    }

    @RequestMapping(value = "/setLucky", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    void setLucky(@RequestBody String email){
        logger.info("request for setLucky for " + email);
        UserAnswer ua = uar.getOne(email);
        ua.setLucky(true);
        uar.save(ua);
    }

    @RequestMapping("/getStats/{question}")
    List<AnswerCount> getStats(@PathVariable String question){
        if(question.equals("q1"))
            return uar.countAllByQ1GroupByQ1();
        else if(question.equals("q2"))
            return uar.countAllByQ1GroupByQ2();
        else if(question.equals("q3"))
            return uar.countAllByQ1GroupByQ3();
        else if(question.equals("q4"))
            return uar.countAllByQ1GroupByQ4();
        else if(question.equals("q5"))
            return uar.countAllByQ1GroupByQ5();
//        else if(question.equals("q6"))
//            return uar.countAllByQ1GroupByQ6();
//        else if(question.equals("q7"))
//            return uar.countAllByQ1GroupByQ7();
        return null;
    }

    @RequestMapping(value = "/reset",method = RequestMethod.POST)
    void reset(){
        uar.deleteAll();
    }

}
