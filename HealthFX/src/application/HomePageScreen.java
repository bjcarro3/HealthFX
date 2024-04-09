package application;

import java.io.FileNotFoundException;

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
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class HomePageScreen extends VBox{
	private VBox titleHolder; //Holds title and prompt to align
	private HBox buttonHolder; //Holds buttons and logo to align
	private Label titleLabel;
	private Label promptLabel;
	private Button patientButton;
	private Button doctorButton;
	private ImageView logoImageView;
	
	public HomePageScreen() {
		//Create title
		titleLabel = new Label("Welcome!");
		titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 80));
		titleLabel.setTextFill(Color.rgb(132, 80, 139));
		
		//Create Prompt
		promptLabel = new Label("Who are you?");
		promptLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 36));
		
		//Place title and prompt into titleHolder and align
		titleHolder = new VBox();
		titleHolder.setAlignment(Pos.CENTER);
		titleHolder.getChildren().addAll(titleLabel, promptLabel);
		this.getChildren().add(titleHolder);
		
		//Patient Button
		patientButton = new Button("I Am A Patient");
		patientButton.setFont(Font.font("Verdana", 20));
		patientButton.setPrefWidth(270);
		patientButton.setPrefHeight(154);
		patientButton.setOnAction(new PatientHandler());
		
		//Logo
		Image logoImage = new Image("/assets/logo.png");
		logoImageView = new ImageView(logoImage);
		
		//Doctor Button
		doctorButton = new Button("I Am A Doctor");
		doctorButton.setFont(Font.font("Verdana", 20));
		doctorButton.setPrefWidth(270);
		doctorButton.setPrefHeight(154);
		doctorButton.setOnAction(new DoctorHandler());
		
		//Place buttons and logo into ButtonHolder to align
		buttonHolder = new HBox();
		buttonHolder.setAlignment(Pos.CENTER);
		buttonHolder.setSpacing(60);
		buttonHolder.getChildren().addAll(patientButton, logoImageView, doctorButton);
		this.getChildren().add(buttonHolder);
		
		//Set up this objects spacing and background
		this.setBackground(new Background(new BackgroundFill(Color.rgb(100, 187, 248), CornerRadii.EMPTY, Insets.EMPTY)));
		this.setSpacing(60);
	}
	
	
	//Handler for patientButton, calls MedicalSystem to change scene to patient login screen on click
	private class PatientHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = null;
			try {
				medSys = MedicalSystem.getInstance();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			medSys.toPatientLogin();
		} //End handle
	} //End subclass
	
	//Handler for doctorButton, calls MedicalSystem to change scene to doctor login screen on click
	private class DoctorHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = null;
			try {
				medSys = MedicalSystem.getInstance();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			medSys.toDoctorLogin();
		} //End handle
	} //End subclass
} //End Class
