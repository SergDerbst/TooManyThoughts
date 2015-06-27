package com.tmt.toomanythoughts.layers.controller.model;

import com.tmt.toomanythoughts.layers.persistence.entities.Idiot;

public class IdiotResponse {

	private final String	profession;

	public IdiotResponse(final Idiot idiot) {
		this.profession = idiot.getProfession();
	}

	public String getProfession() {
		return this.profession;
	}
}
