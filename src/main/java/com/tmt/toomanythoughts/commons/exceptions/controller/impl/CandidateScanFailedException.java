package com.tmt.toomanythoughts.commons.exceptions.controller.impl;

import com.tmt.toomanythoughts.commons.exceptions.controller.ControllerException;

@SuppressWarnings("serial")
public class CandidateScanFailedException extends ControllerException {

	public CandidateScanFailedException(final Throwable cause) {
		super(cause);
	}
}
