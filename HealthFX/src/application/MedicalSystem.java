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
	private static ArrayList<String> allPatients = new ArrayList<>();
	
	private MedicalSystem() throws FileNotFoundException {
		// instantiate all Patients
		String fileName = "src/assets/PatientList.txt";
		File file = new File(fileName);
        Scanner scan = new Scanner(file);
        //String line = scan.nextLine();
        //System.out.println("Line: " + line);
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
}
