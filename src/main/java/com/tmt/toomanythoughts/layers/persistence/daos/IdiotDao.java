package com.tmt.toomanythoughts.layers.persistence.daos;

import com.tmt.toomanythoughts.layers.persistence.CrudDao;
import com.tmt.toomanythoughts.layers.persistence.entities.Idiot;

public interface IdiotDao extends CrudDao<Idiot, Integer> {

	Idiot findByIq(Integer iq);

}
