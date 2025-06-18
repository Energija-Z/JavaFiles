/**
 * 
 */
package main;

/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;*/

import railroad.RR;

/**
 * @author jaksa
 *
 */
public class Main {
	public static void main(String[] args) {
		RR r = new RR();

		r.addTrain("A1");
		r.addTrain("A2");
		r.addstation("B1", "Birkenfest");
		r.addstation("B2", "Kokolon");
		r.addstation("B3", "Anauroch");

		r.addConectiontoStation("B1", "B2");
		r.addConectiontoStation("B2", "B3");
		r.crossTrainByStation("A1", "B1", 1, true);
		r.crossTrainByStation("A1", "B2", 2, true);
		r.crossTrainByStation("A1", "B3", 2, true);
		r.crossTrainByStation("A2", "B2", 3, false);
		System.out.println(r.stopTrainToString("A1"));
		System.out.println(r.stopTrainToString("A2"));
		r.crossTrainByStation("A2", "B3", 3, true);
		System.out.println(r.stopTrainToString("A2"));

		System.out.println(r.crossesStationToString("B1"));
		System.out.println(r.crossesStationToString("B2"));
		System.out.println(r.crossesStationToString("B3"));
		System.out.println(r.stopTrainToString("A1"));
		System.out.println(r.stopTrainToString("A2"));

		r.crossTrainByStation("A2", "B4", 3, false);
		r.crossTrainByStation("A4", "B1", 2, false);
		System.out.println(r.crossesStationToString("B4"));
		System.out.println(r.stopTrainToString("A2"));
	}
}