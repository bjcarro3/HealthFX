//Group: Tu37
//Description: Stores an ArrayList of Strings representing a conversation log, used for messaging

package application;

import java.util.ArrayList;

public class Conversation {
	private ArrayList<String> messages;
	
	public Conversation(ArrayList<String> messages) {
		this.messages = messages;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
	public ArrayList<String> getMessages() {
		return messages;
	}
}
