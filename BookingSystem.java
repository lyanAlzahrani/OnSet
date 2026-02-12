 
package com.mycompany.application;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

public class BookingSystem {

    static class Booking {
        private final String movieName;
        private final int seatNumber;
        private final LocalDateTime showTime;

        public Booking(String movieName, int seatNumber, LocalDateTime showTime) {
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.showTime = showTime;
        }

        public String getMovieName() {
            return movieName;
        }

        public int getSeatNumber() {
            return seatNumber;
        }

        public LocalDateTime getShowTime() {
            return showTime;
        }

        @Override
        public String toString() {
            return "Movie: " + movieName + ", Seat: " + (seatNumber < 10 ? "0" + seatNumber : seatNumber) +
                    ", Date: " + showTime.toLocalDate() + ", Time: " + showTime.toLocalTime();
        }
    }

    static class Customer {
        private final ArrayList<String> reservations;
        private final ArrayList<String> availableMovies;

        public Customer() {
            this.reservations = new ArrayList<>();
            this.availableMovies = new ArrayList<>();
            // إضافة بعض الأفلام المتاحة
            availableMovies.add("Interstellar");
            availableMovies.add("Frozen");
            availableMovies.add("Saw");
            availableMovies.add("The true man show");
            availableMovies.add("Fight Club");
            availableMovies.add("Titanic");
            availableMovies.add("Toy Story");
            availableMovies.add("Deadpool & Wolverine");
            availableMovies.add("Wicked");
            availableMovies.add("Barbie");
            availableMovies.add("The Wild Robot");
            availableMovies.add("Moana 2");
            availableMovies.add("Inside Out 2");
            availableMovies.add("Despicable Me 4");
            availableMovies.add("Spirited Away");
            availableMovies.add("The Lion King");
            availableMovies.add("Oppenheimer");
            availableMovies.add("Howl's Moving Castl");
            availableMovies.add("How to Train Your Dragon");
            availableMovies.add("Harry Potter");
            availableMovies.add("Home Alone");
            availableMovies.add("Night at the Museum");
            availableMovies.add("Avatar 2: the way of water");
            availableMovies.add("Terrifier");
        }

        public void addReservation(String reservation) {
            if (!reservations.contains(reservation)) {
                reservations.add(reservation);
            }
        }

        public boolean isMovieAvailable(String movieName) {
            return availableMovies.contains(movieName);
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

        public String selectMovieByNumber(int movieNumber) {
            if (movieNumber < 1 || movieNumber > availableMovies.size()) {
                return null; // اختيار غير صالح
            }
            return availableMovies.get(movieNumber - 1); // العودة باسم الفيلم المختار
        }
    }

    private final ArrayList<Booking> bookings;
    private final int totalSeats = 30;
    private final ArrayList<LocalDateTime> availableShowtimes;

    public BookingSystem() {
        bookings = new ArrayList<>();
        availableShowtimes = new ArrayList<>();

        // Generate showtimes for the next 12 days starting from today
        LocalDate startDate = LocalDate.now();
        for (int dayOffset = 0; dayOffset < 12; dayOffset++) {
            LocalDate currentDay = startDate.plusDays(dayOffset);
            availableShowtimes.add(LocalDateTime.of(currentDay, java.time.LocalTime.of(10, 0)));
            availableShowtimes.add(LocalDateTime.of(currentDay, java.time.LocalTime.of(13, 0)));
            availableShowtimes.add(LocalDateTime.of(currentDay, java.time.LocalTime.of(16, 0)));
            availableShowtimes.add(LocalDateTime.of(currentDay, java.time.LocalTime.of(19, 0)));
            availableShowtimes.add(LocalDateTime.of(currentDay, java.time.LocalTime.of(22, 0)));
        }
    }

    public void addBooking(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        // عرض الأفلام المتاحة للعميل
        customer.displayAvailableMovies();
        System.out.print("Select a movie (enter the number): ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String selectedMovie = customer.selectMovieByNumber(movieChoice);
        if (selectedMovie == null) {
            System.out.println("Invalid movie selection.");
            return;
        }

        // عرض التواريخ المتاحة
        ArrayList<LocalDate> uniqueDates = new ArrayList<>();
        System.out.println("Available Showtimes:");
        for (LocalDateTime time : availableShowtimes) {
            if (!uniqueDates.contains(time.toLocalDate())) {
                uniqueDates.add(time.toLocalDate());
                System.out.println((uniqueDates.size()) + ". " + time.toLocalDate());
            }
        }
        System.out.print("Select a date (enter the number): ");
        int dateChoice = scanner.nextInt();

        if (dateChoice < 1 || dateChoice > uniqueDates.size()) {
            System.out.println("Invalid date selection.");
            return;
        }
        LocalDate selectedDate = uniqueDates.get(dateChoice - 1);

        // عرض الأوقات المتاحة لهذا التاريخ
        System.out.println("Available Times for " + selectedDate + ":");
        ArrayList<LocalDateTime> timesForDate = new ArrayList<>();
        for (LocalDateTime time : availableShowtimes) {
            if (time.toLocalDate().equals(selectedDate)) {
                timesForDate.add(time);
                System.out.println((timesForDate.size()) + ". " + time.toLocalTime());
            }
        }

        System.out.print("Select a time (enter the number): ");
        int timeChoice = scanner.nextInt();

        if (timeChoice < 1 || timeChoice > timesForDate.size()) {
            System.out.println("Invalid time selection.");
            return;
        }
        LocalDateTime selectedTime = timesForDate.get(timeChoice - 1);

        // Loop for booking multiple seats
        while (true) {
            displayAvailableSeats(selectedTime);
            System.out.print("Enter seat number: ");
            int seatNumber = scanner.nextInt();

            if (seatNumber < 1 || seatNumber > totalSeats) {
                System.out.println("Invalid seat number.");
                return;
            }

            // Check if the seat is already booked
            for (Booking booking : bookings) {
                if (booking.getSeatNumber() == seatNumber && booking.getShowTime().equals(selectedTime)) {
                    System.out.println("Seat " + (seatNumber < 10 ? "0" + seatNumber : seatNumber) + " is already booked for this showtime.");
                    return;
                }
            }

            // Add booking
            bookings.add(new Booking(selectedMovie, seatNumber, selectedTime));
            customer.addReservation("Movie: " + selectedMovie + ", Seat: " + seatNumber + ", at " + selectedTime);
            System.out.println("Booking added: " + selectedMovie + ", Seat " + (seatNumber < 10 ? "0" + seatNumber : seatNumber) + ", at " + selectedTime);

            // Ask if the user wants to book another seat
            System.out.print("Do you want to book another seat? (yes/no): ");
            scanner.nextLine(); // Consume newline
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    public void removeBooking() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available to remove.");
            return;
        }

        System.out.println("Current Bookings:");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ". " + bookings.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select the booking number to remove (enter the number): ");
        int bookingIndex = scanner.nextInt();

        if (bookingIndex < 1 || bookingIndex > bookings.size()) {
            System.out.println("Invalid booking selection.");
            return;
        }

        Booking removedBooking = bookings.remove(bookingIndex - 1);
        System.out.println("Booking removed: " + removedBooking);
    }

    public void displayAvailableSeats(LocalDateTime showTime) {
        System.out.println("Available Seats for " + showTime + ":");
        boolean foundAvailable = false;
        for (int seatNumber = 1; seatNumber <= totalSeats; seatNumber++) {
            boolean isBooked = false;
            for (Booking booking : bookings) {
                if (booking.getSeatNumber() == seatNumber && booking.getShowTime().equals(showTime)) {
                    System.out.print("XX ");
                    isBooked = true;
                    break;
                }
            }
            if (!isBooked) {
                System.out.print((seatNumber < 10 ? "0" + seatNumber : seatNumber) + " ");
                foundAvailable = true;
            }

            if (seatNumber % 6 == 0) System.out.println(); // Print new line every 6 seats
        }
        if (!foundAvailable) {
            System.out.println("No seats available for this showtime.");
        } else {
            System.out.println();
        }
    }

    public void displayBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            System.out.println("Current Bookings:");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    public void runBookingSystem() {
        Customer customer = new Customer();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nBooking System Menu:");
            System.out.println("1. Add Booking");
            System.out.println("2. Remove Booking");
            System.out.println("3. Display Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: addBooking(customer);
                case 2 : removeBooking();
                case 3 : displayBookings();
                case 4 : {
                    System.out.println("Exiting...");
                    return;
                }
                default : System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   
}
                    