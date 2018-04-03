package Models;

import java.io.Serializable;

/**
 * Model for the D2L project that contains hour and minute of the time of day
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @since April 3, 2018
 * @version 1.0
 */
public class Time implements Serializable
{

	/**
	 * Object ID when sending this object
	 */
	private static final long serialVersionUID = 7L;
	
	private int hour;
	
	private int minute;
	
	/**
	 * Constructor function for time
	 * @param hour
	 * @param minute
	 */
	public Time(int hour, int minute)
	{
		if(hour < 0 || hour > 23)
		{
			//TODO maybe throw a invalid time exception???
		}
		if(minute <0 || minute > 59)
		{
			//TODO maybe throw a invalid time exception???
		}
		this.hour = hour;
		this.minute = minute;
	}
	
	
	//Getters and setters
	
	public void setTime(int hour, int minute)
	{
		if(hour < 0 || hour > 23)
		{
			//TODO maybe throw a invalid time exception???
		}
		if(minute <0 || minute > 59)
		{
			//TODO maybe throw a invalid time exception???
		}
		this.hour = hour;
		this.minute = minute;
	}
	
	public int getHour() {return hour;}
	
	public int getMinute() {return minute;}
}
