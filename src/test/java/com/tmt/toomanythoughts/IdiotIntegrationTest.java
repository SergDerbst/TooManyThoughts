package com.tmt.toomanythoughts;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DatabaseSetup("classpath:datasets/idiotSetup.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:datasets/idiotSetup.xml" })
@DirtiesContext
public class IdiotIntegrationTest {

	@Value("${local.server.port}")
	int	port;

	@Test
	public void thatAllIdiotsAreAtTheirPlace() {

		when().get("/idiots/70")
					.then()
					.statusCode(HttpStatus.OK.value())
					.body("profession", is("floor lamp"));
		when().get("/idiots/75")
					.then()
					.statusCode(HttpStatus.OK.value())
					.body("profession", is("doorstop"));
		when().get("/idiots/80")
					.then()
					.statusCode(HttpStatus.OK.value())
					.body("profession", is("revolving door guide"));
		when().get("/idiots/85")
					.then()
					.statusCode(HttpStatus.OK.value())
					.body("profession", is("president"));

	}
}