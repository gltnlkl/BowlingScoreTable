package com.gulukal.europaceagtaskgu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BowlingScoreApplication extends Application {




    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BowlingScoreApplication.class.getResource("bowlingScore-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 210);
        stage.setScene(scene);
        stage.show();

        //toInitThe Method
        BowlingScoreController controller = fxmlLoader.getController();
        controller.initController();
    }

    public static void main(String[] args) {
        launch();
    }
}