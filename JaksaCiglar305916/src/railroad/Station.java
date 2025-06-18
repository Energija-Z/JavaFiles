package railroad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Station {
	@SuppressWarnings("unused")
	private String acronym;
	private String local;
	public List<Station> connections = new ArrayList<Station>();
	private List<Passage> passages = new ArrayList<Passage>();

	/**
	 * Default constructor
	 * @param acr - The acronym of the station
	 * @param loc - The name of the location of the station
	 */
	public Station(String acr, String loc) {
		local   = loc;
		acronym = acr;
	}

	/**
	 * A function that returns the station's location
	 * @returns A String of the station's location
	 */
	public String getLocation(){ return local; }

	/**
	 * A function that adds a connection to a station
	 * @returns True or false, whether the connection has been successfully added
	 */
	public boolean addConnection(Station other){ return connections.add(other); }

	/**
	 * A function that checks if the station is connected to another one
	 * @returns True or false, whether the station has the mutual connection with the other one
	 */
	public boolean hasConnectionWith(Station other){ return connections.contains(other); }

	/**
	 * A function that registers a train's passage through the station
	 * @param p - A Passage of a train through a station
	 * @returns True or false, whether the station has registered the passage
	 */
	public boolean addPassage(Passage p){ return passages.add(p); }

	/**
	 * A function that lists all of the trains that pass through a station; invokes Passage toString function
	 * @returns A String that has an acronym, and the rest of information concerning passages
	 */
	public String passagesToString(){
		StringBuffer str = new StringBuffer();

		for(Passage p: passages)
			str.append("\n" + p.toString());

		return str.toString();
	}
}