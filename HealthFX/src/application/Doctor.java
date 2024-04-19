//Group: Tu37
//Description: Represents a Doctor. Used to store information about currently signed in Doctor

package application;

import java.util.ArrayList;

public class Doctor {
	private String name;
	private ArrayList<Conversation> conversations;
	
	public Doctor(String name, ArrayList<Conversation> conversations) {
		this.name = name;
		this.conversations = conversations;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Conversation> getConversations() {
		return conversations;
	}
	
	public void setConversations(ArrayList<Conversation> conversations) {
		this.conversations = conversations;
	}
}
