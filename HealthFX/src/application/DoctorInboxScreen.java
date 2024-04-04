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

public class DoctorInboxScreen extends BorderPane {
	private Doctor doctor;
	
	//Main Body
	private VBox bodyHolder;
	private Label titleLabel;
	private ArrayList<Button> messageList;
	
	
	//Right side
	private Button closeButton;
	private Button exitButton;
	private ImageView logoImageView;
	private BorderPane rightColumn;
	private HBox closeHolder;
	private HBox imageHolder;
	private HBox logoutHolder;
	
	
	public DoctorInboxScreen(Doctor doctor) {
		this.doctor = doctor;
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
		
		for (int i=0; i<3; i++) {
			Button patientButton = new Button("Patient " + i);
			patientButton.setFont(textFont);
			patientButton.setPrefSize(1920, 40);
			patientButton.setAlignment(Pos.CENTER_LEFT);
			bodyHolder.getChildren().add(patientButton);
		}
		
		
		
		
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
		
		this.setCenter(bodyHolder);
		this.setRight(rightColumn);
	}
	
}
