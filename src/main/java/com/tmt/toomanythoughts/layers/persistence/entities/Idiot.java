package com.tmt.toomanythoughts.layers.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "idiot_id_seq", sequenceName = "idiot_id_seq", allocationSize = 1)
public class Idiot extends BaseEntity {

	@Id
	@GeneratedValue(generator = "idiot_id_seq")
	private Integer	id;

	private Integer	iq;

	private String	profession;

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getIq() {
		return this.iq;
	}

	public void setIq(final Integer iq) {
		this.iq = iq;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(final String profession) {
		this.profession = profession;
	}
}
