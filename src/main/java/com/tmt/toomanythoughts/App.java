package com.tmt.toomanythoughts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.tmt.toomanythoughts.config.ControllerInterceptorConfig;

@SpringBootApplication
@Import({ ControllerInterceptorConfig.class, })
public class App {

	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);
	}
}
