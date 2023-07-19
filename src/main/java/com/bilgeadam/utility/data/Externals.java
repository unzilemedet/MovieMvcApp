package com.bilgeadam.utility.data;
public class Externals{

int tvrage;
int thetvdb;
String imdb;


public Externals(){}

public Externals(int tvrage, int thetvdb, String imdb){

this.tvrage = tvrage;

this.thetvdb = thetvdb;

this.imdb = imdb;

}

public void setTvrage(int tvrage){

 	this.tvrage = tvrage;

}

public int getTvrage(){

 	return this.tvrage;

}

public void setThetvdb(int thetvdb){

 	this.thetvdb = thetvdb;

}

public int getThetvdb(){

 	return this.thetvdb;

}

public void setImdb(String imdb){

 	this.imdb = imdb;

}

public String getImdb(){

 	return this.imdb;

}

}