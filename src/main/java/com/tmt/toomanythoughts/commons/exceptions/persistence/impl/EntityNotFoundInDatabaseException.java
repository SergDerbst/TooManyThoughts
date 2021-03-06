package com.tmt.toomanythoughts.commons.exceptions.persistence.impl;

import com.tmt.toomanythoughts.commons.exceptions.persistence.PersistenceException;

@SuppressWarnings("serial")
public class EntityNotFoundInDatabaseException extends PersistenceException {

	public static EntityNotFoundInDatabaseException prepare(final Object entity) {
		return (EntityNotFoundInDatabaseException) new EntityNotFoundInDatabaseException().addContextValue("entity", entity);
	}
}