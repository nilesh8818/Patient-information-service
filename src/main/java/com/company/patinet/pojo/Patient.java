package com.company.patinet.pojo;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since 04-Jul-2019 7:54:39 am
 *
 */
@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "patientid")
	private BigInteger patientId;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "state")
	private String state;
	
	@Column(name="city")
	private String city;
	
	@Column(name="guid")
	private String guid;
	
	@Column(name = "createdate")
	private Date createdDate;

	@Column(name = "updatedate")
	private Date updatedDate;

	/**
	 * @since 04-Jul-2019 
	 */
	public Patient () {
		super ();
	}

	/**
	 * @param patientId
	 * @param firstName
	 * @param lastName
	 * @param state
	 * @param city
	 * @param guid
	 * @param createdDate
	 * @param updatedDate
	 */
	public Patient (BigInteger patientId, String firstName, String lastName, String state, String city,
		String guid, Timestamp createdDate, Timestamp updatedDate) {
		super ();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.state = state;
		this.city = city;
		this.guid = guid;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	/**
	 * @return
	 */
	public BigInteger getPatientId () {
		return patientId;
	}

	/**
	 * @param patientId
	 */
	public void setPatientId (BigInteger patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return
	 */
	public String getFirstName () {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getLastName () {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName (String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return
	 */
	public String getState () {
		return state;
	}

	/**
	 * @param state
	 */
	public void setState (String state) {
		this.state = state;
	}

	/**
	 * @return
	 */
	public String getCity () {
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity (String city) {
		this.city = city;
	}

	/**
	 * @return
	 */
	public String getGuid () {
		return guid;
	}

	/**
	 * @param guid
	 */
	public void setGuid (String guid) {
		this.guid = guid;
	}

	/**
	 * @return
	 */
	public Date getCreatedDate () {
		return createdDate;
	}

	/**
	 * @param date
	 */
	public void setCreatedDate (Date date) {
		this.createdDate = date;
	}

	/**
	 * @return
	 */
	public Date getUpdatedDate () {
		return updatedDate;
	}

	/**
	 * @param date
	 */
	public void setUpdatedDate (Date date) {
		this.updatedDate = date;
	}

	
}
