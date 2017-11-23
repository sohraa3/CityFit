package prototype;

public class setRates {
	private static int ageRate;
	private static int SexRatioRate;
	private static int PopulationRate;
	public static City[] setPoints(int rate1,int rate2, int rate3){
		ageRate=rate1;
		SexRatioRate=rate2;
		PopulationRate=rate3;
		City[] cityArray=City.create_capitalCities();
		for(City c: cityArray){
			c.setPoints(ageRate, SexRatioRate, PopulationRate);
			System.out.println(c.getName()+", "+c.getAgeMedian()+", "+c.getPopulation()+", "+c.getSexRatio()+", "+c.getPoints());
		}
		return cityArray;
	}
	public static void main (String[]args){
		setPoints(3,6,1);
	}
}
