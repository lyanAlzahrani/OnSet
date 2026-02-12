package com.mycompany.application;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        ArrayList<Movie> movieList = new ArrayList<>();
        movies(movieList);

        AiRecommendation aiRecommendation = new AiRecommendation(movieList);
        MovieSuggestion movieSuggestion = new MovieSuggestion(movieList);

        BookingSystem bookingSystem = new BookingSystem();
        Admin admin = new Admin(movieList);

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to OnSet Movie Management System!");

        while (true) {
            System.out.println("\n-- Main Menu --");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Mode");
            System.out.println("3. Exit");
            System.out.print("\nSelect a mode: ");

            int mode = scanner.nextInt();
            scanner.nextLine();

            switch (mode) {
                case 1 : {
                    System.out.print("Enter admin username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String password = scanner.nextLine();

                    if (admin.login(username, password)) {
                        System.out.println("Login successful.");
                        admin.adminMenu();
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                }
                case 2 : {
                    Customer customer = new Customer(movieList);

                    while (true) {
                        System.out.println("\nCustomer Menu:");
                        System.out.println("1. Display Available Movies");
                        System.out.println("2. Reserve a Movie");
                        System.out.println("3. View Recommendations");
                        System.out.println("4. Suggest Movies by Genre");
                        System.out.println("5. View Reservations");
                        System.out.println("6. Exit Customer Mode");
                        System.out.print("Enter your choice: ");

                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1 : customer.displayAvailableMovies();
                            case 2 : bookingSystem.runBookingSystem();
                            case 3 : {
                                System.out.println("\n--- Movie Recommendations ---");
                                System.out.println("1. Input User Preferences");
                                System.out.println("2. Get Recommendations");
                                System.out.print("Enter your choice: ");
                                int recommendationChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (recommendationChoice) {
                                    case 1 : {
                                        System.out.print("Enter User ID: ");
                                        String userId = scanner.nextLine();
                                        System.out.print("Enter preferred genre: ");
                                        String genre = scanner.nextLine();
                                        aiRecommendation.addUserPreference(userId, genre);
                                        System.out.println("Preference added successfully.");
                                    }
                                    case 2 : {
                                        System.out.print("Enter User ID: ");
                                        String userId = scanner.nextLine();
                                        System.out.print("Enter the number of recommendations to display: ");
                                        int limit = scanner.nextInt();
                                        List<Movie> recommendations = aiRecommendation.recommendMovies(userId);
                                        if (recommendations.isEmpty()) {
                                            System.out.println("No recommendations available.");
                                        } else {
                                            System.out.println("Recommendations:");
                                            recommendations.stream()
                                                    .limit(limit)
                                                    .forEach(movie ->
                                                            System.out.println(movie.getTitle() + " (Rating: " + movie.getRating() + ")"));
                                        }
                                    }
                                    default : System.out.println("Invalid choice.");
                                }
                            }
                           case 4 : {
                                    System.out.print("Enter part of the movie title to find similar movies: ");
                                    String title = scanner.nextLine();
                                    List<Movie> similarMovies = movieSuggestion.suggestSimilarMovies(title);
                                    if (similarMovies.isEmpty()) {
                                       System.out.println("No similar movies found for: " + title);
                                       } else {
                                         System.out.println("Movies with similar titles to \"" + title + "\":");
                                            similarMovies.forEach(movie ->
                                         System.out.println("- " + movie.getTitle() + " (Rating: " + movie.getRating() + ")"));
                                    }
                            }
                            case 5 : customer.getReservations();
                            case 6 : {
                                System.out.println("Exiting customer mode.");
                                break;
                            }
                            default : System.out.println("Invalid choice.");
                        }

                        if (choice == 6) break;
                    }
                }
                case 3 : {
                    System.out.println("Exiting application. Goodbye!");
                    return;
                }
                default : System.out.println("Invalid mode selection.");
            }
        }
    }

    private static void movies(ArrayList<Movie> movieList) {
        movieList.add(new Movie("Interstellar", new ArrayList<>(List.of("Sci-Fi", "Drama")), 8.6, new ArrayList<>(), 15.00));
        movieList.add(new Movie("Frozen", new ArrayList<>(List.of("Family", "Drama")), 7.4, new ArrayList<>(), 10.00));
        movieList.add(new Movie("Saw", new ArrayList<>(List.of("Horror", "Mystery")), 6.7, new ArrayList<>(), 12.00));
        movieList.add(new Movie("The true man show", new ArrayList<>(List.of("Dark comedy")), 8.2, new ArrayList<>(), 14.00));
        movieList.add(new Movie("Fight Club", new ArrayList<>(List.of("Psychological", "Drama")), 8.8, new ArrayList<>(), 16.00));
        movieList.add(new Movie("Titanic", new ArrayList<>(List.of("Drama")), 7.9, new ArrayList<>(), 13.00));
        movieList.add(new Movie("Toy Story", new ArrayList<>(List.of("Fantasy", "Adventure")), 8.3, new ArrayList<>(), 11.00));
        movieList.add(new Movie("Deadpool & Wolverine", new ArrayList<>(List.of("Action", "Adventure", "Comedy")), 7.7, new ArrayList<>(), 15.00));
        movieList.add(new Movie("Wicked", new ArrayList<>(List.of("Fantasy", "Musical")), 8.1, new ArrayList<>(), 14.00));
        movieList.add(new Movie("Barbie", new ArrayList<>(List.of("Fantasy", "Adventure", "Comedy")), 6.8, new ArrayList<>(), 10.00));
        movieList.add(new Movie("The Wild Robot", new ArrayList<>(List.of("Animation", "Sci-Fi")), 8.3, new ArrayList<>(), 12.00));
        movieList.add(new Movie("Moana 2", new ArrayList<>(List.of("Animation", "Adventure")), 7.1, new ArrayList<>(), 9.00));
        movieList.add(new Movie("Inside Out 2", new ArrayList<>(List.of("Animation", "Adventure")), 7.6, new ArrayList<>(), 10.00));
        movieList.add(new Movie("Despicable Me 4", new ArrayList<>(List.of("Animation", "Comedy")), 6.2, new ArrayList<>(), 8.00));
        movieList.add(new Movie("Spirited Away", new ArrayList<>(List.of("Animation", "Fantasy")), 8.6, new ArrayList<>(), 14.00));
        movieList.add(new Movie("The Lion King", new ArrayList<>(List.of("Animation", "Adventure")), 8.5, new ArrayList<>(), 13.00));
        movieList.add(new Movie("Oppenheimer", new ArrayList<>(List.of("History", "Drama")), 8.3, new ArrayList<>(), 15.00));
        movieList.add(new Movie("Howl's Moving Castl", new ArrayList<>(List.of("Animation", "Family")), 8.2, new ArrayList<>(), 13.00));
        movieList.add(new Movie("How to Train Your Dragon", new ArrayList<>(List.of("Animation", "Fantasy")), 8.1, new ArrayList<>(), 11.00));
        movieList.add(new Movie("Harry Potter", new ArrayList<>(List.of("Fantasy", "Adventure")), 7.7, new ArrayList<>(), 14.00));
        movieList.add(new Movie("Home Alone", new ArrayList<>(List.of("Adventure", "Family")), 7.7, new ArrayList<>(), 10.00));
        movieList.add(new Movie("Night at the Museum", new ArrayList<>(List.of("Family", "Adventure")), 6.2, new ArrayList<>(), 9.00));
        movieList.add(new Movie("Avatar 2: the way of water", new ArrayList<>(List.of("Fantasy", "Adventure")), 7.5, new ArrayList<>(), 12.00));
        movieList.add(new Movie("Terrifier", new ArrayList<>(List.of("Horror", "Dark comedy")), 6.4, new ArrayList<>(), 10.00));
    }
}
