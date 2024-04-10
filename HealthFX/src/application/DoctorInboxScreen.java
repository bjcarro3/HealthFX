package application;


import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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

public class DoctorInboxScreen extends BorderPane {
	private Doctor doctor;
	
	//Main Body
	private VBox bodyHolder;
	private Label titleLabel;
	private ArrayList<Button> messageList;
	private int numPatient;
	
	
	//Right side
	private Button closeButton;
	private Button exitButton;
	private ImageView logoImageView;
	private BorderPane rightColumn;
	private HBox closeHolder;
	private HBox imageHolder;
	private HBox logoutHolder;
	
	private File files;
	
	public DoctorInboxScreen(Doctor doctor) {
		this.doctor = doctor;
		//setupEventHandlers();
		Font titleFont = Font.font("Verdana", 25);
		Font textFont = Font.font("Verdana", 15);
		Font buttonFont = Font.font("Verdana", 12);
		
		//Main Body
		bodyHolder = new VBox();
		bodyHolder.setSpacing(5);
		bodyHolder.setPadding(new Insets(10, 10, 10, 10));
		
		titleLabel = new Label("Inbox");
		titleLabel.setFont(titleFont);
		bodyHolder.getChildren().add(titleLabel);


		// Calculate number of patients
		File dir = new File("src/assets/patients");
		File[] directoryListing = dir.listFiles();
		numPatient = directoryListing.length;
		messageList = new ArrayList<Button>();
		for (File child : directoryListing) {
			
		    try {
		    	if ((child.getName().substring(child.getName().length() - 3)).equals("txt")) {
		    		Scanner scan = new Scanner(child);
					String firstName = scan.nextLine();
					String lastName = scan.nextLine();
					String birthday = scan.nextLine();
					scan.close();
					
					Button btnToAdd = new Button(firstName + " " + lastName);
					messageList.add(btnToAdd);
					btnToAdd.setOnAction(event -> {
					MedicalSystem medSys = null;
					Patient patient = null;
					try {
						medSys = MedicalSystem.getInstance();
						patient  = medSys.getPatient(firstName+lastName, birthday);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					medSys.toDoctorConversationScreen(doctor, patient);
					});
		    	}
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		      
		}
		
		//messageList = new Button[numPatient];
		/*
		messageList = new Button[3];
		
		for (int i = 0; i < messageList.length; i++) {
			final int index = i;
			File[] files = new File("src/assets/conversations").listFiles();
			
			messageList[i] = new Button("Patient" + (i+1)); //"Patient " + (i+1) + ": " + Patient.name
			
			Patient patient = null; //patient = patientID or like patient[i]
			
			messageList[i].setOnAction(event -> {
				MedicalSystem medSys = null;
				try {
					medSys = MedicalSystem.getInstance();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				medSys.toDoctorConversationScreen(doctor, patient, index);
			});
		}
		*/
		bodyHolder.getChildren().addAll(messageList);
		/*
		for (int i=0; i<3; i++) {
			Button patientButton = new Button("Patient " + i);
			patientButton.setFont(textFont);
			patientButton.setPrefSize(1920, 40);
			patientButton.setAlignment(Pos.CENTER_LEFT);
			bodyHolder.getChildren().add(patientButton);
		}
		*/
		
		
		
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
		closeButton.setOnAction(new BackHandler());
						
		//Logo
		Image logoImage = new Image("/assets/logo.png");
		logoImageView = new ImageView(logoImage);
		logoImageView.setFitWidth(100);
		logoImageView.setFitHeight(100);
		imageHolder = new HBox(logoImageView);
		imageHolder.setAlignment(Pos.CENTER);
		rightColumn.setCenter(imageHolder);
						
		//Exit Button
		exitButton = new Button("Exit");
		exitButton.setPrefSize(100, 100);
		exitButton.setFont(buttonFont);
		logoutHolder = new HBox(exitButton);
		logoutHolder.setAlignment(Pos.CENTER);
		rightColumn.setBottom(logoutHolder);
		exitButton.setOnAction(new ExitHandler());
		
		this.setCenter(bodyHolder);
		this.setRight(rightColumn);
	}
	/* added lines 101 - 140... debugging necessary*/ 
	/*
	 private void setupEventHandlers() {
	        closeButton.setOnAction(new BackHandler());
	        exitButton.setOnAction(new ExitHandler());
	        // Add event handlers for patient buttons
	        for (Button patientButton : messageList) {
	            patientButton.setOnAction(new PatientButton());
	        }
	    }
	 
	 //Calls MedicalSystem to change the screen to PatientInfoScreen after clicking button.
	 private class PatientButton implements EventHandler<ActionEvent> {

	        @Override
	        public void handle(ActionEvent event) {
	            try {
					MedicalSystem medSys = MedicalSystem.getInstance();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       //     medSys.toPatientInfo(); //INCORRECT IMPLEMENTATION; NEEDS TO BE FIXED
	        }
	    }
	    */

	    // Calls the MedicalSystem to change the screen to the home page
	    private class BackHandler implements EventHandler<ActionEvent> {

	        @Override
	        public void handle(ActionEvent event) {
	            MedicalSystem medSys = MedicalSystem.getInstance();
	            medSys.toDoctorSearch(doctor);	//Currently, clicking closeButton redirects to HomePage... FIX
	        }
	    }

	    // Handles exit: brings user back to HomePageScreen
	    private class ExitHandler implements EventHandler<ActionEvent> {

	        @Override
	        public void handle(ActionEvent event) {
	        	MedicalSystem medSys = MedicalSystem.getInstance();
	            medSys.toHomePage();	
	        }
	    }
	
}
