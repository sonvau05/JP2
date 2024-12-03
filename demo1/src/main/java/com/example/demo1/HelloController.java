package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField textField; // Kết nối với TextField trong file FXML

    @FXML
    private Button button; // Kết nối với Button trong file FXML

    @FXML
    private Label welcomeText; // Kết nối với Label trong file FXML

    @FXML
    private void onHelloButtonClick() {
        // Hàm này được gọi khi người dùng nhấn nút "Hiển thị"
        String text = textField.getText(); // Lấy nội dung người dùng nhập từ TextField
        welcomeText.setText("Nội dung: " + text); // Gán nội dung lấy được vào Label
    }
}
