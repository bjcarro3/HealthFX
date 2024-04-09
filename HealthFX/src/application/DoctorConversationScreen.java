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
		this.patient = patient;
		Font titleFont = Font.font("Verdana", 25);
		Font textFont = Font.font("Verdana", 15);
		Font buttonFont = Font.font("Verdana", 12);
		
		//Main Body
		//Info Holder
		//Patient Name
		patientNameLabel = new Label("John Smith");
		patientNameLabel.setFont(titleFont);
		
		patientPhoneLabel = new Label("Phone: 111-111-TEST");
		patientPhoneLabel.setFont(textFont);
		
		emergencyNameLabel = new Label("Emergency Contact: PERSON SMITH");
		emergencyNameLabel.setFont(textFont);
		
		guardianPhoneLabel = new Label("Guardian Phone: 111-111-TEST");
		guardianPhoneLabel.setFont(textFont);
		
		emergencyPhoneLabel = new Label("Contact's Phone: 111-111-TEST");
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
		
		//Send Area
		//Sender Name
		senderNameLabel = new Label("DOCTOR NAME:");
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
	
	private class SendHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			sendArea.setText("");
		} //End handle
	} //End subclass
	
	private class InboxHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = null;
			try {
				medSys = MedicalSystem.getInstance();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			medSys.toDoctorInboxScreen(doctor);
		} //End handle
	} //End subclass
	
	private class LogOutHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = null;
			try {
				medSys = MedicalSystem.getInstance();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			medSys.toHomePage();
		} //End handle
	} //End subclass
}
