package com.example.pslabudp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UDPApplication extends Application {
    private static Stage primaryStage;
    public static Stage getPrimaryStage() {
        return UDPApplication.primaryStage;
    }
    private static void setPrimaryStage(Stage stage) {
        UDPApplication.primaryStage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        setPrimaryStage(stage);
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(UDPApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("UDP Multicast & Broadcast");
        stage.setScene(scene);
        stage.show();

        primaryStage.setOnCloseRequest(event -> {
            closeApp();
        });
    }

    private void closeApp() {
        System.out.println("Zamykanie aplikacji...");
        Platform.exit();
        System.exit(0);
    }
    public static void main(String[] args) {
        launch();
    }
}