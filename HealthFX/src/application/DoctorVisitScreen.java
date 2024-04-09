package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.FileNotFoundException;

public class DoctorVisitScreen extends DoctorView {
	private HBox centerHolder;
	private VBox leftHolder;
	private VBox rightHolder;
	
	//Left Side
	private Label titleLabel;
	//Vitals
	private GridPane vitalsHolder;
	private Label vitalsLabel;
	private Label weightLabel;
	private TextField weightField;
	private Label heightLabel;
	private TextField heightField;
	private Label tempLabel;
	private TextField tempField;
	private Label bpLabel;
	private TextField bpField;
	
	private VBox examResultsHolder;
	private Label examResultsLabel;
	private TextArea examResultsArea;
	
	//Right Side
	private VBox prescriptionHolder;
	private Label prescriptionLabel;
	private TextArea prescriptionArea;
	private HBox sendHolder;
	private Button sendButton;
	private VBox recommendationsHolder;
	private Label recommendationsLabel;
	private TextArea recommendationsArea;
	

	public DoctorVisitScreen(Doctor doctor, Patient patient) {
		super(doctor, patient);
		
		//Fonts
		Font titleFont = Font.font("Verdana", 25);
		Font textFont = Font.font("Verdana", 15);
				
		//Left Side
		//title
		titleLabel = new Label("Visit MM-DD-YYYY");
		titleLabel.setFont(titleFont);
		
		
		//Vitals
		vitalsLabel = new Label("Vitals-");
		vitalsLabel.setFont(textFont);
		
		//Weight
		weightLabel = new Label("Weight:");
		weightLabel.setFont(textFont);
		
		weightField = new TextField();
		weightField.setFont(textFont);
		weightField.setPrefWidth(100);
		
		//Height
		heightLabel = new Label("Height:");
		heightLabel.setFont(textFont);
		
		heightField = new TextField();
		heightField.setFont(textFont);
		heightField.setPrefWidth(100);
		
		//Temperature
		tempLabel = new Label("Temperature:");
		tempLabel.setFont(textFont);
		
		tempField = new TextField();
		tempField.setFont(textFont);
		tempField.setPrefWidth(100);
		
		//Blood Pressure
		bpLabel = new Label("Blood Pressure:");
		bpLabel.setFont(textFont);
		
		bpField = new TextField();
		bpField.setFont(textFont);
		bpField.setPrefWidth(100);
				
		//Set up Vitals Box
		vitalsHolder = new GridPane();
		vitalsHolder.add(vitalsLabel, 0, 0, 2, 1);
		vitalsHolder.add(weightLabel, 1, 1);
		vitalsHolder.add(weightField, 2, 1);
		vitalsHolder.add(heightLabel, 1, 2);
		vitalsHolder.add(heightField, 2, 2);
		vitalsHolder.add(tempLabel, 1, 3);
		vitalsHolder.add(tempField, 2, 3);
		vitalsHolder.add(bpLabel, 1, 4);
		vitalsHolder.add(bpField, 2, 4);
		
		vitalsHolder.setHgap(10);
		vitalsHolder.setVgap(20);
		
		//Exam Results
		examResultsLabel = new Label("Examination Results-");
		examResultsLabel.setFont(textFont);
		
		examResultsArea = new TextArea();
		examResultsArea.setFont(textFont);
		examResultsArea.setPrefSize(400, 300);
		
		examResultsHolder = new VBox(examResultsLabel, examResultsArea);
		examResultsHolder.setSpacing(10);
		
		//Set up Left Holder
		leftHolder = new VBox(titleLabel, vitalsHolder, examResultsHolder);
		leftHolder.setSpacing(40);
		
		
		
		//Right Side
		//New Prescriptions
		prescriptionLabel = new Label("New Prescriptions-");
		prescriptionLabel.setFont(textFont);
		
		prescriptionArea = new TextArea();
		prescriptionArea.setFont(textFont);
		prescriptionArea.setPrefSize(400, 200);
		
		sendButton = new Button("Send to Pharmacy");
		sendButton.setFont(textFont);
		sendHolder = new HBox(sendButton);
		sendHolder.setAlignment(Pos.CENTER_RIGHT);
		sendButton.setOnAction(new SendToPharmacyHandler());
		
		prescriptionHolder = new VBox(prescriptionLabel, prescriptionArea, sendHolder);
		prescriptionHolder.setSpacing(10);
		
		//Recommendations
		recommendationsLabel = new Label("Recommendation-");
		recommendationsLabel.setFont(textFont);
		
		recommendationsArea = new TextArea();
		recommendationsArea.setFont(textFont);
		recommendationsArea.setPrefSize(400, 200);
		
		recommendationsHolder = new VBox(recommendationsLabel, recommendationsArea);
		recommendationsHolder.setSpacing(10);
		
		
		rightHolder = new VBox(prescriptionHolder, recommendationsHolder);
		rightHolder.setSpacing(40);
		rightHolder.setPadding(new Insets(50, 0, 0, 0));
		
		
		
		centerHolder = new HBox(leftHolder, rightHolder);
		centerHolder.setSpacing(100);
		centerHolder.setPadding(new Insets(10, 10, 10, 10));
		
		this.setCenter(centerHolder);
	}
	
	private class SendToPharmacyHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			//currently just a placeholder
		} //End handle
	} //End subclass
	
}
