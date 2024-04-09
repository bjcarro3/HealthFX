package application;
	
import java.io.File;
import java.io.FileWriter;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	private File Database;
	private File Patients;
	private File Doctors;
	private File Doctor1;
	
	private FileWriter writer;
	
	public void start(Stage primaryStage) {
		try {
			Database = new File("src/assets/Data");
			Patients = new File("src/assets/Data/Patients");
			Doctors = new File("src/assets/Data/Doctors");
			Doctor1 = new File("src/assets/Data/Doctors/Doc123");
			
			if (Database.exists()) { 
				System.out.println("Database already made"); 
			}
			else
			{
				Database.mkdir();
				System.out.println("Database has been created successfully"); 
			}
			if (Patients.exists()) { 
				System.out.println("Patients already made"); 
			}
			else
			{
				Patients.mkdir();
				System.out.println("Patients has been created successfully"); 
			}
			if (Doctors.exists()) { 
				System.out.println("Conversations already made"); 
			}
			else
			{
				Doctors.mkdir();
				System.out.println("Doctors has been created successfully"); 
			}
			if(Doctor1.exists()) {
				System.out.println("Doctor Account already made"); 
			}
			else {
				Doctor1.createNewFile();
				
				writer = new FileWriter(Doctor1);
				writer.write("Hello123\nHarvey");	
		    	writer.close();
		    	System.out.println("Doctor Account has been created"); 
			}
			
			MedicalSystem medSys = MedicalSystem.getInstance();
			HomePageScreen homePageScreen = new HomePageScreen();
			Scene scene = new Scene(homePageScreen,1280,720);
			medSys.setScene(scene);
			primaryStage.setScene(scene);
			primaryStage.setTitle("HealthFX Pediatric System");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
