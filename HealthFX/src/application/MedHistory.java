package application;

public class MedHistory {
	private String allergies;
	private String concerns;
	private String healthIssues;
	private String medications;
	private String immunizations;
	
	public MedHistory() {
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getConcerns() {
		return concerns;
	}

	public void setConcerns(String concerns) {
		this.concerns = concerns;
	}

	public String getHealthIssues() {
		return healthIssues;
	}

	public void setHealthIssues(String healthIssues) {
		this.healthIssues = healthIssues;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public String getImmunizations() {
		return immunizations;
	}

	public void setImmunizations(String immunizations) {
		this.immunizations = immunizations;
	}
	
	
}
