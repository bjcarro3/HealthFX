//Group: Tu37
//Description: UI used when a Doctor is messaging a specific patient. Allows the doctor to view messages,
//			   send new messages, and view a patient's contact information if contacting is necessary

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DoctorConversationScreen extends BorderPane {
	private Doctor doctor;
	private Patient patient;
	
	//Main Body
	private VBox bodyHolder;
	private GridPane infoHolder;
	private Label patientNameLabel;
	private Label patientPhoneLabel;
	private Label emergencyNameLabel;
	private Label guardianPhoneLabel;
	private Label emergencyPhoneLabel;
	private TextArea messageArea;
	private HBox senderHolder;
	private Label senderNameLabel;
	private TextArea sendArea;
	private Button sendButton;
	private HBox buttonHolder;
	
	
	
	//Right Column
	private Button closeButton;
	private Button logoutButton;
	private ImageView logoImageView;
	private BorderPane rightColumn;
	private HBox closeHolder;
	private HBox imageHolder;
	private HBox logoutHolder;
	
	
	
	public DoctorConversationScreen(Doctor doctor, Patient patient) {
		this.doctor = doctor;
		this.patient = patient;
		MedicalSystem medSys = MedicalSystem.getInstance();
		patient.setConversation(medSys.getConversation((patient.getFirstName() + patient.getLastName())));
		Font titleFont = Font.font("Verdana", 25);
		Font textFont = Font.font("Verdana", 15);
		Font buttonFont = Font.font("Verdana", 12);
		
		//Main Body
		//Info Holder
		//Patient Name
		patientNameLabel = new Label(patient.getFirstName() + " " + patient.getLastName());
		patientNameLabel.setFont(titleFont);
		
		String patientPhoneLabelString = "Phone: " + patient.getPatientInfo().getPhoneNumber();
		patientPhoneLabel = new Label(patientPhoneLabelString);
		patientPhoneLabel.setFont(textFont);
		
		emergencyNameLabel = new Label("Emergency Contact: " + patient.getPatientInfo().getEmergencyName());
		emergencyNameLabel.setFont(textFont);
		
		guardianPhoneLabel = new Label("Guardian Phone: " + patient.getPatientInfo().getGuardianPhone());
		guardianPhoneLabel.setFont(textFont);
		
		emergencyPhoneLabel = new Label("Contact's Phone: " + patient.getPatientInfo().getEmergencyPhone());
		emergencyPhoneLabel.setFont(textFont);
		
		//Set up info holder
		infoHolder = new GridPane();
		infoHolder.setVgap(10);
		infoHolder.setHgap(80);
		infoHolder.add(patientNameLabel, 0, 0, 1, 2);
		infoHolder.add(patientPhoneLabel, 1, 0);
		infoHolder.add(emergencyNameLabel, 2, 0);
		infoHolder.add(guardianPhoneLabel, 1, 1);
		infoHolder.add(emergencyPhoneLabel, 2, 1);
		
		messageArea = new TextArea();
		messageArea.setFont(textFont);
		messageArea.setEditable(false);
		messageArea.setPrefHeight(500);
		displayMessages();
		
		//Send Area
		//Sender Name
		senderNameLabel = new Label("Dr. " + doctor.getName() + ":");
		senderNameLabel.setFont(textFont);
		
		//Sender Text Field
		sendArea = new TextArea();
		sendArea.setFont(textFont);
		sendArea.setPrefSize(800, 100);
		sendArea.setWrapText(true);
		
		//Send Button
		sendButton = new Button("Send");
		sendButton.setFont(textFont);
		buttonHolder = new HBox(sendButton);
		buttonHolder.setAlignment(Pos.CENTER_RIGHT);
		sendButton.setOnAction(new SendHandler());
		
		//Set Up Sender Holder
		senderHolder = new HBox(senderNameLabel, sendArea, buttonHolder);
		senderHolder.setSpacing(50);
		
		//Set up body holder
		bodyHolder = new VBox(infoHolder, messageArea, senderHolder);
		bodyHolder.setSpacing(30);
		bodyHolder.setPadding(new Insets(10, 10, 10, 10));
		
		
		
		//Right Column
		rightColumn = new BorderPane();
		rightColumn.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		rightColumn.setPrefHeight(1280);
		rightColumn.setPadding(new Insets(10, 10, 10 ,10));
		
		//Messages Button
		closeButton = new Button("Inbox");
		closeButton.setPrefSize(100, 100);
		closeButton.setFont(buttonFont);
		closeHolder = new HBox(closeButton);
		closeHolder.setAlignment(Pos.CENTER);
		rightColumn.setTop(closeHolder);
		closeButton.setOnAction(new InboxHandler());
						
		//Logo
		Image logoImage = new Image("/assets/logo.png");
		logoImageView = new ImageView(logoImage);
		logoImageView.setFitWidth(100);
		logoImageView.setFitHeight(100);
		imageHolder = new HBox(logoImageView);
		imageHolder.setAlignment(Pos.CENTER);
		rightColumn.setCenter(imageHolder);
						
		//Logout Button
		logoutButton = new Button("Exit");
		logoutButton.setPrefSize(100, 100);
		logoutButton.setFont(buttonFont);
		logoutHolder = new HBox(logoutButton);
		logoutHolder.setAlignment(Pos.CENTER);
		rightColumn.setBottom(logoutHolder);
		logoutButton.setOnAction(new ExitHandler());
						
		
		this.setCenter(bodyHolder);
		this.setRight(rightColumn);
	}
	
	//Prints the conversation object to the screen
	private void displayMessages() {
		messageArea.clear();
		for (String message : patient.getConversation().getMessages()) {
			messageArea.appendText(message + "\n");
		}
	}
	
	//Appends message from the SendArea to the conversation
	private class SendHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			if(!sendArea.getText().isBlank()) {
				patient.getConversation().addMessage(
						"Dr. " + doctor.getName() + ": " + sendArea.getText());
				sendArea.setText(""); //Clear text field
				displayMessages();
				MedicalSystem medSys = MedicalSystem.getInstance();
				medSys.saveConversation((patient.getFirstName() + patient.getLastName()), patient.getConversation());
			}
		} //End handle
	} //End subclass
	
	//Goes to inbox when inbox button is clicked
	private class InboxHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			medSys.toDoctorInboxScreen(doctor);
		} //End handle
	} //End subclass
	
	private class ExitHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			medSys.toDoctorSearch(doctor);
		} //End handle
	} //End subclass
}
