package application;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class MedicalSystem {
	private static MedicalSystem instance = null;
	private Scene scene;
	public ArrayList<String> allPatients = new ArrayList<>();
	
	private MedicalSystem() throws FileNotFoundException {
		// instantiate all Patients
		String fileName = "src/assets/PatientList.txt";
		File file = new File(fileName);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
        	String line = scan.nextLine();
        	if (line == "")
        		break;
        	allPatients.add(line);
        }
        scan.close();
	}
	
	public static MedicalSystem getInstance() throws FileNotFoundException {
		if(instance != null) {
			return instance;
		}
		else {
			instance = new MedicalSystem();
			return instance;
		}
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void toHomePage() {
		setScreen(new HomePageScreen());
	}
	
	//Patient UI
	public void toPatientLogin() {
		setScreen(new PatientLoginScreen());
	}
	
	public void toCreateAccount() {
		setScreen(new CreateAccountScreen());
	}
	
	public void toPatientInfoScreen(Patient patient) {
		setScreen(new PatientInfoScreen(patient));
	}
	
	public void toPatientMessageScreen(Patient patient) {
		setScreen(new PatientConversationScreen(patient));
	}
	
	public void toPatientVisitScreen(Patient patient, int visitNumber) {
		//need to work on this later
		setScreen(new PatientVisitScreen(patient));
	}
	
	//Doctor UI
	public void toDoctorLogin() {
		setScreen(new DoctorLoginScreen());
	}
	
	public void toDoctorSearch(Doctor doctor) {
		setScreen(new DoctorSearchScreen(doctor));
	}
	
	public void toDoctorPatientInfoScreen(Doctor doctor, Patient patient) {
		setScreen(new DoctorPatientInfoScreen(doctor, patient));
	}
	
	
	
	private void setScreen(Parent screen) {
		scene.setRoot(screen);
		screen.requestFocus();
	}
	
	
	public Patient getPatient(String fullName, String inputPass) throws FileNotFoundException {
		String fileName = "src/assets/patients/" + fullName + ".txt";
		File file = new File(fileName);
		Scanner scan = new Scanner(file);
        
		String firstName = scan.nextLine();
		String lastName = scan.nextLine();
		String birthday = scan.nextLine();
		String password = scan.nextLine();
		scan.close();
		if (inputPass != password) {
			return null;
		}
		
		PatientInfo pInfo = new PatientInfo();
		fileName = "src/assets/patientinformation/" + fullName + ".txt";
		file = new File(fileName);
		scan = new Scanner(file);
		pInfo.setSex(scan.nextLine());
		pInfo.setAddress(scan.nextLine());
		pInfo.setPhoneNumber(scan.nextLine());
		pInfo.setEmail(scan.nextLine());
		pInfo.setGuardianName(scan.nextLine());
		pInfo.setGuardianEmail(scan.nextLine());
		pInfo.setGuardianPhone(scan.nextLine());
		pInfo.setEmergencyName(scan.nextLine());
		pInfo.setEmergencyPhone(scan.nextLine());
		pInfo.setInsuranceInfo(scan.nextLine());
		pInfo.setPharmacyName(scan.nextLine());
		pInfo.setPharmacyAddress(scan.nextLine());
		scan.close();
		
		fileName = "src/assets/medHistory/" + fullName + ".txt";
		file = new File(fileName);
		scan = new Scanner(file);
		MedHistory medHistory = new MedHistory();
		medHistory.setAllergies(scan.nextLine());
		medHistory.setConcerns(scan.nextLine());
		medHistory.setHealthIssues(scan.nextLine());
		medHistory.setMedications(scan.nextLine());
		medHistory.setImmunizations(scan.nextLine());
		scan.close();
		
		// ignore this huge block of text. made it by mistake :( maybe we will need it somewhere else
		/*
		fileName = "src/assets/appointments/" + fullName + ".txt";
		file = new File(fileName);
		scan = new Scanner(file);
		String date = scan.nextLine();
		Appointment pApt = new Appointment(date);
		if (scan.hasNext()) {	
			pApt.setHeight(Float.parseFloat(scan.nextLine()));
			pApt.setWeight(Float.parseFloat(scan.nextLine()));
			pApt.setTemperature(Float.parseFloat(scan.nextLine()));
			pApt.setBp(scan.nextLine());
			pApt.setPrescriptions(scan.nextLine());
			pApt.setExamResults(scan.nextLine());
			pApt.setRecommendations(scan.nextLine());
		}
		scan.close();
		
		fileName = "src/assets/conversations/" + fullName + ".txt";
		file = new File(fileName);
		scan = new Scanner(file);
		String pName = scan.nextLine();
		String dName = scan.nextLine();
		ArrayList<String> messages = new ArrayList<>();
		while (scan.hasNext()) {
			messages.add(scan.nextLine());
		}
		scan.close();
		Conversation conversation = new Conversation(pName, dName, messages);
		*/
		
		Patient patient = new Patient(firstName, lastName, birthday, pInfo, medHistory);
		return patient;
	}
}
