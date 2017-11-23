
package prototype;

/**
 * This is an ADT for Cities represented in the datasets.
 *  The fields are what appear in the datasets. there are two fields for state
 *  this is because In the Zip dataset there is one state associated with each city 
 *  but in the connected cities file there are more than one.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import edu.princeton.cs.algs4.*;

public class City implements Comparable<City> {
	// the fields needed to creat city object based on dataset
	private int Zip;
	private int StateCode;
	private String State;
	private String Name;
	private Double Latitude;
	private Double Longitude;
	private double ageMedian;
	private double sexRatio;
	private double Population;
	private int points;
	public static Comparator<City> comparePoints = new Comparator<City>(){
		public int compare(City self, City other) {
			if (self.points>other.points){
				return 1;
			}
			if (self.points<other.points){
				return -1;
			}
			return 0;
		}
	};
			
	public City(String[] ID) {
		StateCode = Integer.parseInt(ID[0]);
		Zip = Integer.parseInt(ID[1]);
		State = ID[2];
		Name = ID[3];
		Latitude = Double.parseDouble(ID[4]);
		Longitude = Double.parseDouble(ID[5]);
	}

	/**
	 * implements the function from Comparable interface is used to sort cities
	 * and put them inside a BST
	 */
	public int compareTo(City other) {
		return this.Name.compareTo(other.Name);
	}

	/**
	 * given a city name it gives you the city object created from the text file
	 * 
	 * @return a BST for searching for a city
	 */
	public static RedBlackBST<String, City> create_CityST() {
		In in = new In("zips1990.csv");
		String[] stringList = in.readAllLines();
		RedBlackBST<String, City> CityST = new RedBlackBST<String, City>();
		for (int i = 1; i < stringList.length; i++) {
			City c = new City(stringList[i].split(","));
			CityST.put(c.Name + ", " + c.State, c);
		}
		return CityST;
	}

	public static City[] create_capitalCities() {
		RedBlackBST<String, City> CityST = create_CityST();
		In in = new In("State Capitals.csv");
		String[] stringList = in.readAllLines();
		City[] cityArray = new City[stringList.length];
		for (int i = 0; i < stringList.length; i++) {
			String cityName = stringList[i].toUpperCase().replace('"', '\0');
			City c = CityST.get(cityName);
			cityArray[i] = c;
		}
		initialize_fields(cityArray);
		return cityArray;
	}

	public static double[][] create_DataArray() {
		In in = new In("Data.csv");
		String[] stringList = in.readAllLines();
		String[][] indexedArray = new String[stringList.length][4];
		for (int i = 0; i < stringList.length; i++) {
			indexedArray[i] = stringList[i].split(",");
		}
		double[][] doubleArray = new double[stringList.length - 1][4];
		for (int i = 1; i < indexedArray.length; i++) {
			for (int j = 0; j < indexedArray[i].length; j++) {
				try {
					doubleArray[i - 1][j] = Double.parseDouble(indexedArray[i][j]);
				} catch (Exception NumberFormatException) {
					doubleArray[i - 1][j] = 0;
				}
			}
		}
		return doubleArray;
	}

	public static RedBlackBST<Integer, double[]> create_ZipBST() {
		RedBlackBST<Integer, double[]> ZipBST = new RedBlackBST<Integer, double[]>();
		double[][] dataArray = create_DataArray();
		for (int i = 0; i < dataArray.length; i++) {
			ZipBST.put((int) dataArray[i][0], dataArray[i]);
		}
		return ZipBST;
	}

	public static void initialize_fields(City[] cityArray) {
		RedBlackBST<Integer, double[]> ZipBST = create_ZipBST();
		for (City c : cityArray) {
			double[] fields = ZipBST.get(c.Zip);
			for (int i = 0; i < fields.length; i++) {
				c.setAgeMedian(fields[2]);
				c.setPopulation(fields[1]);
				c.setSexRatio(fields[3]);
			}
		}
	}
	public void setPoints(int ageRate, int sexRatioRate, int populationRate){
		int agePoint=1;
		int sexRatioPoint=1;
		int populationPoint=1;
		if (Population>50000){
			populationPoint=3;
		}
		if (Population>25000 && Population<50000){
			populationPoint=2;
		}
		if (Population<25000){
			populationPoint=1;
		}
		if (Math.abs(sexRatio-1.0)<5){
			sexRatioPoint=3;
		}
		if (Math.abs(sexRatio-1.0)<10){
			sexRatioPoint=2;
		}
		if (Math.abs(sexRatio-1.0)>10){
			sexRatioPoint=1;
		}
		if (ageMedian<34){
			agePoint=3;
		}
		if (ageMedian>34 && ageMedian<40){
			agePoint=2;
		}
		if (ageMedian>40){
			agePoint=1;
		}
		points=ageRate*agePoint + sexRatioRate*sexRatioPoint + populationRate*populationPoint;
	}
	/**
	 * created to test the methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// RedBlackBST<String, City> CityST=create_CityST();
		// City c = CityST.get("FRANKFORT, KY");
		// System.out.println(c.Zip);
		City[] cityArray = create_capitalCities();
		for (City c : cityArray) {
			System.out.println(c.getAgeMedian() + ", " + c.getPopulation() + ", " + c.sexRatio);
		}

	}

	public boolean equals(City other) {
		return this.Zip == other.getZip();
	}

	public String getName() {
		return this.Name;
	}

	public int getZip() {
		return this.Zip;
	}

	public int getStateCode() {
		return this.StateCode;
	}

	public String getState() {
		return this.State;
	}

	public double getLatitude() {
		return this.Latitude;
	}

	public double getLongitude() {
		return this.Longitude;
	}

	public String toString() {
		return this.Name;
	}

	public double getAgeMedian() {
		return ageMedian;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	public void setAgeMedian(double ageMedian) {
		this.ageMedian = ageMedian;
	}

	public double getSexRatio() {
		return sexRatio;
	}

	public void setSexRatio(double sexRatio) {
		this.sexRatio = sexRatio;
	}

	public double getPopulation() {
		return Population;
	}

	public void setPopulation(double population) {
		Population = population;
	}

}
