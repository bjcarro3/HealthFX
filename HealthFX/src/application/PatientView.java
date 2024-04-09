package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class PatientView extends BorderPane {
	protected Patient patient;
	private Label patientName;
	private Button contactButton;
	//private ArrayList<Button> visitButtons;
	private Button[] visitButton;
	private Button messagesButton;
	private Button logoutButton;
	private ImageView logoImageView;
	private VBox leftColumn;
	private HBox nameHolder;
	private HBox contactHolder;
	private BorderPane rightColumn;
	private HBox messageHolder;
	private HBox imageHolder;
	private HBox logoutHolder;
	
	public PatientView(Patient patient) {
		this.patient = patient;
		Font nameFont = Font.font("Verdana", 20);
		Font buttonFont = Font.font("Verdana", 12);
		
		//Left Column
		leftColumn = new VBox();
		leftColumn.setSpacing(5);
		leftColumn.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		leftColumn.setPrefHeight(1280);
		leftColumn.setPadding(new Insets(10, 10, 10 ,10));
		
		//patientName = new Label(patient.getFirstName() + " " + patient.getLastName());
		patientName = new Label("John Smith");
		patientName.setFont(nameFont);
		nameHolder = new HBox(patientName);
		nameHolder.setAlignment(Pos.CENTER);
		leftColumn.getChildren().add(nameHolder);
		
		contactButton = new Button("Contact Information");
		contactButton.setFont(buttonFont);
		contactHolder = new HBox(contactButton);
		contactHolder.setAlignment(Pos.CENTER);
		leftColumn.getChildren().add(contactHolder);
		contactButton.setOnAction(new ContactHandler());
		
		//visitButton = new Button[numVisit];
		visitButton = new Button[3];
		
		for (int i = 0; i < visitButton.length; i++) {
			visitButton[i] = new Button("Button " + (i + 1));
        }
		VBox buttonHolder = new VBox();
		buttonHolder.getChildren().addAll(visitButton);
		buttonHolder.setAlignment(Pos.CENTER);
		leftColumn.getChildren().add(buttonHolder);
		
		/*
		for (int i=0; i<3; i++) {
			Button visitButton = new Button("Visit " + i);
			visitButton.setFont(buttonFont);
			VBox buttonHolder = new VBox(visitButton);
			buttonHolder.setAlignment(Pos.CENTER);
			leftColumn.getChildren().add(buttonHolder);
		}
		*/
		
		//Right Column
		rightColumn = new BorderPane();
		rightColumn.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		rightColumn.setPrefHeight(1280);
		rightColumn.setPadding(new Insets(10, 10, 10 ,10));
		
		//Messages Button
		messagesButton = new Button("Messages");
		messagesButton.setPrefSize(100, 100);
		messagesButton.setFont(buttonFont);
		messageHolder = new HBox(messagesButton);
		messageHolder.setAlignment(Pos.CENTER);
		rightColumn.setTop(messageHolder);
		messagesButton.setOnAction(new PatientMessageHandler());
		
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
		
		
		this.setLeft(leftColumn);
		this.setRight(rightColumn);
	}
	
	private class ContactHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = null;
			try {
				medSys = MedicalSystem.getInstance();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			medSys.toPatientInfoScreen(patient);
		} //End handle
	} //End subclass
	
	private class PatientMessageHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = null;
			try {
				medSys = MedicalSystem.getInstance();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			medSys.toPatientMessageScreen(patient);
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
