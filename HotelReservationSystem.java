import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Room {
    private int roomNumber;
    private String roomType;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public boolean isAvailable() { return isAvailable; }
    public double getPrice() { return price; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + roomType + "] - Price: $" + price + " - Available: " + isAvailable;
    }
}


class Booking {
    private int bookingId;
    private String customerName;
    private Room room;
    private String date;

    public Booking(int bookingId, String customerName, Room room, String date) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.room = room;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Customer: " + customerName + ", Room: " + room.getRoomNumber() +
               ", Date: " + date;
    }
}


class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;
    private int bookingCounter;

    public Hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        bookingCounter = 1;

        rooms.add(new Room(101, "Single", 100.00));
        rooms.add(new Room(102, "Double", 150.00));
        rooms.add(new Room(103, "Suite", 300.00));
    }

    public List<Room> searchRooms(String roomType) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean bookRoom(int roomNumber, String customerName, String date) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Booking booking = new Booking(bookingCounter++, customerName, room, date);
                bookings.add(booking);
                return true;
            }
        }
        return false;
    }

    public void showRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}


class Payment {
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + "...");
        System.out.println("Payment successful!");
        return true;
    }
}


public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Payment payment = new Payment();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. View All Rooms\n2. Search Room\n3. Book Room\n4. View Bookings\n5. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                hotel.showRooms();
            } else if (choice == 2) {
                System.out.println("Enter room type (Single/Double/Suite): ");
                String roomType = scanner.next();
                List<Room> availableRooms = hotel.searchRooms(roomType);

                if (availableRooms.isEmpty()) {
                    System.out.println("No rooms available of this type.");
                } else {
                    for (Room room : availableRooms) {
                        System.out.println(room);
                    }
                }
            } else if (choice == 3) {
                System.out.println("Enter room number to book: ");
                int roomNumber = scanner.nextInt();
                System.out.println("Enter your name: ");
                String customerName = scanner.next();
                System.out.println("Enter date of booking (YYYY-MM-DD): ");
                String date = scanner.next();

                boolean isBooked = hotel.bookRoom(roomNumber, customerName, date);
                if (isBooked) {
                    Room bookedRoom = null;
                    for (Room room : hotel.searchRooms("")) {
                        if (room.getRoomNumber() == roomNumber) {
                            bookedRoom = room;
                            break;
                        }
                    }
                    if (bookedRoom != null) {
                        payment.processPayment(bookedRoom.getPrice());
                    }
                    System.out.println("Room booked successfully!");
                } else {
                    System.out.println("Room is unavailable or does not exist.");
                }
            } else if (choice == 4) {
                hotel.viewBookings();
            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
