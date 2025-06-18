package railroad;

import java.util.Date;

public class Passage {
	@SuppressWarnings("unused")
	private int id;
	private boolean withStop;
	private int line;
	private Date datehour = new Date();
	private Station station;
	private Train train;

	/**
	 * Default constructor
	 * @param id - The id of the passage
	 * @param to - Whether the train has a stop
	 * @param lin - The line number
	 * @param s - The acronym of the station the train goes through
	 * @param t - The acronym of the train
	 */
	public Passage(int id, boolean to, int lin, Station s, Train t) {
		this.id  = id;
		withStop = to;
		line     = lin;
		station  = s;
		train    = t;
	}

	/**
	 * A function that checks if there is a stop
	 * @returns True or false if the passage has a stop
	 */
	public boolean hasStop(){ return withStop; }

	/**
	 * A function that returns the passage station
	 * @returns A Station that is the passing point
	 */
	public Station getStation(){ return station; }

	@SuppressWarnings("deprecation")
	/**
	 * A function that displays a passage's details
	 * @returns A string listing a passage's train, date and time of arrival, line number, location and whether it stops there
	 */
	public String toString(){
		int time = datehour.getMinutes();
		String str = time + "";

		if(time < 10)
			str = "0" + time;

		return "train: " + train.getAcronym() + ", date: " + datehour.getDate() + "." + datehour.getMonth() + "." +
	 (1900 + datehour.getYear()) + "., time: " + datehour.getHours() + ":" + str + ", line number: " + line + ", location: " +
	 station.getLocation() +  " (stopping: " + withStop + ")"; }
 }