package railroad;

import java.util.ArrayList;
import java.util.List;

public class Train {
	private String acronym;
	private List<Passage> passages = new ArrayList<Passage>();

	/**
	 * Default constructor. Parses the train's acronym.
	 * @param acr - A train acronym
	 */
	public Train(String acr) { acronym = acr; }

	/**
	 * A function that returns a train's acronym
	 * @returns A String, the train's acronym
	 */
	public String getAcronym(){ return acronym; }

	/**
	 * A function that shows the last station (passage) of the train.
	 * @returns The last station.
	 */
	public Station lastStation(){
		if(passages.isEmpty())
			return null;
		else
			return passages.get(passages.size() - 1).getStation();
	}

	/**
	 * A function that registers a new passage, using the size of the list of the stations it has to pass by as an ID
	 * @returns True or false, whether the function has registered the passage successfully
	 */
	public boolean passByStation(Station st, int lin, boolean to){
		boolean OK = false;

		// If there is already an entry
		if(!passages.isEmpty())
			for(int i = 0; i < passages.size(); i++){
				Passage p = passages.get(i);
				// Check if there is an entry, then see if it has a connection with the previous station
				if(!p.getStation().hasConnectionWith(st)){
					passages.add(new Passage(passages.size(), to, lin, st, this));
					OK = passages.get(passages.size() - 1).getStation().addConnection(st);
					break;
				}
			}
		// To add the first entry (since there is no previous station)
		else
			OK = passages.add(new Passage(passages.size(), to, lin, st, this));

		return OK;
	}

	/**
	 * A function that lists a train's passages  
	 * @returns A string that shows details of a train's passage
	 */
	public String stopsToString(){
		StringBuffer str = new StringBuffer();

		for(Passage p: passages)
			str.append("\n" + p.toString());

		return str.toString();
	}
}