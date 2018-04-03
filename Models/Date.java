package Models;

import java.io.Serializable;

public class Date implements Serializable
{

	/**
	 * Object ID for sending the object
	 */
	private static final long serialVersionUID = 8L;

	private int day;
	
	private int month;
	
	private int year;
	
	private Time time;
	
	public Date(int day, int month, int year, int hour, int minute)
	{
		//TODO throw an invalid date exception???
		
		this.day = day;
		
		this.month = month;
		
		this.year = year;
		
		this.time = new Time(hour, minute);
	}
	
	//Getters and Setters
	
	public int getDay() {return day;}
	public int getMonth() { return month;}
	public int getYear() { return year;}
	public Time getTime() { return time;}
	
	//TODO create setters if we need them
}
