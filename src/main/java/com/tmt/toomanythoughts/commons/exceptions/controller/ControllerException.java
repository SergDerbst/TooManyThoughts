package com.tmt.toomanythoughts.commons.exceptions.controller;

import org.apache.commons.lang3.exception.ExceptionContext;

import com.tmt.toomanythoughts.commons.exceptions.TooManyThoughtsException;

@SuppressWarnings("serial")
public abstract class ControllerException extends TooManyThoughtsException {

	protected ControllerException() {
		super();
	}

	protected ControllerException(final String message) {
		super(message);
	}

	protected ControllerException(final Throwable cause) {
		super(cause);
	}

	protected ControllerException(final String message,
																final Throwable cause) {
		super(message,
		      cause);
	}

	protected ControllerException(final String message,
																final Throwable cause,
																final ExceptionContext context) {
		super(message);
	}
}
