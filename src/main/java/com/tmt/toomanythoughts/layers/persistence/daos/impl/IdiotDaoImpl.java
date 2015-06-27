package com.tmt.toomanythoughts.layers.persistence.daos.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmt.toomanythoughts.layers.persistence.BaseCrudDaoService;
import com.tmt.toomanythoughts.layers.persistence.daos.IdiotDao;
import com.tmt.toomanythoughts.layers.persistence.entities.Idiot;
import com.tmt.toomanythoughts.layers.persistence.repositories.IdiotRepository;

@Service
@Transactional
public class IdiotDaoImpl extends BaseCrudDaoService<IdiotRepository, Idiot> implements IdiotDao {

	@Autowired
	IdiotRepository	repository;

	@Override
	public IdiotRepository getRepository() {
		return this.repository;
	}

	@Override
	public Idiot findByIq(final Integer iq) {
		return this.repository.findByIq(iq);
	}
}
