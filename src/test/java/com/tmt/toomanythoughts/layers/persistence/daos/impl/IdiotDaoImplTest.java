package com.tmt.toomanythoughts.layers.persistence.daos.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.tmt.toomanythoughts.App;
import com.tmt.toomanythoughts.layers.persistence.daos.IdiotDao;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@DatabaseSetup("classpath:datasets/idiotSetup.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:datasets/idiotSetup.xml" })
@DirtiesContext
public class IdiotDaoImplTest {

	@Autowired
	IdiotDao	idiotDao;

	@Test
	public void thatThereAreFourIdiots() {
		assertThat(this.idiotDao.count(), is(4L));
	}
}
