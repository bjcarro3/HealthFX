package application;

import java.util.ArrayList;

public class Patient {
	private String firstName;
	private String lastName;
	private String birthday;
	private PatientInfo patientInfo;
	private MedHistory medHistory;
	private Conversation conversation;
	private ArrayList<Appointment> appointments;
	
	public Patient(String firstName, String lastName, String birthday, PatientInfo patientInfo, MedHistory medHistory) {
		this.firstName = firstName;
		this.lastName= lastName;
		this.birthday = birthday;
		this.patientInfo = patientInfo;
		this.medHistory = medHistory;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public MedHistory getMedHistory() {
		return medHistory;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}
	
}
