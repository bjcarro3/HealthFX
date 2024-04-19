//Group: Tu37
//Description: UI used by doctors to pull up a Patient's records by their name and birthday. Creates a
//			   Patient object to keep track of this patient used in next UIs

package application;

import java.io.FileNotFoundException;

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

public class DoctorSearchScreen extends BorderPane {
	private Doctor doctor;
	
	private VBox messageHolder; //Holds messageButton to align
	private VBox titleHolder; //Holds title and prompt to align
	private VBox inputHolder; 
	private HBox logoutHolder;
	private Button messageButton;
	private ImageView logoImageView;
	private Label titleLabel;
	private Label promptLabel;
	private Label statusLabel;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField birthdayField;
	private Button getRecordsButton;
	private Button logoutButton;
	
	public DoctorSearchScreen(Doctor doctor) {
		this.doctor = doctor;
		//Messages Button
		messageButton = new Button("Messages");
		messageButton.setPrefWidth(100);
		messageButton.setPrefHeight(80);
		messageButton.setOnAction(new MessagesHandler());
		
		messageHolder = new VBox(messageButton);
		messageHolder.setAlignment(Pos.TOP_RIGHT);
		
		
		//Logo
		Image logoImage = new Image("/assets/logo.png");
		logoImageView = new ImageView(logoImage);
		logoImageView.setFitWidth(150);
		logoImageView.setFitHeight(150);
				
		//Create title
		titleLabel = new Label("Staff Portal");
		titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 60));
		titleLabel.setTextFill(Color.rgb(132, 80, 139));
		
		//Create Prompt
		promptLabel = new Label("Patient Records");
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
				
		//Get Records Button
		Font buttonFont = Font.font("Veranda", 20);
		getRecordsButton = new Button("Get Records");
		getRecordsButton.setPrefWidth(210);
		getRecordsButton.setPrefHeight(50);
		getRecordsButton.setFont(buttonFont);
		getRecordsButton.setOnAction(new GetRecordsHandler());
		
		//Holder for all inputs
		inputHolder = new VBox(firstNameField, lastNameField, birthdayField, getRecordsButton);
		inputHolder.setSpacing(20);
		inputHolder.setAlignment(Pos.CENTER);
		
		//Log out button
		logoutButton = new Button("Log Out");
		logoutButton.setPrefWidth(100);
		logoutButton.setPrefHeight(80);
		logoutButton.setOnAction(new LogoutHandler());
		
		//Holder for back button to align it to bottom right
		logoutHolder = new HBox(logoutButton);
		logoutHolder.setAlignment(Pos.BOTTOM_RIGHT);
		
		//Set up DoctorSearch Pane
		this.setTop(titleHolder);
		this.setRight(messageHolder);
		this.setCenter(inputHolder);
		this.setBottom(logoutHolder);
		this.setBackground(new Background(new BackgroundFill(Color.rgb(100, 187, 248), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	
	private class MessagesHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			medSys.toDoctorInboxScreen(doctor);
		} //End handle
	} //End subclass
	
	
	//Gets the records of patient when button is clicked an bring up UI for that patient
	private class GetRecordsHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			if (firstNameField.getText().isBlank() || lastNameField.getText().isBlank() || birthdayField.getText().isBlank()) {
				statusLabel.setText("Empty Fields");
				statusLabel.setTextFill(Color.RED);
			} else {
				String fullName = firstNameField.getText() + lastNameField.getText();
				MedicalSystem medSys;
				Patient patient;
				try {
					medSys = MedicalSystem.getInstance();
					patient = medSys.getPatient(fullName, birthdayField.getText());
					if (patient == null) {
						statusLabel.setText("Patient Not Fount");
						statusLabel.setTextFill(Color.RED);
					} else {
						medSys.toDoctorView(doctor, patient);
					}
				} catch (FileNotFoundException e) {
					statusLabel.setText("Patient Not Found");
					statusLabel.setTextFill(Color.RED);
				}
			}
			
		} //End handle
	} //End subclass
	
	
	//Calls the MedicalSystem to change the screen to the Doctor Login page
		private class LogoutHandler implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent arg0) {
				MedicalSystem medSys = MedicalSystem.getInstance();
				medSys.toDoctorLogin();
			} //End handle
		} //End subclass
}
