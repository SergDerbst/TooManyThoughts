package com.tmt.toomanythoughts.commons.exceptions.persistence;

import org.apache.commons.lang3.exception.ExceptionContext;

import com.tmt.toomanythoughts.commons.exceptions.TooManyThoughtsException;

@SuppressWarnings("serial")
public abstract class PersistenceException extends TooManyThoughtsException {

	protected PersistenceException() {
		super();
	}

	protected PersistenceException(final String message) {
		super(message);
	}

	protected PersistenceException(final Throwable cause) {
		super(cause);
	}

	protected PersistenceException(	final String message,
																	final Throwable cause) {
		super(message,
					cause);
	}

	protected PersistenceException(	final String message,
																	final Throwable cause,
																	final ExceptionContext context) {
		super(message);
	}
}
