package application;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class MedicalSystem {
	private static MedicalSystem instance = null;
	private Scene scene;
	
	private MedicalSystem() {
		
	}
	
	public static MedicalSystem getInstance() {
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
	
	//Doctor UI
	public void toDoctorLogin() {
		setScreen(new DoctorLoginScreen());
	}
	
	public void toDoctorSearch() {
		setScreen(new DoctorSearchScreen());
	}
	
	private void setScreen(Parent screen) {
		scene.setRoot(screen);
		screen.requestFocus();
	}
}
