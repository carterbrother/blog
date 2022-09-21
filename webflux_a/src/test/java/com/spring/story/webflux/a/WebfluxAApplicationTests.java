package com.spring.story.webflux.a;

import com.spring.story.webflux.a.dto.Greeting;
import com.spring.story.webflux.a.test.GreetingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RequiredArgsConstructor
@Slf4j
public class WebfluxAApplicationTests  implements ApplicationContextAware {

	@Autowired
	private  GreetingClient greetingClient;

	@Autowired
	private  WebTestClient webTestClient;


	@Test
	void testHello1() {

		String message = applicationContext.getBean(GreetingClient.class).getMessage().block();
		log.info("hello return: {}",message);

	}

	@Test
	void testHello() {

		String message = greetingClient.getMessage().block();
		log.info("hello return: {}",message);

	}


	@Test
	void testHello2() {

		webTestClient.get().uri("/hello").accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk()
				.expectBody(Greeting.class).value(val->{
					String message = val.getMessage();
					log.info("hello return2: ",message);
					assertThat(message).contains("hello");
				});

	}

	ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
}
