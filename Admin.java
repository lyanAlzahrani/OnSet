
package com.mycompany.application;
import java.util.ArrayList;
import java.util.Scanner;
public class Admin {
    public Admin(ArrayList<Movie> movieList) {
    }
    
    static ArrayList<String> offersList = new ArrayList<>();
    private final String username = "admin";
    private final String password = "224";
    static double discount = 0.0;
    MovieManagement movie = new MovieManagement();
    
    public static double getDiscount() {
        return discount;
    }
    public boolean login(String admin_Username, String admin_Password) {
        return username.equals(admin_Username) && password.equals(admin_Password);
    } // check the username and password
    public static void addOffer (int movieIndex, double discountPercentage) {
     if (movieIndex > 0 && movieIndex <= MovieManagement.moviesList.size()) {
    MovieManagement movie = MovieManagement.moviesList.get(movieIndex - 1);
    movie.applyDiscount(discountPercentage);
    offersList.add("Offer applied to movie: " + movie.getTitle() + " with " + discountPercentage + "% discount.");
    System.out.println("Offer added to movie: " + movie.getTitle());}
    else {
    System.out.println("Invalid movie index!");
        }
    }
    public static void displayOffers() {
        if (offersList.isEmpty()) {
            System.out.println("No offers available.");
        } else {
            System.out.println("Available offers:");
            for (int i = 0; i < offersList.size(); i++) {
                System.out.println((i + 1) + "- " + offersList.get(i));
            }
            System.out.println("Current discount: " + discount + "%");
        }
    } // pritn all available offers 
    public static void deleteOffer(int index) {
        if (index > 0 && index <= offersList.size()) {
            System.out.println("The offer \"" + offersList.get(index - 1) + "\" has been removed successfully!");
            offersList.remove(index - 1);
            if (offersList.isEmpty()) {
                discount = 0.0; 
            }
        } else {
            System.out.println("Invalid offer index!");
        }
    } //remove an offer from the list 
    public static void adminMenu(){
 Scanner input = new Scanner(System.in);
 
 System.out.println("----- Admin Menu -----");
 System.out.println("Enter 1 to add movies.");
        System.out.println("Enter 2 to remove movies. ");
 System.out.println("Enter 3 to display all movies.");
 System.out.println("Enter 4 to add offer.");
 System.out.println("Enter 5 to display all offers. ");
 System.out.println("Enter 6 to remove offer.");
 System.out.println("Enter 7 to quit .");
        int choice;
 do{     
 System.out.println("Enter your choice :");
 choice = input.nextInt();
         switch(choice){
case 1 : {
    System.out.print("Enter movie information (name, year of release, ticket price, profit): ");
    String name = input.next();
    int year = input.nextInt();
    int ticket = input.nextInt();
    double profit = input.nextDouble();
    MovieManagement.addMovie(name, year, ticket, profit);
         }
case 2 : {
    System.out.println("Enter number of the movie that you want to delete it :");
    int index = input.nextInt();
    MovieManagement.deleteMovie(index);
         }
case 3 : MovieManagement.display();
case 4 : {
    System.out.println("Enter the number of the movieto apply the offer to:");
    MovieManagement.display();
             int index = input.nextInt();
             System.out.print("Enter the discount percentage: ");
             double discountPercentage = input.nextDouble();
             addOffer(index, discountPercentage);
         }
case 5 : displayOffers();
case 6 : {
    System.out.println("Enter the number of the offer you want to delete:");
    displayOffers();
             int index = input.nextInt();
             deleteOffer(index);
         }
default : System.out.println("Invalid chice!");
}           
        }while(choice != 7);           
    }
} 