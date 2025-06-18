package railroad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RR {
	private Set<Train> trains = new HashSet<Train>(); // List of all trains
	private Map<String, String> temp_list_of_stations = new HashMap<String, String>(); // just to add acronyms as the stupid task does not let creation of Station getAcronym function
	private List<Station> stations = new ArrayList<Station>(); // List of all stations

	/** Default constructor */
	public RR() {}

	/**
	 * A function that adds a train to the list in the railway network
	 * @param acr - A train acronym
	 * @returns True or false, whether the function has been successfully executed and passed
	 */
	public boolean addTrain(String acr){ return trains.add(new Train(acr)); }

	/**
	 * A function that adds a station to the list in the railway network
	 * @param acr - A station acronym
	 * @param loc - A station location (if a location has multiple stations, then the name of the locations should be different)
	 * @returns True or false, whether the function has been successfully executed and passed
	 */
	public boolean addstation(String acr, String loc){
		// If there is no other key, the function returns null, otherwise it will overwrite the value
		if(temp_list_of_stations.put(acr, loc) == null)
			return stations.add(new Station(acr, loc));
		else
			return false;
	}

	/**
	 * A function that adds a connection between two stations; if there is a station in the network, it simply adds a new value in the two
	 * Station's lists (if they exist) and returns true, otherwise it returns a false value; invokes Station hasConnectionWith, addConnection
	 * and getLocation functions
	 * @param - st A station location
	 * @param - stOfConnection An other station location
	 * @returns True or false, whether the function has been successfully executed and passed
	 */
	public boolean addConectiontoStation(String st, String stOfConnection){
		boolean OK1 = false, OK2 = false;

		// If there are both station's acronyms in the list, place them in the list of connections
		if(temp_list_of_stations.containsKey(st) && temp_list_of_stations.containsKey(stOfConnection) && !st.equals(stOfConnection)){
			String loc = temp_list_of_stations.get(st), locConn = temp_list_of_stations.get(stOfConnection);

			Station temp_st = new Station(st, loc);
			Station temp_stConn = new Station(stOfConnection, locConn);

			for(Station s: stations){
				if(s.getLocation().equals(loc) && !s.hasConnectionWith(temp_stConn))
					OK1 = s.addConnection(temp_stConn);
				else if(s.getLocation().equals(locConn) && !s.hasConnectionWith(temp_st))
					OK2 = s.addConnection(temp_st);
			}
		}

		// If the connections have been placed, then true, otherwise false
		return OK1 && OK2;
	}

	/**
	 * A function that uses a train's acronym to show all of its stopping stations, invokes Train stopsToString function
	 * @param tra - A train acronym
	 * @param sta - A station acronym
	 * @returns¸True or false, whether the passage has been registered
	 */
	public boolean crossTrainByStation(String tra, String sta, int lin, boolean to){
		boolean OK = false;

		// If there is a station with an acronym, take its location
		if(temp_list_of_stations.containsKey(sta)){
			String loc = temp_list_of_stations.get(sta);

			/* If there is an existing location: search for a station, which has a matching location, then register the passage for the station,
			 * using that location, and the train, which has a matching acronym (the function returns true if both the passages have been
			 * registered for the station and the train */
			for(Station s: stations)
				if(s.getLocation().equals(loc))
					for(Train t: trains)
						if(t.getAcronym().equals(tra)){
							Station st_temp = new Station(sta, loc);

							// The passByStation function verifies if there is a connection between stations for train aspect, and if there is, then
							// the passage for stations can be safely added as well; or if this is the first passage (there are no previous stations
							// recorded for the train)
							if(s.hasConnectionWith(t.lastStation()) || t.lastStation() == null){
								t.passByStation(new Station(sta, loc), lin, to);
								OK = s.addPassage(new Passage(stations.indexOf(sta), to, lin, st_temp, t));
								break;
							}
						}
		}

		return OK;
	}

	/**
	 * A function that uses a station's acronym to show all trains that pass by it, invokes Station passagesToString function
	 * @param acr - A station acronym
	 * @returns A String that displays all of station's trains that pass by
	 */
	public String crossesStationToString(String acr){
		String str = "", location = temp_list_of_stations.get(acr);

		for(Station s: stations)
			if(s.getLocation().equals(location))
				str += s.passagesToString();

		return "List of passages for " + acr + ":" + str + "\n----";
	}

	/**
	 * A function that uses a train's acronym to show all of its stopping stations, invokes Train stopsToString function
	 * @param acr - A train acronym
	 * @returns A String that displays all of train's stopping stations
	 */
	public String stopTrainToString(String acr){
		String str = "";

		for(Train t: trains)
			if(t.getAcronym().equals(acr))
				str += t.stopsToString();

		return "List of stops for " + acr + ":" + str + "\n----";
	}
}