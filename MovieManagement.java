
package com.mycompany.application; import java.util.ArrayList;
public class MovieManagement {
    String title;
    int release;
    int ticket;
    double profit;
    static ArrayList<MovieManagement> moviesList = new ArrayList<>();
    static double discount = 0.0;
    boolean dis;
    MovieManagement(){
        
    }
    MovieManagement(String title, int release, int ticket, double profit) {
        this.title = title;
        this.release = release;
        this.ticket = ticket;
        this.profit = profit;
    }
    public double getPriceAfterDIscount() {
     if(dis) {
      return ticket - (ticket * discount / 100);
     }
     return ticket;
    }
    public void applyDiscount(double discount) {
     this.dis = true;
     this.discount = discount;
    }
    public  String getTitle() {
        return title;
    }
    public int getRelease() {
        return release;
    }
    public double getProfit() {
        return profit;
    }
    public int getTicket() {
        return ticket;
    }
    
    public double getTicketPriceAfterDiscount() {
        return ticket - (ticket * Admin.getDiscount() / 100);
    }
    @Override
    public String toString() {
        return "Title: " + title + ", Release Year: " + release + ", Original Ticket Price: " + ticket +
                ", Ticket Price After Discount: " + getTicketPriceAfterDiscount() + ", Profit: " + profit;
    }
    public static void addMovie(String title, int release, int ticket, double profit) {
        moviesList.add(new MovieManagement(title, release, ticket, profit));
        System.out.println("The movie has been added: " + title);
    }
    public static void deleteMovie(int index) {
        if (index > 0 && index <= moviesList.size()) {
            System.out.println("The movie \"" + moviesList.get(index - 1).getTitle() + "\" has been removed successfully!");
            moviesList.remove(index - 1);
        } else {
            System.out.println("Invalid movie index!");
        }
    }
    public static void display() {
        if (moviesList.isEmpty()) {
            System.out.println("No movies available to display.");
        } else {
            for (int i = 0; i < moviesList.size(); i++) {
                System.out.println((i + 1) + "- " + moviesList.get(i));
            }
        }
    }
    public static MovieManagement findHighestProfit() {
        if (moviesList.isEmpty()) {
            return null;
        }
        MovieManagement highest = moviesList.get(0);
        for (MovieManagement movie : moviesList) {
            if (movie.profit > highest.profit) {
                highest = movie;
            }
        }
        return highest;
    }
}