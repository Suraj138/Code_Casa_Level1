import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    private String userId;
    private String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private int id;
    private String name;
    private String date;
    private int numberOfGuests;

    public Reservation(int id, String name, String date, int numberOfGuests) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}

class ReservationSystem {
    private List<Reservation> reservations = new ArrayList<>();
    private int nextId = 1;

    public Reservation makeReservation(String name, String date, int numberOfGuests) {
        Reservation reservation = new Reservation(nextId++, name, date, numberOfGuests);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Reservation getReservationById(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public boolean cancelReservation(int id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservations.remove(reservation);
            return true;
        }
        return false;
    }
}

public class OnlineReservationSystem {
    private ReservationSystem reservationSystem = new ReservationSystem();
    private List<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public OnlineReservationSystem() {
        // Add some sample users (you can replace this with your actual user management)
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));
    }

    public User authenticateUser() {
        System.out.println("=======================================================");
        System.out.println("Welcome to our Digital ONLINE RESERVATION SYSTEM");
        System.out.println("=======================================================");
        System.out.print("Enter User ID:- ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password:- ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void start() {
        User authenticatedUser = null;
        while (authenticatedUser == null) {
            authenticatedUser = authenticateUser();
            if (authenticatedUser == null) {
                System.out.println("Invalid User ID or Password. Please try again.");
            }
        }

        System.out.println("\n=======================================================");
        System.out.println("Welcome (',') , " + authenticatedUser.getUserId() + "! ");
        System.out.println("=======================================================");

        while (true) {
        	System.out.println("\n********* MAIN MENU *********");
            System.out.println("1. Make a reservation");
            System.out.println("2. View all reservations");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Exit");
            System.out.println("*****************************");
            System.out.println(" ");
            System.out.println("Please choose any no to perform.");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Number of guests: ");
                    int numberOfGuests;
                    try {
                        numberOfGuests = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for number of guests. Please enter a number.");
                        continue;
                    }

                    Reservation reservation = reservationSystem.makeReservation(name, date, numberOfGuests);
                    System.out.println("Reservation made with ID " + reservation.getId());
                    break;
                case 2:
                    List<Reservation> allReservations = reservationSystem.getReservations();
                    if (allReservations.isEmpty()) {
                        System.out.println("There are no reservation records.");
                    } else {
                        System.out.println("Reservations:");
                        for (Reservation r : allReservations) {
                            System.out.println("Reservation ID => " + r.getId());
                            System.out.println("Name Of the customer => " + r.getName());
                            System.out.println("Number Of Guests => " + r.getNumberOfGuests());
                            System.out.println("Date =>  " + r.getDate());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Reservation ID to cancel: ");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for reservation ID. Please enter a number.");
                        continue;
                    }

                    if (reservationSystem.cancelReservation(id)) {
                        System.out.println("Reservation canceled");
                    } else {
                        System.out.println("Reservation not found");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using our Online Reservation System!");
                    scanner.close(); // Close the scanner to prevent resource leak
                    return;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
    	OnlineReservationSystem obj = new OnlineReservationSystem();
        obj.start();
    }
}
