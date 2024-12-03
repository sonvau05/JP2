package Entity;

public class Player {
    // Các thuộc tính của lớp Player được đóng gói (private), không thể truy cập trực tiếp từ bên ngoài lớp
    private String name;  // Tên cầu thủ
    private int age;      // Tuổi cầu thủ
    private String position; // Vị trí chơi

    // Constructor để khởi tạo cầu thủ với các thuộc tính
    public Player(String name, int age, String position) {
        this.name = name;        // Đặt tên cầu thủ
        this.age = age;          // Đặt tuổi cầu thủ
        this.position = position; // Đặt vị trí chơi của cầu thủ
    }

    // Getter và Setter cho các thuộc tính (cho phép truy cập và thay đổi giá trị của các thuộc tính)

    // Phương thức getter cho tên cầu thủ
    public String getName() {
        return name;
    }

    // Phương thức setter cho tên cầu thủ (cho phép thay đổi tên)
    public void setName(String name) {
        this.name = name;
    }

    // Phương thức getter cho tuổi cầu thủ
    public int getAge() {
        return age;
    }

    // Phương thức setter cho tuổi cầu thủ (cho phép thay đổi tuổi)
    public void setAge(int age) {
        this.age = age;
    }

    // Phương thức getter cho vị trí chơi cầu thủ
    public String getPosition() {
        return position;
    }

    // Phương thức setter cho vị trí chơi cầu thủ (cho phép thay đổi vị trí chơi)
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Cầu thủ: " + name + ", Tuổi: " + age + ", Vị trí: " + position;
    }
}

