//Group: Tu37
//Description: Parent UI containing sidebars when a Patient is pulled up by a doctor. Is also the default
//			   UI when a patient's records are pulled up before the specific UI is chosen.

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat; 
import java.util.Date; 

//import application.DoctorSearchScreen.MessagesHandler;
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

public class DoctorView extends BorderPane {
	protected Doctor doctor;
	protected Patient patient;
	private Label patientName;
	private Button addVisitButton;
	private Button contactButton;
	private Button medHistoryButton;
	private Button[] visitButton;
	private Button messagesButton;
	private Button exitButton;
	private ImageView logoImageView;
	private VBox leftColumn;
	private HBox nameHolder;
	private HBox contactHolder;
	private HBox medHistoryHolder;
	private BorderPane rightColumn;
	private HBox messageHolder;
	private HBox imageHolder;
	private HBox exitHolder;
	private int numVisit = 0;
	
	public DoctorView(Doctor doctor, Patient patient) {
		this.doctor = doctor;
		this.patient = patient;
		
		Font nameFont = Font.font("Verdana", 20);
		Font addFont = Font.font("Verdana", 20);
		Font buttonFont = Font.font("Verdana", 12);
		
		//Left Column
		leftColumn = new VBox();
		leftColumn.setSpacing(5);
		leftColumn.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		leftColumn.setPrefHeight(1280);
		leftColumn.setPadding(new Insets(10, 10, 10 ,10));
		
		//patientName = new Label(patient.getFirstName() + " " + patient.getLastName());
		patientName = new Label(patient.getFirstName() + " " + patient.getLastName());
		patientName.setFont(nameFont);
		addVisitButton = new Button("+");
		addVisitButton.setFont(addFont);
		addVisitButton.setPrefSize(50, 50);
		nameHolder = new HBox(patientName, addVisitButton);
		nameHolder.setAlignment(Pos.CENTER);
		nameHolder.setSpacing(20);
		leftColumn.getChildren().addAll(nameHolder);
		addVisitButton.setOnAction(new AddVisitHandler());
		
		contactButton = new Button("Patient Information");
		contactButton.setFont(buttonFont);
		contactHolder = new HBox(contactButton);
		contactHolder.setAlignment(Pos.CENTER);
		leftColumn.getChildren().add(contactHolder);
		contactButton.setOnAction(new PatientInfosHandler());
		
		medHistoryButton = new Button("Medical History");
		medHistoryButton.setFont(buttonFont);
		medHistoryHolder = new HBox(medHistoryButton);
		medHistoryHolder.setAlignment(Pos.CENTER);
		leftColumn.getChildren().add(medHistoryHolder);
		medHistoryButton.setOnAction(new MedicalHistoryHandler());
		
		// find # of visits
		String fileName = "src/assets/appointments/" + patient.getFirstName() + patient.getLastName();
		File file = new File(fileName + ".txt");
		while (file.exists()) {
			numVisit++;
			file = new File(fileName + numVisit + ".txt");
		}
		
		visitButton = new Button[numVisit];
		//For each visit for the patient create a button to view that visit
		for (int i = 0; i < visitButton.length; i++) {
			final int index = i;
			
			visitButton[i] = new Button("Visit " + (i + 1));
			visitButton[i].setFont(buttonFont);
			
			visitButton[i].setOnAction(event -> {
				MedicalSystem medSys = MedicalSystem.getInstance();
				savePatient();
				medSys.toDoctorVisitScreen(doctor, patient, index);
			});
        }
		VBox buttonHolder = new VBox();
		buttonHolder.getChildren().addAll(visitButton);
		buttonHolder.setAlignment(Pos.CENTER);
		leftColumn.getChildren().add(buttonHolder);
				
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
		messagesButton.setOnAction(new MessagesHandler());
		
		//Logo
		Image logoImage = new Image("/assets/logo.png");
		logoImageView = new ImageView(logoImage);
		logoImageView.setFitWidth(100);
		logoImageView.setFitHeight(100);
		imageHolder = new HBox(logoImageView);
		imageHolder.setAlignment(Pos.CENTER);
		rightColumn.setCenter(imageHolder);
		
		//Logout Button
		exitButton = new Button("Exit");
		exitButton.setPrefSize(100, 100);
		exitButton.setFont(buttonFont);
		exitHolder = new HBox(exitButton);
		exitHolder.setAlignment(Pos.CENTER);
		rightColumn.setBottom(exitHolder);
		exitButton.setOnAction(new ExitHandler());
				
		this.setLeft(leftColumn);
		this.setRight(rightColumn);		
		
	}
	
	//Save the patient (called when screen exited), different for each child UI
	protected void savePatient() {
		MedicalSystem medSys = MedicalSystem.getInstance();
		try {
			medSys.savePatientInfo(patient);
			medSys.saveMedicalHistory(patient);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private class PatientInfosHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			savePatient();
			medSys.toDoctorPatientInfoScreen(doctor, patient);
		} //End handle
	} //End subclass
	
	private class MedicalHistoryHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			savePatient();
			medSys.toMedHistoryScreen(doctor, patient);
		} //End handle
	} //End subclass
	
	private class MessagesHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			savePatient();
			medSys.toDoctorInboxScreen(doctor);
		} //End handle
	} //End subclass
	
	private class ExitHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			savePatient();
			medSys.toDoctorSearch(doctor);
		} //End handle
	} //End subclass
	
	//Create a new visit for today's date for the patient and add it to the system
	private class AddVisitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = null;
			SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
			String currentDate = ft.format(new Date()); 
			String fileName;
			if (numVisit == 0) {
				fileName = "src/assets/appointments/" + patient.getFirstName() + patient.getLastName() + ".txt";
			} else {
				fileName = "src/assets/appointments/" + patient.getFirstName() + patient.getLastName() + Integer.toString(numVisit) + ".txt";
			}
			numVisit++;
			File file = new File(fileName);
			try {
	    		// Creates new patient file and writes the login info
	    		PrintWriter pw = new PrintWriter(file);
	    		pw.println(currentDate);
	    		pw.close();
		    	
			} catch (IOException e) {
				System.out.println("Account Could Not Be Created: File Error");
			}
			medSys = MedicalSystem.getInstance();
			medSys.toDoctorVisitScreen(doctor, patient, numVisit-1);
			
		}
	}
}
