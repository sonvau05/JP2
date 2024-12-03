package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Tải file FXML để tạo giao diện
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        // Tạo Scene từ file FXML
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);

        // Thiết lập tiêu đề cho cửa sổ
        primaryStage.setTitle("JavaFX TextBox và Button");

        // Đặt Scene vào cửa sổ chính và hiển thị
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Chạy ứng dụng JavaFX
    }
}
