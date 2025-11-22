import java.util.*;
public class MakeMyTripDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Hardcoded data
        City mumbai = new City("Mumbai");
        Hotel taj = new Hotel("Taj Hotel");
        taj.addRoom(new Room("Deluxe", 8000, 5));
        taj.addRoom(new Room("Suite", 15000, 2));
        mumbai.addHotel(taj);

        Hotel oberoi = new Hotel("Oberoi Hotel");
        oberoi.addRoom(new Room("Standard", 5000, 4));
        oberoi.addRoom(new Room("Luxury", 10000, 3));
        mumbai.addHotel(oberoi);

        City delhi = new City("Delhi");
        Hotel lalit = new Hotel("The Lalit");
        lalit.addRoom(new Room("Executive", 7000, 3));
        lalit.addRoom(new Room("Suite", 12000, 2));
        delhi.addHotel(lalit);

        ArrayList<City> cities = new ArrayList<>();
        cities.add(mumbai);
        cities.add(delhi);

        while (true) {
            System.out.println("\n===== MAKE MY TRIP HOTEL BOOKING =====");
            System.out.println("Available Cities:");
            for (int i = 0; i < cities.size(); i++) {
                System.out.println((i + 1) + ". " + cities.get(i).name);
            }
            System.out.println((cities.size() + 1) + ". Exit");
            System.out.print("Choose a city: ");
            int cityChoice = sc.nextInt();

            if (cityChoice == cities.size() + 1) {
                System.out.println("Thank you for using MakeMyTrip Demo! 👋");
                break;
            }

            City selectedCity = cities.get(cityChoice - 1);
            selectedCity.showHotels();

            System.out.print("Select a hotel (0 to go back): ");
            int hotelChoice = sc.nextInt();
            if (hotelChoice == 0) continue;

            Hotel selectedHotel = selectedCity.selectHotel(hotelChoice - 1);
            selectedHotel.showRooms();

            System.out.print("Select a room type to book (0 to go back): ");
            int roomChoice = sc.nextInt();
            if (roomChoice == 0) continue;

            Room selectedRoom = selectedHotel.selectRoom(roomChoice - 1);
            selectedRoom.bookRoom();
        }

        sc.close();
    }
}