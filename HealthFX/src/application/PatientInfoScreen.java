package application;

import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PatientInfoScreen extends PatientView {
	private VBox centerHolder;
	private GridPane patientInfoHolder;
	private GridPane emergencyHolder;
	
	private Label titleLabel;
	private Label firstNameLabel;
	private Label lastNameLabel;
	private Label dobLabel;
	private Label addressLabel;
	private Label phoneLabel;
	private Label emailLabel;
	private Label guardianNameLabel;
	private Label guardianEmailLabel;
	private Label guardianPhoneLabel;
	private Label emergencyTitleLabel;
	private Label emergencyNameLabel;
	private Label emergencyPhoneLabel;
	
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField dobField;
	private TextField addressField;
	private TextField phoneField;
	private TextField emailField;
	private TextField guardianNameField;
	private TextField guardianEmailField;
	private TextField guardianPhoneField;
	private TextField emergencyNameField;
	private TextField emergencyPhoneField;
	
	

	public PatientInfoScreen(Patient patient) {
		super(patient);

		
		Font titleFont = Font.font("Verdana", 25);
		Font textFont = Font.font("Verdana", 15);
		
		//title
		titleLabel = new Label("Contact Information");
		titleLabel.setFont(titleFont);
		
		//Patient Info (Top part)
		//First Name
		firstNameLabel = new Label("First Name:");
		firstNameLabel.setFont(textFont);
		
		firstNameField = new TextField(patient.getFirstName());
		firstNameField.setFont(textFont);
		firstNameField.setEditable(false);
		firstNameField.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		firstNameField.setPrefWidth(400);
		
		//Last Name
		lastNameLabel = new Label("Last Name:");
		lastNameLabel.setFont(textFont);
		
		lastNameField = new TextField(patient.getLastName());
		lastNameField.setFont(textFont);
		lastNameField.setEditable(false);
		lastNameField.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//DOB
		dobLabel = new Label("D.O.B:");
		dobLabel.setFont(textFont);
		
		dobField = new TextField(patient.getBirthday());
		dobField.setFont(textFont);
		dobField.setEditable(false);
		dobField.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
	
		//Address
		addressLabel = new Label("Address:");
		addressLabel.setFont(textFont);
		
		addressField = new TextField(patient.getPatientInfo().getAddress());
		addressField.setFont(textFont);
	
		//Phone
		phoneLabel = new Label("Phone:");
		phoneLabel.setFont(textFont);
		
		phoneField = new TextField(patient.getPatientInfo().getPhoneNumber());
		phoneField.setFont(textFont);
		
		//Email
		emailLabel = new Label("Email:");
		emailLabel.setFont(textFont);
		
		emailField = new TextField(patient.getPatientInfo().getEmail());
		emailField.setFont(textFont);
		
		//Guardian Name
		guardianNameLabel = new Label("Guardian Name:");
		guardianNameLabel.setFont(textFont);
		
		guardianNameField = new TextField(patient.getPatientInfo().getGuardianName());
		guardianNameField.setFont(textFont);
		
		//Guardian Email
		guardianEmailLabel = new Label("Guardian Email:");
		guardianEmailLabel.setFont(textFont);
		
		guardianEmailField = new TextField(patient.getPatientInfo().getGuardianEmail());
		guardianEmailField.setFont(textFont);
		
		//Guardian Phone
		guardianPhoneLabel = new Label("Guardian Phone:");
		guardianPhoneLabel.setFont(textFont);
		
		guardianPhoneField = new TextField(patient.getPatientInfo().getGuardianPhone());
		guardianPhoneField.setFont(textFont);
		
		//Set Up GridPane
		patientInfoHolder = new GridPane();
		patientInfoHolder.setHgap(20);
		patientInfoHolder.setVgap(15);
		patientInfoHolder.setPadding(new Insets(0, 0, 0, 20));
		
		patientInfoHolder.add(firstNameLabel, 0, 0);
		patientInfoHolder.add(firstNameField, 1, 0);
		patientInfoHolder.add(lastNameLabel, 0, 1);
		patientInfoHolder.add(lastNameField, 1, 1);
		patientInfoHolder.add(dobLabel, 0, 2);
		patientInfoHolder.add(dobField, 1, 2);
		patientInfoHolder.add(addressLabel, 0, 3);
		patientInfoHolder.add(addressField, 1, 3);
		patientInfoHolder.add(phoneLabel, 0, 4);
		patientInfoHolder.add(phoneField, 1, 4);
		patientInfoHolder.add(emailLabel, 0, 5);
		patientInfoHolder.add(emailField, 1, 5);
		patientInfoHolder.add(guardianNameLabel, 0, 6);
		patientInfoHolder.add(guardianNameField, 1, 6);
		patientInfoHolder.add(guardianEmailLabel, 0, 7);
		patientInfoHolder.add(guardianEmailField, 1, 7);
		patientInfoHolder.add(guardianPhoneLabel, 0, 8);
		patientInfoHolder.add(guardianPhoneField, 1, 8);
		
		
		emergencyTitleLabel = new Label("Emergency Contact-");
		emergencyTitleLabel.setFont(textFont);
		
		//Emergency Contact Holder
		//Contact's Name
		emergencyNameLabel = new Label("Contact's Name:");
		emergencyNameLabel.setFont(textFont);
		
		emergencyNameField = new TextField(patient.getPatientInfo().getEmergencyName());
		emergencyNameField.setFont(textFont);
		emergencyNameField.setPrefWidth(400);
		
		//Contact's Phone
		emergencyPhoneLabel = new Label("Contact's Phone:");
		emergencyPhoneLabel.setFont(textFont);
		
		emergencyPhoneField = new TextField(patient.getPatientInfo().getEmergencyPhone());
		emergencyPhoneField.setFont(textFont);
		
		//Set up GridPane
		emergencyHolder = new GridPane();
		emergencyHolder.setHgap(20);
		emergencyHolder.setVgap(15);
		emergencyHolder.setPadding(new Insets(0, 0, 0, 20));
		
		emergencyHolder.add(emergencyNameLabel, 0, 0);
		emergencyHolder.add(emergencyNameField, 1, 0);
		emergencyHolder.add(emergencyPhoneLabel, 0, 1);
		emergencyHolder.add(emergencyPhoneField, 1, 1);
		
		
		//Center Holder
		centerHolder = new VBox(titleLabel, patientInfoHolder, emergencyTitleLabel, emergencyHolder);
		centerHolder.setSpacing(20);
		centerHolder.setPadding(new Insets(10, 10, 10, 10));
		
		this.setCenter(centerHolder);
		
		
	
	}
	
	
	protected void savePatient() {
		PatientInfo pInfo = patient.getPatientInfo();
		pInfo.setAddress(addressField.getText());
		pInfo.setEmail(emailField.getText());
		pInfo.setPhoneNumber(phoneField.getText());
		pInfo.setGuardianName(guardianNameField.getText());
		pInfo.setGuardianEmail(guardianEmailField.getText());
		pInfo.setGuardianPhone(guardianPhoneField.getText());
		pInfo.setEmergencyName(emergencyNameField.getText());
		pInfo.setEmergencyPhone(emergencyPhoneField.getText());
		MedicalSystem medSys = MedicalSystem.getInstance();
		try {
			medSys.savePatientInfo(patient);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
