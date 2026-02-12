package com.mycompany.application;
import java.util.*;

public class AiRecommendation {
    private final Map<String, List<String>> userPreferences;
    private final List<Movie> movieDatabase;

    public AiRecommendation(List<Movie> movieDatabase) {
        this.movieDatabase = movieDatabase;
        this.userPreferences = new HashMap<>();
    }

    public void addUserPreference(String userId, String genre) {
        userPreferences.computeIfAbsent(userId, k -> new ArrayList<>()).add(genre);
    }

    public List<Movie> recommendMovies(String userId) {
        List<String> userGenres = userPreferences.get(userId);
        if (userGenres == null) return new ArrayList<>();

        List<Movie> recommendations = new ArrayList<>();
        for (Movie movie : movieDatabase) {
            for (String genre : userGenres) {
                if (movie.getGener().contains(genre)) {
                    recommendations.add(movie);
                    break;
                }
            }
        }

        recommendations.sort((m1, m2) -> Double.compare(m2.getRating(), m1.getRating()));

        return recommendations;
    }
}  
