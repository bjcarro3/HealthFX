package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class CreateAccountScreen extends BorderPane {
	private VBox titleHolder; //Holds title and prompt to align
	private VBox inputHolder; 
	private HBox backHolder;
	private ImageView logoImageView;
	private Label titleLabel;
	private Label promptLabel;
	private Label statusLabel;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField birthdayField;
	private TextField passwordField;
	private Button createAccountButton;
	private Button backButton;
	
	private Patient newPatient;
	private PatientInfo newPatientInfo;
	private MedHistory newMedHistory;
	
	private File newPatientDir;
	private File patientLoginInfo;
	private File patientData;
	
	private FileWriter writer;
	
	
	public CreateAccountScreen() {
		//Logo
		Image logoImage = new Image("/assets/logo.png");
		logoImageView = new ImageView(logoImage);
		logoImageView.setFitWidth(150);
		logoImageView.setFitHeight(150);
		
		//Create title
		titleLabel = new Label("Patient Portal");
		titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 60));
		titleLabel.setTextFill(Color.rgb(132, 80, 139));
		
		//Create Prompt
		promptLabel = new Label("Create Account");
		promptLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 28));
		
		//Status Text
		statusLabel = new Label();
		statusLabel.setFont(Font.font("Verdana", 20));
		
		
		//Place title and prompt into titleHolder and align
		titleHolder = new VBox();
		titleHolder.setAlignment(Pos.CENTER);
		titleHolder.getChildren().addAll(logoImageView, titleLabel, promptLabel, statusLabel);
		
		//Create Text Fields
		Font fieldFont = Font.font("Verdana", 20);
		firstNameField = new TextField();
		firstNameField.setFont(fieldFont);
		firstNameField.setPromptText("First Name");
		firstNameField.setMaxWidth(500);
		
		lastNameField = new TextField();
		lastNameField.setFont(fieldFont);
		lastNameField.setPromptText("Last Name");
		lastNameField.setMaxWidth(500);

		birthdayField = new TextField();
		birthdayField.setFont(fieldFont);
		birthdayField.setPromptText("Birthday (mm/dd/yyyy)");
		birthdayField.setMaxWidth(500);
		
		passwordField = new TextField();
		passwordField.setFont(fieldFont);
		passwordField.setPromptText("Password");
		passwordField.setMaxWidth(500);
		
		//Create Account Button
		Font buttonFont = Font.font("Veranda", 20);
		createAccountButton = new Button("Create Account");
		createAccountButton.setPrefWidth(210);
		createAccountButton.setPrefHeight(50);
		createAccountButton.setFont(buttonFont);
		createAccountButton.setDisable(false);
		createAccountButton.setOnAction(new CreateAccountHandler());
		
		//Holder for all inputs
		inputHolder = new VBox(firstNameField, lastNameField, birthdayField, passwordField, createAccountButton);
		inputHolder.setSpacing(20);
		inputHolder.setAlignment(Pos.CENTER);
		
		//Back button
		backButton = new Button("Back");
		backButton.setPrefWidth(100);
		backButton.setPrefHeight(80);
		backButton.setFont(buttonFont);
		backButton.setOnAction(new BackHandler());
		
		//Holder for back button to align it to bottom right
		backHolder = new HBox(backButton);
		backHolder.setAlignment(Pos.BOTTOM_RIGHT);
		
		//Set up CreateAccount Pane
		this.setTop(titleHolder);
		this.setCenter(inputHolder);
		this.setBottom(backHolder);
		this.setBackground(new Background(new BackgroundFill(Color.rgb(100, 187, 248), CornerRadii.EMPTY, Insets.EMPTY)));
	} //End Constructor
	
	
	private class CreateAccountHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			if (firstNameField.getText().isBlank() || lastNameField.getText().isBlank() || birthdayField.getText().isBlank() || passwordField.getText().isBlank()) {
				statusLabel.setText("Account Could Not Be Created: Empty Fields");
				statusLabel.setTextFill(Color.RED);
				return;
			}
			
			//Checks if info for patient already exists
			patientLoginInfo = new File("src/assets/patients/" + firstNameField.getText() + lastNameField.getText() + ".txt");
			if(patientLoginInfo.exists()) {
				statusLabel.setText("Account Could Not Be Created: Patient Already Exists");
				statusLabel.setTextFill(Color.RED);
			} else {
		    //Writes the information to the new file and creates new file if not existed
		    	try {
		    		// Creates new patient file and writes the login info
		    		PrintWriter pw = new PrintWriter(patientLoginInfo);
		    		pw.println(firstNameField.getText());
		    		pw.println(lastNameField.getText());
		    		pw.println(birthdayField.getText());
		    		pw.println(passwordField.getText());
		    		pw.close();
			    	
			    	statusLabel.setText("Account Successfully Created");
					statusLabel.setTextFill(Color.GREEN);
					createAccountButton.setDisable(true);
			    	
				} catch (IOException e) {
					statusLabel.setText("Account Could Not Be Created: File Error");
					statusLabel.setTextFill(Color.RED);
				}
		    	
				
			}

		
		
		} //End handle
	} //End subclass
	
	//Calls the MedicalSystem to change the screen to the home page
		private class BackHandler implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent arg0) {
				MedicalSystem medSys = MedicalSystem.getInstance();
				medSys.toPatientLogin();
			} //End handle
		} //End subclass
		
} //End Class
