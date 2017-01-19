package io.pivotal.emcforum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@EnableDiscoveryClient
@EnableBinding(Sink.class)
@SpringBootApplication
public class EmailServiceApplication {

	@Bean
	Sampler alwaysSampler(){
		return new AlwaysSampler();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	private static Logger logger = LoggerFactory.getLogger(EmailServiceApplication.class);

	@Autowired
	JavaMailSender mailSender;

	@Autowired Email email;

//	@Autowired EmailRepository emailRepository;

	@ServiceActivator(inputChannel= Sink.INPUT)
	public void sendEmail(String emailAddress){
		logger.info("Email: " + emailAddress );
		SimpleMailMessage m = new SimpleMailMessage();
		m.setFrom("emcforum2016hk@gmail.com");
		m.setTo(emailAddress);
		m.setSentDate(new Date());
		m.setSubject(email.getSubject());
		m.setText(email.getBody());
		if(email.isReallySend()) {
			logger.info("Sending to " + emailAddress);
			mailSender.send(m);
		}

//		EmailLog log = new EmailLog();
//		log.setEmail(emailAddress);
//		log.setSentDatetime(new Date());
//		emailRepository.save(log);
	}


}

//@Data @Entity
//class EmailLog{
//	@Id @GeneratedValue
//	Long id;
//	String email;
//	Date sentDatetime;
//}
//interface EmailRepository extends JpaRepository<EmailLog, Long>{
//
//}
