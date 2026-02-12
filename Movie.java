
package com.mycompany.application;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Movie {
    private String title;
    public ArrayList<String> genre;//tybe
    public double Rating;//0.0-10.0
    private ArrayList<LocalDateTime> schedul;//showtime
    public ArrayList<LocalDateTime> showtime;
    public boolean availability;
    private final double rating;
    private final double price;
   
    public Movie (String title, ArrayList<String> genre, double rating, ArrayList<LocalDateTime> schedule, double price){
    this.title=title;
    this.genre=genre;
    this.rating=rating;
    this.schedul=schedule;
    this.showtime=showtime;
    this.price=price;
    }
    public void Display()
    {
    System.out.println("title:"+title);
    System.out.println("Rating:"+Rating);
    System.out.println("Schedul:"+schedul);
    System.out.println("genre:"+genre);
    }
    public void addGenre(ArrayList<String>newGenres)
    {
        this.genre.addAll(newGenres);
    }
    public ArrayList<LocalDateTime> getschedul(){
    return schedul;
    }
    public String getTitle(){
    return title;
    }
    public ArrayList<String> getGener(){
    return genre;
    }
    public Double getRating(){
    return rating;
    }
    public ArrayList<LocalDateTime> getShowtime(){
    return showtime;
    }
    public boolean isAvailable(){
    return availability;
    }
    public void UpdateDetails(String title,ArrayList<String> genre,double rating,ArrayList<LocalDateTime> showtime, boolean availability)
    {
    this.title=title;
    this.genre=genre;
    this.Rating= rating;
    this.schedul= showtime;
    this.availability=availability;
    }
    
    @Override
    public String toString() {
    return "Title: " + title + ", Genres: " + genre + ", Rating: " + rating;
}

    public void DisplayDetails(){
    System.out.println("Title: "+title);
    System.out.println("genre: "+genre);
    System.out.println("Rating: "+rating);
    System.out.println("showtime: "+showtime);
    System.out.println("IS Available: "+(availability ?"yes":"no"));
    
    }
}