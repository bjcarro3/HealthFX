package application;

import java.util.ArrayList;

public class Conversation {
	private String patient;
	private String doctor;
	private ArrayList<String> messages;
	
	public Conversation(String patient, String doctor, ArrayList<String> messages) {
		this.patient = patient;
		this.doctor = doctor;
		this.messages = messages;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
}
