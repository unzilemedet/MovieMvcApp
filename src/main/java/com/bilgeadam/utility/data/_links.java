package com.bilgeadam.utility.data;

public class _links{

Self self;
Previousepisode previousepisode;


public _links(){}

public _links(Self self, Previousepisode previousepisode){

this.self = self;

this.previousepisode = previousepisode;

}

public void setSelf(Self self){

 	this.self = self;

}

public Self getSelf(){


 	return this.self;

}

public void setPreviousepisode(Previousepisode previousepisode){

 	this.previousepisode = previousepisode;

}

public Previousepisode getPreviousepisode(){


 	return this.previousepisode;

}

}