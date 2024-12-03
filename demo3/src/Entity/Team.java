package Entity;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName; // Tên đội bóng
    private List<Player> players; // Danh sách cầu thủ

    // Constructor khởi tạo đội bóng
    public Team(String teamName) {
        this.teamName = teamName;
        this.players = new ArrayList<>();
    }

    // Phương thức thêm cầu thủ vào đội
    public void addPlayer(Player player) {
        players.add(player);
    }

    // Phương thức hiển thị danh sách cầu thủ trong đội
    public void showPlayers() {
        System.out.println("Danh sách cầu thủ của đội " + teamName + ":");
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
