import java.util.*;
class City {
    String name;
    ArrayList<Hotel> hotels = new ArrayList<>();

    City(String name) {
        this.name = name;
    }

    void addHotel(Hotel h) {
        hotels.add(h);
    }

    void showHotels() {
        System.out.println("\nHotels in " + name + ":");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).name);
        }
    }

    Hotel selectHotel(int index) {
        if (index >= 0 && index < hotels.size()) {
            return hotels.get(index);
        }
        return null;
    }
}
