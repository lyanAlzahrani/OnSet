
package com.mycompany.application;

import java.util.ArrayList;
public class Customer {
    private final ArrayList<String> reservations;
    private final ArrayList<Movie> availableMovies;

    public Customer(ArrayList<Movie> movies) {
        this.reservations = new ArrayList<>();
        this.availableMovies = movies;
    }

    public void addReservation(String reservation) {
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
        }
    }

    public boolean isMovieAvailable(String movieName) {
        return availableMovies.stream().anyMatch(movie -> movie.getTitle().equals(movieName));
    }

    public ArrayList<String> getReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("Your Reservations:");
            for (String reservation : reservations) {
                System.out.println("- " + reservation);
            }
        }
        return reservations;
    }

    public void displayAvailableMovies() {
        System.out.println("Available Movies:");
        for (int i = 0; i < availableMovies.size(); i++) {
            System.out.println((i + 1) + ". " + availableMovies.get(i));
        }
    }

    public Movie selectMovieByNumber(int movieNumber) {
        if (movieNumber < 1 || movieNumber > availableMovies.size()) {
            return null; // Invalid selection
        }
        return availableMovies.get(movieNumber - 1); // Return selected movie
    }
}