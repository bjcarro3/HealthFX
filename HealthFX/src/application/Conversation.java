package application;

import java.util.ArrayList;

public class Conversation {
	private String patient;
	private String doctor;
	private ArrayList<String> messages;
	
	public Conversation(String patient, String doctor, ArrayList<String> messages) {
		this.patient = patient;
		this.doctor = doctor;
		if(messages != null) {
			this.messages = messages;
		}
		else {
			this.messages = new ArrayList<>();
		}
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
	public ArrayList<String> getMessages() {
		return messages;
	}
}
