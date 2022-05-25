package com.bharath.springdata.patientscheduling;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springdata.patientscheduling.entities.Appointment;
import com.bharath.springdata.patientscheduling.entities.Doctor;
import com.bharath.springdata.patientscheduling.entities.Insurance;
import com.bharath.springdata.patientscheduling.entities.Patient;
import com.bharath.springdata.patientscheduling.repos.AppointmentRepository;
import com.bharath.springdata.patientscheduling.repos.DoctorRepository;
import com.bharath.springdata.patientscheduling.repos.PatientRepository;

@SpringBootTest
class PatientschedulingApplicationTests {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Test
	void testCreateDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("Bharath");
		doctor.setLastName("Thippireddy");
		doctor.setSpeciality("All");
		doctorRepository.save(doctor);
	}
	
	@Test
	void testCreatePatient() {
		Patient patient = new Patient();
		patient.setFirstName("Doug");
		patient.setLastName("Bailey");
		patient.setPhone("123456");
		
		Insurance insurance = new Insurance();
		insurance.setProviderName("Blue Cross Blue Shield");
		insurance.setCopay(20d);
		
		Doctor doctor = doctorRepository.findById(1L).get();
		List<Doctor> doctors = Arrays.asList(doctor);
		patient.setDoctors(doctors);
		
		patient.setInsurance(insurance);
		
		patientRepository.save(patient);
	}

	@Test
	void testCreateAppointment() {
		
		Appointment appointment = new Appointment();
		Timestamp appointmentTime = new Timestamp(new Date().getTime());
		appointment.setAppointmentTime(appointmentTime);
		appointment.setReason("I have a problem");
		appointment.setStarted(true);
		
		appointment.setPatient(patientRepository.findById(1L).get());
		appointment.setDoctor(doctorRepository.findById(1L).get());
		
		appointmentRepository.save(appointment);
	}
	
}














