//Group: Tu37
//Description: Singleton system object used to create and navigate to new UIs and to handle saving and loading
//			   Patients and their information from the file system

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
	
	public void toPatientVisitScreen(Patient patient, int index) {
		//need to work on this later
		setScreen(new PatientVisitScreen(patient, index));
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
	
	public void toDoctorInboxScreen(Doctor doctor) {
		setScreen(new DoctorInboxScreen(doctor));
	}
	
	public void toDoctorConversationScreen(Doctor doctor, Patient patient) {
		setScreen(new DoctorConversationScreen(doctor, patient));
	}
	
	public void toDoctorVisitScreen(Doctor doctor, Patient patient, int index) {
		setScreen(new DoctorVisitScreen(doctor, patient, index));
	}
	
	public void toMedHistoryScreen(Doctor doctor, Patient patient) {
		setScreen(new MedHistoryScreen(doctor, patient));
	}
	
	
	private void setScreen(Parent screen) {
		scene.setRoot(screen);
		screen.requestFocus();
	}
	
	public void toDoctorView(Doctor doctor, Patient patient){
		setScreen(new DoctorView(doctor, patient));
	}
	
	public void toPatientView(Patient patient) {
		setScreen(new PatientView(patient));
	}
	
	//Write patient info to file
	public void savePatientInfo(Patient patient) throws FileNotFoundException {
		PatientInfo pInfo = patient.getPatientInfo();
		String fileName = "src/assets/patientinformation/" + patient.getFirstName() + patient.getLastName() + ".txt";
		PrintWriter pw = new PrintWriter(fileName);
		pw.println(pInfo.getSex());
		pw.println(pInfo.getAddress());
		pw.println(pInfo.getPhoneNumber());
		pw.println(pInfo.getEmail());
		pw.println(pInfo.getGuardianName());
		pw.println(pInfo.getGuardianEmail());
		pw.println(pInfo.getGuardianPhone());
		pw.println(pInfo.getEmergencyName());
		pw.println(pInfo.getEmergencyPhone());
		pw.println(pInfo.getInsuranceInfo());
		pw.println(pInfo.getPharmacyName());
		pw.println(pInfo.getPharmacyAddress());
		pw.close();
	}
	
	//Write Medical History to file
	public void saveMedicalHistory(Patient patient) throws FileNotFoundException {
		MedHistory medHistory = patient.getMedHistory();
		String fileName = "src/assets/medHistory/" + patient.getFirstName() + patient.getLastName() + ".txt";
		PrintWriter pw = new PrintWriter(fileName);
		pw.println(medHistory.getAllergies());
		pw.println(medHistory.getConcerns());
		pw.println(medHistory.getHealthIssues());
		pw.println(medHistory.getMedications());
		pw.println(medHistory.getImmunizations());
		pw.close();
	}
	
	//Save appointment to file system
	public void saveAppointment(Patient patient, Appointment appt, int visitNum) throws FileNotFoundException {
		String fileName;
		if (visitNum == 0) {
			fileName = "src/assets/appointments/" + patient.getFirstName() + patient.getLastName() + ".txt";
		} else {
			fileName = "src/assets/appointments/" + patient.getFirstName() + patient.getLastName() + visitNum + ".txt";
		}
		PrintWriter pw = new PrintWriter(fileName);
		pw.println(appt.getDate());
		pw.println(appt.getHeight());
		pw.println(appt.getWeight());
		pw.println(appt.getTemperature());
		pw.println(appt.getBp());
		pw.println(appt.getPrescriptions());
		pw.println(appt.getExamResults());
		pw.println(appt.getRecommendations());
		pw.close();
	}
	
	//Create a patient object based on information in the system and return it
	public Patient getPatient(String fullName, String inputBirthday, String inputPass) throws FileNotFoundException {
		String fileName = "src/assets/patients/" + fullName + ".txt";
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}
		Scanner scan = new Scanner(file);
        
		String firstName = scan.nextLine();
		String lastName = scan.nextLine();
		String birthday = scan.nextLine();
		String password = scan.nextLine();
		scan.close();
		if (!inputPass.equals(password) || !inputBirthday.equals(birthday)) {
			return null;
		}
		
		PatientInfo pInfo = new PatientInfo();
		fileName = "src/assets/patientinformation/" + fullName + ".txt";
		file = new File(fileName);
		if (file.exists()) {
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
		} 
		
		MedHistory medHistory = new MedHistory();
		fileName = "src/assets/medHistory/" + fullName + ".txt";
		file = new File(fileName);
		if (file.exists()) {
			scan = new Scanner(file);
			medHistory.setAllergies(scan.nextLine());
			medHistory.setConcerns(scan.nextLine());
			medHistory.setHealthIssues(scan.nextLine());
			medHistory.setMedications(scan.nextLine());
			medHistory.setImmunizations(scan.nextLine());
			scan.close();
		}
		
		
		Patient patient = new Patient(firstName, lastName, birthday, pInfo, medHistory);
		return patient;
	}
	
	//Overloaded in order to get the patient without verifying the password (for doctors)
	public Patient getPatient(String fullName, String inputBirthday) throws FileNotFoundException {
		String fileName = "src/assets/patients/" + fullName + ".txt";
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}
		Scanner scan = new Scanner(file);
        
		String firstName = scan.nextLine();
		String lastName = scan.nextLine();
		String birthday = scan.nextLine();
		scan.close();
		
		PatientInfo pInfo = new PatientInfo();
		fileName = "src/assets/patientinformation/" + fullName + ".txt";
		file = new File(fileName);
		if (file.exists()) {
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
		} 
		
		MedHistory medHistory = new MedHistory();
		fileName = "src/assets/medHistory/" + fullName + ".txt";
		file = new File(fileName);
		if (file.exists()) {
			scan = new Scanner(file);
			medHistory.setAllergies(scan.nextLine());
			medHistory.setConcerns(scan.nextLine());
			medHistory.setHealthIssues(scan.nextLine());
			medHistory.setMedications(scan.nextLine());
			medHistory.setImmunizations(scan.nextLine());
			scan.close();
		}
		
		Patient patient = new Patient(firstName, lastName, birthday, pInfo, medHistory);
		return patient;
	}
	
	//Gets a specific visit form the file system and returns it in an Appointment object
	public Appointment getAppointment(String fullName, int visitNum) throws FileNotFoundException {
		String fileName;
		if (visitNum == 0) {
			fileName = "src/assets/appointments/" + fullName + ".txt";
		} else {
			fileName = "src/assets/appointments/" + fullName + visitNum + ".txt";
		}
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}
		Scanner scan = new Scanner(file);
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
		
		return pApt;
	}
	
	public Conversation getConversation(String fullName)
	{
		ArrayList<String> Messages = new ArrayList<String>();
		Conversation returnedConvo = new Conversation(Messages);
		File convoFile = new File("src/assets/conversations/" + fullName + ".txt");
		Scanner scanConvo;
		if(!convoFile.exists())
		{
			try {
				convoFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			scanConvo = new Scanner(convoFile);
			String nextMessage;
			
			while(scanConvo.hasNextLine()){
				nextMessage = scanConvo.nextLine();
				returnedConvo.addMessage(nextMessage);			
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return returnedConvo;
	}
	
	public void saveConversation(String fullName, Conversation saved) {
		File convoFile = new File("src/assets/conversations/" + fullName + ".txt");
		if(!convoFile.exists())
		{
			try {
				convoFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter writer = new FileWriter(convoFile);
			for(int i = 0; i < saved.getMessages().size(); i++) {
				writer.write(saved.getMessages().get(i) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

}
}

