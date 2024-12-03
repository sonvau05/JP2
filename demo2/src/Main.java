import Entity.Player;
import Entity.Team;

public class Main {

    public static void main(String[] args) {
        // Tạo đối tượng cầu thủ
        Player player1 = new Player("Messi", 36, "Tiền đạo");
        Player player2 = new Player("Ronaldo", 39, "Tiền đạo");

        // Tạo đối tượng đội bóng
        Team team = new Team("Manchester United");

        // Thêm cầu thủ vào đội
        team.addPlayer(player1);
        team.addPlayer(player2);

        // Hiển thị danh sách cầu thủ trong đội
        team.showPlayers();
    }
}
