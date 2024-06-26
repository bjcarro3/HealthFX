//Group: Tu37
//Description: UI used by Doctors to view and edit a patient's medical history. Saves to the system when
//			   the screen is changed.

package application;

import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MedHistoryScreen extends DoctorView {
	private HBox centerHolder;
	private VBox leftHolder;
	private VBox rightHolder;
	
	//Left Side
	private Label titleLabel;
	private VBox allergiesHolder;
	private Label allergiesLabel;
	private TextArea allergiesArea;
	private VBox concernsHolder;
	private Label concernsLabel;
	private TextArea concernsArea;
	private VBox healthIssuesHolder;
	private Label healthIssuesLabel;
	private TextArea healthIssuesArea;
	
	//Right Side
	private VBox medicationsHolder;
	private Label medicationsLabel;
	private TextArea medicationsArea;
	private VBox immunizationsHolder;
	private Label immunizationsLabel;
	private TextArea immunizationsArea;
	
	
	
	

	public MedHistoryScreen(Doctor doctor, Patient patient) {
		super(doctor, patient);
		
		//Fonts
		Font titleFont = Font.font("Verdana", 25);
		Font textFont = Font.font("Verdana", 15);
		
		//Left Side
		//title
		titleLabel = new Label("Medical History");
		titleLabel.setFont(titleFont);
		
		//Allergies
		allergiesLabel = new Label("Allergies-");
		allergiesLabel.setFont(textFont);
		
		allergiesArea = new TextArea(patient.getMedHistory().getAllergies());
		allergiesArea.setFont(textFont);
		allergiesArea.setPrefSize(400, 300);
		
		allergiesHolder = new VBox(allergiesLabel, allergiesArea);
		allergiesHolder.setSpacing(10);
		
		//Concerns
		concernsLabel = new Label("Concerns-");
		concernsLabel.setFont(textFont);
		
		concernsArea = new TextArea(patient.getMedHistory().getConcerns());
		concernsArea.setFont(textFont);
		concernsArea.setPrefSize(400, 300);
		
		concernsHolder = new VBox(concernsLabel, concernsArea);
		concernsHolder.setSpacing(10);
		
		//Health Issues
		healthIssuesLabel = new Label("Health Issues-");
		healthIssuesLabel.setFont(textFont);
		
		healthIssuesArea = new TextArea(patient.getMedHistory().getHealthIssues());
		healthIssuesArea.setFont(textFont);
		healthIssuesArea.setPrefSize(400, 300);
		
		healthIssuesHolder = new VBox(healthIssuesLabel, healthIssuesArea);
		healthIssuesHolder.setSpacing(10);
		
		//Set up Left Holder
		leftHolder = new VBox(titleLabel, allergiesHolder, concernsHolder, healthIssuesHolder);
		leftHolder.setSpacing(20);
		
		
		//Right Side
		//Medications
		medicationsLabel = new Label("Medications-");
		medicationsLabel.setFont(textFont);
		
		medicationsArea = new TextArea(patient.getMedHistory().getMedications());
		medicationsArea.setFont(textFont);
		medicationsArea.setPrefSize(400, 200);
		
		medicationsHolder = new VBox(medicationsLabel, medicationsArea);
		medicationsHolder.setSpacing(10);
		
		//Immunizations
		immunizationsLabel = new Label("Immunizations-");
		immunizationsLabel.setFont(textFont);
		
		immunizationsArea = new TextArea(patient.getMedHistory().getImmunizations());
		immunizationsArea.setFont(textFont);
		immunizationsArea.setPrefSize(400, 200);
		
		immunizationsHolder = new VBox(immunizationsLabel, immunizationsArea);
		immunizationsHolder.setSpacing(10);
		
		//Set up Right Holder
		rightHolder = new VBox(medicationsHolder, immunizationsHolder);
		rightHolder.setSpacing(40);
		rightHolder.setPadding(new Insets(50, 0, 0, 0));
		
		
		centerHolder = new HBox(leftHolder, rightHolder);
		centerHolder.setSpacing(100);
		centerHolder.setPadding(new Insets(10, 10, 10, 10));
		
		this.setCenter(centerHolder);
	}
	
	
	//Update patient object, then use Medical System to save to file system
	protected void savePatient() {
		MedHistory medHistory = patient.getMedHistory();
		medHistory.setAllergies(allergiesArea.getText());
		medHistory.setConcerns(concernsArea.getText());
		medHistory.setHealthIssues(healthIssuesArea.getText());
		medHistory.setMedications(medicationsArea.getText());
		medHistory.setImmunizations(immunizationsArea.getText());
		MedicalSystem medSys = MedicalSystem.getInstance();
		try {
			medSys.saveMedicalHistory(patient);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
