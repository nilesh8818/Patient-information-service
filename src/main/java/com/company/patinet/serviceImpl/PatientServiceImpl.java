package com.company.patinet.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.exception.EmptyResultDataAccessException;
import com.company.exception.PatientBadRequestException;
import com.company.exception.PatientNotFoundException;
import com.company.patinet.dao.PatientRepository;
import com.company.patinet.exposedpojo.PatientExposed;
import com.company.patinet.pojo.Patient;
import com.company.patinet.service.PatientService;

/**
 * @since 04-Jul-2019 7:50:22 am
 *
 */
@Service
public class PatientServiceImpl implements PatientService {

	private final Logger		log	= LoggerFactory.getLogger (PatientServiceImpl.class);

	@Autowired
	private PatientRepository	patientRepository;

	@Autowired
	private Mapper				mapper;

	private PatientExposed		patientExposed;

	// save new patient
	@Override
	public PatientExposed createNewPatinets (Patient patient) throws PatientBadRequestException {

		if (StringUtils.isEmpty (patient.getFirstName ())
			|| StringUtils.isEmpty (patient.getLastName ())
			|| StringUtils.isEmpty (patient.getState ())
			|| StringUtils.isEmpty (patient.getCity ())) {
			throw new PatientBadRequestException ("Patient Request is not correct");
		}

		String guid = genrateGuid ();
		patient.setGuid (guid);
		patient.setCreatedDate (new Date ());
		patient.setUpdatedDate (new Date ());
		patient = patientRepository.save (patient);

		if (StringUtils.isEmpty (patient.toString ())) {
			throw new PatientNotFoundException ("Patient is not stored");
		}

		return patientExposed = mapper.map (patient, PatientExposed.class);  // Dozzer pojo mapping

	}

	// return guid
	private String genrateGuid () {
		UUID uuid = UUID.randomUUID ();
		String guid = uuid.toString ();
		log.info ("Guid{} Genrated for the user is", guid);
		return guid;
	}

	// get patient by first name
	@Override
	public PatientExposed getPatientByParticularName (String firstName)
		throws PatientBadRequestException, PatientNotFoundException {

		if (StringUtils.isBlank (firstName)) {
			throw new PatientBadRequestException ("patient first name should not be empty");
		}

		Patient patient = patientRepository.findByFirstName (firstName);

		if (patient == null) {
			throw new PatientNotFoundException (
				"Patient is not found for particular name=" + firstName);
		}

		return patientExposed = mapper.map (patient, PatientExposed.class);

	}

	// get all patient
	@Override
	public List <PatientExposed> getAllPatient () {

		log.info ("get the all user");
		List <Patient> patient = patientRepository.findAll ();
		List <PatientExposed> pa = new ArrayList <> ();
		for (Patient pat : patient) {
			patientExposed = mapper.map (pat, PatientExposed.class);
			pa.add (patientExposed);
		}

		if (pa.isEmpty ()) {
			throw new PatientNotFoundException ("List of patient not found");
		}

		return pa;
	}

	// updataing existing patient
	@Override
	public PatientExposed updateExistingPatient (Patient patient, BigInteger id)
		throws PatientBadRequestException, PatientNotFoundException {

		if (StringUtils.isEmpty (patient.toString ())) {
			throw new PatientBadRequestException ("patient or id should not be empty");
		}

		Patient patt = patientRepository.findOne (id);
		if (patt == null) {
			throw new PatientNotFoundException (
				"patient is not found for particular patientId=" + id);
		}
        
		patient.setGuid (patt.getGuid ());
		patient.setCreatedDate (patt.getCreatedDate ());
		Patient pat = patientRepository.save (patient);
		patientExposed = mapper.map (pat, PatientExposed.class);

		if (!patient.equals (pat)) {
			log.error ("User not updated for id= {}", id);
		}
		return patientExposed;
	}

	// delete existing patient
	/**
	 * @param id
	 * @throws PatientNotFoundException
	 * @throws PatientBadRequestException
	 */
	@Override
	public String deleteExistingPatient (BigInteger id)
		throws EmptyResultDataAccessException, PatientNotFoundException {

		try {
			patientRepository.delete (id);
		}
		catch (Exception e) {
			throw new PatientNotFoundException ("patient not found for particular id=" + id);
		}

		log.info ("Patinet deletd successfully for the id={}", id);

		return "Patient Deletd Successfully";
	}

	
}
