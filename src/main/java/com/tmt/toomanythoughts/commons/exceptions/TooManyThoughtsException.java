package com.tmt.toomanythoughts.commons.exceptions;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.exception.ExceptionContext;

@SuppressWarnings("serial")
public abstract class TooManyThoughtsException extends ContextedRuntimeException {

	protected TooManyThoughtsException() {
		super();
	}

	protected TooManyThoughtsException(final String message) {
		super(message);
	}

	protected TooManyThoughtsException(final Throwable cause) {
		super(cause);
	}

	protected TooManyThoughtsException(	final String message,
																		final Throwable cause) {
		super(message,
		      cause);
	}

	protected TooManyThoughtsException(	final String message,
																		final Throwable cause,
	                                 	final ExceptionContext context) {
		super(message);
	}
}
