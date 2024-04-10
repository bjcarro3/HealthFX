package application;

import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DoctorPatientInfoScreen extends DoctorView {
	private HBox centerHolder;
	private VBox leftHolder;
	private GridPane patientInfoHolder;
	private GridPane emergencyHolder;
	private VBox rightHolder;
	private GridPane pharmacyHolder;
	
	//Left Side
	private Label titleLabel;
	private Label firstNameLabel;
	private Label lastNameLabel;
	private Label sexLabel;
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
	private TextField sexField;
	private TextField dobField;
	private TextField addressField;
	private TextField phoneField;
	private TextField emailField;
	private TextField guardianNameField;
	private TextField guardianEmailField;
	private TextField guardianPhoneField;
	private TextField emergencyNameField;
	private TextField emergencyPhoneField;
	
	//Right Side
	private Label insuranceLabel;
	private TextArea insuranceArea;
	private Label pharmacyTitleLabel;
	private Label pharmacyNameLabel;
	private TextField pharmacyNameField;
	private Label pharmacyAddressLabel;
	private TextField pharmacyAddressField;
	
	
	public DoctorPatientInfoScreen(Doctor doctor, Patient patient) {
		super(doctor, patient);
		
		
		Font titleFont = Font.font("Verdana", 25);
		Font textFont = Font.font("Verdana", 15);
		
		
		//Left side
		
		//title
		titleLabel = new Label("Patient Information");
		titleLabel.setFont(titleFont);
		
		//Patient Info (Top part)
		//First Name
		firstNameLabel = new Label("First Name:");
		firstNameLabel.setFont(textFont);
		
		firstNameField = new TextField(patient.getFirstName());
		firstNameField.setFont(textFont);
		firstNameField.setEditable(false);
		firstNameField.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		firstNameField.setPrefWidth(300);
		
		//Last Name
		lastNameLabel = new Label("Last Name:");
		lastNameLabel.setFont(textFont);
		
		lastNameField = new TextField(patient.getLastName());
		lastNameField.setFont(textFont);
		lastNameField.setEditable(false);
		lastNameField.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//Sex
		sexLabel = new Label("Sex:");
		sexLabel.setFont(textFont);
		
		sexField = new TextField(patient.getPatientInfo().getSex());
		sexField.setFont(textFont);
		
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
		patientInfoHolder.add(sexLabel, 0, 2);
		patientInfoHolder.add(sexField, 1, 2);
		patientInfoHolder.add(dobLabel, 0, 3);
		patientInfoHolder.add(dobField, 1, 3);
		patientInfoHolder.add(addressLabel, 0, 4);
		patientInfoHolder.add(addressField, 1, 4);
		patientInfoHolder.add(phoneLabel, 0, 5);
		patientInfoHolder.add(phoneField, 1, 5);
		patientInfoHolder.add(emailLabel, 0, 6);
		patientInfoHolder.add(emailField, 1, 6);
		patientInfoHolder.add(guardianNameLabel, 0, 7);
		patientInfoHolder.add(guardianNameField, 1, 7);
		patientInfoHolder.add(guardianEmailLabel, 0, 8);
		patientInfoHolder.add(guardianEmailField, 1, 8);
		patientInfoHolder.add(guardianPhoneLabel, 0, 9);
		patientInfoHolder.add(guardianPhoneField, 1, 9);
		
		
		emergencyTitleLabel = new Label("Emergency Contact-");
		emergencyTitleLabel.setFont(textFont);
		
		//Emergency Contact Holder
		//Contact's Name
		emergencyNameLabel = new Label("Contact's Name:");
		emergencyNameLabel.setFont(textFont);
		
		emergencyNameField = new TextField(patient.getPatientInfo().getEmergencyName());
		emergencyNameField.setFont(textFont);
		emergencyNameField.setPrefWidth(300);
		
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
		
		//Left Holder
		leftHolder = new VBox(titleLabel, patientInfoHolder, emergencyTitleLabel, emergencyHolder);
		leftHolder.setSpacing(20);
		
		
		//Right Side
		//Insurance
		insuranceLabel = new Label("Insurance Information");
		insuranceLabel.setFont(textFont);
		
		insuranceArea = new TextArea(patient.getPatientInfo().getInsuranceInfo());
		insuranceArea.setFont(textFont);
		insuranceArea.setPrefWidth(300);
		
		//Pharmacy Info
		pharmacyTitleLabel = new Label("Pharmacy Information");
		pharmacyTitleLabel.setFont(textFont);
		
		pharmacyNameLabel = new Label("Name");
		pharmacyNameLabel.setFont(textFont);
		
		pharmacyNameField = new TextField(patient.getPatientInfo().getPharmacyName());
		pharmacyNameField.setFont(textFont);
		
		pharmacyAddressLabel = new Label("Address");
		pharmacyAddressLabel.setFont(textFont);
		
		pharmacyAddressField = new TextField(patient.getPatientInfo().getPharmacyAddress());
		pharmacyAddressField.setFont(textFont);
		
		//Pharmacy Field Aligner
		pharmacyHolder = new GridPane();
		pharmacyHolder.setHgap(20);
		pharmacyHolder.setVgap(15);
		
		pharmacyHolder.add(pharmacyTitleLabel, 0, 0, 2, 1);
		pharmacyHolder.add(pharmacyNameLabel, 0, 1);
		pharmacyHolder.add(pharmacyNameField, 1, 1);
		pharmacyHolder.add(pharmacyAddressLabel, 0, 2);
		pharmacyHolder.add(pharmacyAddressField, 1, 2);
		
		//Right Holder
		rightHolder = new VBox(insuranceLabel, insuranceArea, pharmacyHolder);
		rightHolder.setSpacing(40);
		rightHolder.setPadding(new Insets(50, 0, 0, 0));
		
		
		//Center Holder
		centerHolder = new HBox(leftHolder, rightHolder);
		centerHolder.setSpacing(100);
		centerHolder.setPadding(new Insets(10, 10, 10, 10));
		
		this.setCenter(centerHolder);
		
		
	}
	
	protected void savePatient() {
		PatientInfo pInfo = patient.getPatientInfo();
		pInfo.setSex(sexField.getText());
		pInfo.setAddress(addressField.getText());
		pInfo.setEmail(emailField.getText());
		pInfo.setPhoneNumber(phoneField.getText());
		pInfo.setGuardianName(guardianNameField.getText());
		pInfo.setGuardianEmail(guardianEmailField.getText());
		pInfo.setGuardianPhone(guardianPhoneField.getText());
		pInfo.setEmergencyName(emergencyNameField.getText());
		pInfo.setEmergencyPhone(emergencyPhoneField.getText());
		pInfo.setInsuranceInfo(insuranceArea.getText());
		pInfo.setPharmacyName(pharmacyNameField.getText());
		pInfo.setPharmacyAddress(pharmacyAddressField.getText());
		MedicalSystem medSys = null;
		try {
			medSys = MedicalSystem.getInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			medSys.savePatientInfo(patient);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
