package application;

import java.io.FileNotFoundException;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PatientConversationScreen extends BorderPane {
	private Patient patient;
	
	//Main Body
	private VBox bodyHolder;
	private Label titleLabel;
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
	
	
	
	public PatientConversationScreen(Patient patient) {
		this.patient = patient;
		MedicalSystem medSys = MedicalSystem.getInstance();
		medSys.getConversation((patient.getFirstName() + patient.getLastName()));
		patient.setConversation(medSys.getConversation((patient.getFirstName() + patient.getLastName())));
		
		
		Font titleFont = Font.font("Verdana", 25);
		Font textFont = Font.font("Verdana", 15);
		Font buttonFont = Font.font("Verdana", 12);
		
		//Main Body
		//title
		titleLabel = new Label("Messages");
		titleLabel.setFont(titleFont);
		
		messageArea = new TextArea();
		messageArea.setFont(textFont);
		messageArea.setEditable(false);
		messageArea.setPrefHeight(500);
		displayMessages();
		
		//Send Area
		//Sender Name
		senderNameLabel = new Label(patient.getFirstName() + " " + patient.getLastName() + ":");
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
		bodyHolder = new VBox(titleLabel, messageArea, senderHolder);
		bodyHolder.setSpacing(30);
		bodyHolder.setPadding(new Insets(10, 10, 10, 10));
		
		
		
		//Right Column
		rightColumn = new BorderPane();
		rightColumn.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		rightColumn.setPrefHeight(1280);
		rightColumn.setPadding(new Insets(10, 10, 10 ,10));
		
		//Messages Button
		closeButton = new Button("Close");
		closeButton.setPrefSize(100, 100);
		closeButton.setFont(buttonFont);
		closeHolder = new HBox(closeButton);
		closeHolder.setAlignment(Pos.CENTER);
		rightColumn.setTop(closeHolder);
		closeButton.setOnAction(new CloseHandler());
						
		//Logo
		Image logoImage = new Image("/assets/logo.png");
		logoImageView = new ImageView(logoImage);
		logoImageView.setFitWidth(100);
		logoImageView.setFitHeight(100);
		imageHolder = new HBox(logoImageView);
		imageHolder.setAlignment(Pos.CENTER);
		rightColumn.setCenter(imageHolder);
						
		//Logout Button
		logoutButton = new Button("Log Out");
		logoutButton.setPrefSize(100, 100);
		logoutButton.setFont(buttonFont);
		logoutHolder = new HBox(logoutButton);
		logoutHolder.setAlignment(Pos.CENTER);
		rightColumn.setBottom(logoutHolder);
		logoutButton.setOnAction(new LogOutHandler());
		
		this.setCenter(bodyHolder);
		this.setRight(rightColumn);
	}
	
	private void displayMessages() {
		messageArea.clear();
		for (String message : patient.getConversation().getMessages()) {
			messageArea.appendText(message + "\n");
		}
	}
	
	private class SendHandler implements EventHandler<ActionEvent> {

		@SuppressWarnings("null")
		public void handle(ActionEvent arg0) {
			if(!sendArea.getText().isBlank()) {
				patient.getConversation().addMessage(
						patient.getFirstName() + " " + patient.getLastName() + ": " + sendArea.getText());
				sendArea.setText(""); //Clear text field
				displayMessages();
				MedicalSystem medSys = MedicalSystem.getInstance();
				medSys.saveConversation((patient.getFirstName() + patient.getLastName()), patient.getConversation());
				
			}
		} //End handle
	} //End subclass
	
	private class CloseHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			medSys.toPatientInfoScreen(patient);
		} //End handle
	} //End subclass
	
	private class LogOutHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			medSys.toHomePage();
		} //End handle
	} //End subclass
	
}
