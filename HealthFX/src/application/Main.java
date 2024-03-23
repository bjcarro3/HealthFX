package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
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
