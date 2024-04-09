package application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PatientVisitScreen extends PatientView {
	private VBox centerHolder;
	private Label visitTitle;
	private TextArea visitText;

	public PatientVisitScreen(Patient patient, int index) {
		super(patient);
		
		Font titleFont = Font.font("Verdana", 25);
		Font summaryFont = Font.font("Verdana", 15);
		
		String VisitLabel = "Visit "  + index + "-" + index + index + "-" + index + index + index + index;
		
		visitTitle = new Label(VisitLabel);
		visitTitle.setFont(titleFont);
		
		visitText = new TextArea("KJSAFKLAJSFLFSKASFJKLS");
		visitText.setEditable(false);
		visitText.setFont(summaryFont);
		visitText.setPrefHeight(1080);
		
		centerHolder = new VBox(visitTitle, visitText);
		centerHolder.setSpacing(20);
		centerHolder.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		centerHolder.setPadding(new Insets(10, 10, 10, 10));
		
		this.setCenter(centerHolder);
		
		
	}

}
