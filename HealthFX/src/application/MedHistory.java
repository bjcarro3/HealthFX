//Group: Tu37
//Description: Part of a Patient object. Store information related to a Patient's previous medical history

package application;

public class MedHistory {
	private String allergies;
	private String concerns;
	private String healthIssues;
	private String medications;
	private String immunizations;
	
	public MedHistory() {
	}
	
	public String writeMedicalHistory() {
		return (getAllergies() + "\n" + getConcerns() + "\n" + getHealthIssues() + "\n" + getMedications() + "\n" + getImmunizations());
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
