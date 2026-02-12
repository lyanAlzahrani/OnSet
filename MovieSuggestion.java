package com.mycompany.application;

import java.util.*;
import java.util.stream.Collectors;

class MovieSuggestion {
    private final List<Movie> movieList;

    public MovieSuggestion(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> suggestByGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            System.out.println("Invalid genre input. Please provide a valid genre.");
            return List.of();
        }
        
        List<Movie> filteredMovies = movieList.stream()
                .filter(movie -> movie.getGener().stream()
                        .anyMatch(g -> g.equalsIgnoreCase(genre)))
                .sorted(Comparator.comparing(Movie::getRating).reversed())
                .collect(Collectors.toList());

        if (filteredMovies.isEmpty()) {
            System.out.println("No movies found for the genre: " + genre);
        }

        return filteredMovies;
    }

    public List<Movie> suggestSimilarMovies(String title) {
        if (title == null || title.isBlank()) {
            System.out.println("Invalid title input. Please provide a valid movie title.");
            return List.of();
        }

        return movieList.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .sorted(Comparator.comparing(Movie::getRating).reversed())
                .collect(Collectors.toList());
    }
}
