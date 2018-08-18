package Model;

public class City {
	
	private String Name;
	
	private double demandNumber = 0;
	
	private double currentPercantage = 0;
	
	public City(String Name, double demandNumber) {
		
		this.Name = Name;
		this.demandNumber = demandNumber;
	}

	public String getName() {
		return Name.toString();
	}

	public double getDemandNumber() {
		return demandNumber;
	}
	
	public double getCurrentPercantage() {
		return currentPercantage;
	}

	public void setCurrentPercantage(double currentPercantage) {
		this.currentPercantage = currentPercantage;
	}

	@Override
	public String toString() {
		return "City [Name=" + Name + ", demandNumber=" + demandNumber + "]";
	}
	
}
