package com.company.patinet.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.patinet.pojo.Patient;

/**
 * @since 04-Jul-2019 7:57:05 am
 *
 */
public interface PatientRepository extends JpaRepository <Patient, BigInteger> {

	/**
	 * @param firstName
	 * @return repository
	 */
	Patient findByFirstName (String firstName);

	/**
	 * @param id
	 */
	void delete (BigInteger id);

	/**
	 * @param patientId
	 * @param id
	 * @return
	 */
	Patient findByPatientId (BigInteger patientId);

}
