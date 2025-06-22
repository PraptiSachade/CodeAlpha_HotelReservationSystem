import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    int roomNumber;

    Booking(String customerName, int roomNumber) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}

public class HotelReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();

        while (true) {
            System.out.println("\n--- Hotel Reservation Menu ---");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    showAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System.");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void initializeRooms() {
        for (int i = 1; i <= 3; i++) rooms.add(new Room(i, "Standard"));
        for (int i = 4; i <= 6; i++) rooms.add(new Room(i, "Deluxe"));
        for (int i = 7; i <= 9; i++) rooms.add(new Room(i, "Suite"));
    }

    static void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (!r.isBooked) {
                System.out.println("Room " + r.roomNumber + " (" + r.category + ")");
            }
        }
    }

    static void bookRoom() {
        showAvailableRooms();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && !r.isBooked) {
                r.isBooked = true;
                bookings.add(new Booking(name, roomNo));
                System.out.println("Booking successful for Room " + roomNo);
                return;
            }
        }
        System.out.println("Room not available!");
    }

    static void cancelBooking() {
        System.out.print("Enter your name to cancel booking: ");
        String name = sc.nextLine();

        Iterator<Booking> it = bookings.iterator();
        while (it.hasNext()) {
            Booking b = it.next();
            if (b.customerName.equalsIgnoreCase(name)) {
                for (Room r : rooms) {
                    if (r.roomNumber == b.roomNumber) {
                        r.isBooked = false;
                        break;
                    }
                }
                it.remove();
                System.out.println("Booking canceled.");
                return;
            }
        }
        System.out.println("No booking found with this name.");
    }

    static void viewBookings() {
        System.out.println("\nCurrent Bookings:");
        for (Booking b : bookings) {
            System.out.println("Name: " + b.customerName + ", Room: " + b.roomNumber);
        }
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        }
    }
}