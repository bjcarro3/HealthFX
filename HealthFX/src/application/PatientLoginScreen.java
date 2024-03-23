package application;
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

public class PatientLoginScreen extends BorderPane {
	private VBox titleHolder; //Holds title and prompt to align
	private VBox inputHolder; //Holds text fields and buttonHolder to align
	private HBox buttonHolder;
	private HBox backHolder;
	private ImageView logoImageView;
	private Label titleLabel;
	private Label promptLabel;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField birthdayField;
	private TextField passwordField;
	private Button createAccountButton;
	private Button loginButton;
	private Button backButton;
	
	
	public PatientLoginScreen() {
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
		promptLabel = new Label("Log In");
		promptLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 28));
		
		//Place title and prompt into titleHolder and align
		titleHolder = new VBox();
		titleHolder.setAlignment(Pos.CENTER);
		titleHolder.getChildren().addAll(logoImageView, titleLabel, promptLabel);
		
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
		
		//Create Buttons
		Font buttonFont = Font.font("Veranda", 20);
		createAccountButton = new Button("Create Account");
		createAccountButton.setPrefWidth(210);
		createAccountButton.setPrefHeight(50);
		createAccountButton.setFont(buttonFont);
		createAccountButton.setOnAction(new CreateAccountHandler());
		
		loginButton = new Button("Login");
		loginButton.setPrefWidth(210);
		loginButton.setPrefHeight(50);
		loginButton.setFont(buttonFont);
		loginButton.setOnAction(new LoginHandler());
		
		//Holder for buttons to display side by side
		buttonHolder = new HBox(createAccountButton, loginButton);
		buttonHolder.setSpacing(30);
		buttonHolder.setAlignment(Pos.CENTER);
		
		//Holder for all inputs
		inputHolder = new VBox(firstNameField, lastNameField, birthdayField, passwordField, buttonHolder);
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
		
		//Set up PatientLogin Pane
		this.setTop(titleHolder);
		this.setCenter(inputHolder);
		this.setBottom(backHolder);
		this.setBackground(new Background(new BackgroundFill(Color.rgb(100, 187, 248), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	private class CreateAccountHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			medSys.toCreateAccount();
		} //End handle
	} //End subclass
	
	private class LoginHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
		} //End handle
	} //End subclass
	
	//Calls the MedicalSystem to change the screen to the home page
	private class BackHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			MedicalSystem medSys = MedicalSystem.getInstance();
			medSys.toHomePage();
		} //End handle
	} //End subclass
} //End Class
