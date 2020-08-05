
public class Countries {

	String nameOfCountry;
	int population; 
	boolean isInWesternHemisphere;
	
	public Countries(String nameOfCountry, int population, boolean isInWesternHemisphere) {
		super();
		this.nameOfCountry = nameOfCountry;
		this.population = population;
		this.isInWesternHemisphere = isInWesternHemisphere;
	}
	public String getNameOfCountry() {
		return nameOfCountry;
	}
	
	public void setNameOfCountry(String nameOfCountry) {
		this.nameOfCountry = nameOfCountry;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public boolean isInWesternHemisphere() {
		return isInWesternHemisphere;
	}
	public void setInWesternHemisphere(boolean isInWesternHemisphere) {
		this.isInWesternHemisphere = isInWesternHemisphere;
	}
	
	@Override
	public String toString() {
		return "Countries: " + getNameOfCountry() + " Population: " + 
				getPopulation() + " Western Hemisphere? " + isInWesternHemisphere;
		
	}
	
	
	
}
