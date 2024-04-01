package application;

public class Appointment {
	private String date;
	private float height;
	private float weight;
	private float temperature;
	private String bp;
	private String prescriptions;
	private String examResults;
	private String recommendations;
	
	public Appointment(String date) {
		this.date = date;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public String getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
	}

	public String getExamResults() {
		return examResults;
	}

	public void setExamResults(String examResults) {
		this.examResults = examResults;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	public String getDate() {
		return date;
	}
	
	
}
