package application;

import java.util.ArrayList;

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
	private ArrayList<Button> visitButtons;
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
		patientName = new Label("John Smith");
		patientName.setFont(nameFont);
		addVisitButton = new Button("+");
		addVisitButton.setFont(addFont);
		addVisitButton.setPrefSize(50, 50);
		nameHolder = new HBox(patientName, addVisitButton);
		nameHolder.setAlignment(Pos.CENTER);
		nameHolder.setSpacing(20);
		leftColumn.getChildren().addAll(nameHolder);
		
		contactButton = new Button("Contact Information");
		contactButton.setFont(buttonFont);
		contactHolder = new HBox(contactButton);
		contactHolder.setAlignment(Pos.CENTER);
		leftColumn.getChildren().add(contactHolder);
		
		medHistoryButton = new Button("Medical History");
		medHistoryButton.setFont(buttonFont);
		medHistoryHolder = new HBox(medHistoryButton);
		medHistoryHolder.setAlignment(Pos.CENTER);
		leftColumn.getChildren().add(medHistoryHolder);
		
		for (int i=0; i<3; i++) {
			Button visitButton = new Button("Visit " + i);
			visitButton.setFont(buttonFont);
			VBox buttonHolder = new VBox(visitButton);
			buttonHolder.setAlignment(Pos.CENTER);
			leftColumn.getChildren().add(buttonHolder);
		}
				
				
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
		
				
		this.setLeft(leftColumn);
		this.setRight(rightColumn);		
		
	}
}
