package com.tmt.toomanythoughts.layers.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmt.toomanythoughts.layers.persistence.entities.Idiot;

public interface IdiotRepository extends JpaRepository<Idiot, Integer> {

	Idiot findByIq(Integer iq);

}
