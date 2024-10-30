package Controller;

import Entity.Room;
import Service.RoomService;

public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public void addRoom(Room room) {
        roomService.addRoom(room);
    }

    public void listRooms() {
        roomService.getAll().forEach(room ->
                System.out.println("Room ID: " + room.getId() + ", Type: " + room.getRoomType() + ", Price: " + room.getPricePerHour() + "$"));
    }
}



