package io.pivotal.emcforum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by derrickwong on 21/9/2016.
 */
@RestResource
public interface UserAnswerRepository extends JpaRepository<UserAnswer, String> {

    List<UserAnswer> findAllByQ1AndQ2AndQ3AndQ4AndQ5(
            @Param("q1") String q1,
            @Param("q2") String q2,
            @Param("q3") String q3,
            @Param("q4") String q4,
            @Param("q5") String q5
            );

    List<UserAnswer> findAllByLucky(@Param("lucky") boolean lucky);

    Long countByLucky(@Param("lucky") boolean lucky);

    Long countByQ1AndQ2AndQ3AndQ4AndQ5(
            @Param("q1") String q1,
            @Param("q2") String q2,
            @Param("q3") String q3,
            @Param("q4") String q4,
            @Param("q5") String q5
            );

    @Query(value = "select new io.pivotal.emcforum.AnswerCount(u.q1, count(u.q1)) from UserAnswer u group by u.q1")
    List<AnswerCount> countAllByQ1GroupByQ1();
    @Query(value = "select new io.pivotal.emcforum.AnswerCount(u.q2, count(u.q2)) from UserAnswer u group by u.q2")
    List<AnswerCount> countAllByQ1GroupByQ2();
    @Query(value = "select new io.pivotal.emcforum.AnswerCount(u.q3, count(u.q3)) from UserAnswer u group by u.q3")
    List<AnswerCount> countAllByQ1GroupByQ3();
    @Query(value = "select new io.pivotal.emcforum.AnswerCount(u.q4, count(u.q4)) from UserAnswer u group by u.q4")
    List<AnswerCount> countAllByQ1GroupByQ4();
    @Query(value = "select new io.pivotal.emcforum.AnswerCount(u.q5, count(u.q5)) from UserAnswer u group by u.q5")
    List<AnswerCount> countAllByQ1GroupByQ5();
//    @Query(value = "select new io.pivotal.emcforum.AnswerCount(u.q6, count(u.q6)) from UserAnswer u group by u.q6")
//    List<AnswerCount> countAllByQ1GroupByQ6();
//    @Query(value = "select new io.pivotal.emcforum.AnswerCount(u.q7, count(u.q7)) from UserAnswer u group by u.q7")
//    List<AnswerCount> countAllByQ1GroupByQ7();


}

