package com.bilgeadam.utility.data;
public class Image{

public String medium;
String original;


public Image(){}

public Image(String medium, String original){

this.medium = medium;

this.original = original;

}

public void setMedium(String medium){

 	this.medium = medium;

}

public String getMedium(){

 	return this.medium;

}

public void setOriginal(String original){

 	this.original = original;

}

public String getOriginal(){

 	return this.original;

}

}