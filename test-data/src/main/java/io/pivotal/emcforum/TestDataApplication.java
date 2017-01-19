package io.pivotal.emcforum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestDataApplication implements CommandLineRunner{

	@Autowired
	UserAnswerRepository userAnswerRepository;

	@Override
	public void run(String... strings) throws Exception {
		List<UserAnswer> userAnswerList = new ArrayList<UserAnswer>();

		for(int i=0;i<1000;i++){
			userAnswerList.add(new UserAnswer("test_a_" + i + "@pivotaltest.io", "test_name_a_" + i, "a", "b", "c", "d", "a", "a", "b", false));
		}
		for(int i=0;i<300;i++){
			userAnswerList.add(new UserAnswer("test_b_" + i + "@pivotaltest.io", "test_name_b_" + i, "b", "a", "a", "a", "b", "c", "a", false));
		}
		for(int i=0;i<200;i++){
			userAnswerList.add(new UserAnswer("test_c_" + i + "@pivotaltest.io", "test_name_c_" + i, "c", "c", "b", "b", "c", "b", "c", false));
		}
		for(int i=0;i<100;i++){
			userAnswerList.add(new UserAnswer("test_d_" + i + "@pivotaltest.io", "test_name_d_" + i, "c", "a", "a", "c", "d", "b", "c", false));
		}
		for(int i=0;i<10;i++){
			userAnswerList.add(new UserAnswer("test_e_" + i + "@pivotaltest.io", "test_name_e_" + i, "a", "b", "c", "b", "c", "a", "a", false));
		}
		userAnswerRepository.save(userAnswerList);
	}

	public static void main(String[] args) {
		SpringApplication.run(TestDataApplication.class, args);
	}
}


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
class UserAnswer {
	@Id
	private String email;
	private String name;
	private String q1,q2,q3,q4,q5,q6,q7;
	private boolean lucky=false;
}

interface UserAnswerRepository extends JpaRepository<UserAnswer, String>{

}
