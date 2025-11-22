import java.util.*;
class Hotel {
    String name;
    ArrayList<Room> rooms = new ArrayList<>();

    Hotel(String name) {
        this.name = name;
    }

    void addRoom(Room r) {
        rooms.add(r);
    }

    void showRooms() {
        System.out.println("\nRooms in " + name + ":");
        for (int i = 0; i < rooms.size(); i++) {
            System.out.print((i + 1) + ". ");
            rooms.get(i).showRoomDetails();
        }
    }

    Room selectRoom(int index) {
        if (index >= 0 && index < rooms.size()) {
            return rooms.get(index);
        }
        return null;
    }
}
