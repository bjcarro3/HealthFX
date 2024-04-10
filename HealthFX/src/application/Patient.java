package application;

import java.util.ArrayList;

public class Patient {
	private String firstName;
	private String lastName;
	private String birthday;
	private PatientInfo patientInfo;
	private MedHistory medHistory;
	private Conversation conversation;
	
	public Patient(String firstName, String lastName, String birthday, PatientInfo patientInfo, MedHistory medHistory) {
		this.firstName = firstName;
		this.lastName= lastName;
		this.birthday = birthday;
		this.patientInfo = patientInfo;
		this.medHistory = medHistory;
		this.conversation = new Conversation(new ArrayList<String>());
	}
	
	//Write the patient info
	public String writeInformation() {
		String info = firstName + "\n" +
				lastName + "\n" +
				birthday + "\n" +
				patientInfo.writePatientInfo() + "\n" +
				medHistory.writeMedicalHistory() + "\n";
		return info;
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
	
	public void setConversation(Conversation update) {
		this.conversation = update;
	}
	
}
