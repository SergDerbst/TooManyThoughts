package com.tmt.toomanythoughts.layers.controller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.toomanythoughts.layers.controller.model.IdiotResponse;
import com.tmt.toomanythoughts.layers.persistence.daos.IdiotDao;

@RestController
public class IdiotController extends BaseController {

	@Autowired
	IdiotDao	idiotDao;

	@RequestMapping("/idiots/{iq}")
	public IdiotResponse idiots(@PathVariable final Integer iq) {
		return new IdiotResponse(this.idiotDao.findByIq(iq));
	}
}
