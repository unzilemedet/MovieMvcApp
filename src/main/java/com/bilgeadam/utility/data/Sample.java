package com.bilgeadam.utility.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sample {
    public Integer id;
    public String url;
    public String name;
    String type;
    public String language;
    public List<String> genres;
    String status;
    int runtime;
    int averageRuntime;
    public String premiered;
    String ended;
    String officialSite;
    Schedule schedule;
    public Rating rating;
    int weight;
    public Network network;
    WebChannel webChannel;
    DvdCountry dvdCountry;
    Externals externals;
    public Image image;
    public String summary;
    public int updated;
    public _links _links;
}