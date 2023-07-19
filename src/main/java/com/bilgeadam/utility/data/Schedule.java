package com.bilgeadam.utility.data;

import java.util.List;

public class Schedule{

String time;
List<String> days;


public Schedule(){}

public Schedule(String time, List<String> days){

this.time = time;

this.days = days;

}

public void setTime(String time){

 	this.time = time;

}

public String getTime(){

 	return this.time;

}

public void setDays(List<String>days){

 	this.days = days;

}
public List<String> getDays(){

 	return this.days;

}

}