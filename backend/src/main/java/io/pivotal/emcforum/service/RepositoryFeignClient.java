package io.pivotal.emcforum.service;

import io.pivotal.emcforum.model.UserAnswer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

/**
 * Created by derrickwong on 24/9/2016.
 */
@FeignClient(name = "emc-forum-repository", fallback = RepositoryFallback.class)
public interface RepositoryFeignClient {

    @RequestMapping(value = "/findAllByAnswers", method = RequestMethod.POST)
    List<UserAnswer> findAllByAnswers(@RequestBody Map<String, String> answers);

    @RequestMapping(value = "/countByAnswers", method = RequestMethod.POST)
    Long countByAnswers(@RequestBody Map<String, String> answers);

    @RequestMapping(value = "/findAllLucky", method = RequestMethod.GET)
    List<String> findAllLucky();

    @RequestMapping("/resetAllLucky")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    void resetAllLucky();

    @RequestMapping(value = "/setLucky", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    void setLucky(@RequestBody String email );

}
