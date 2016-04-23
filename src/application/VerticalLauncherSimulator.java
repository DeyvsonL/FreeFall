package application;

import application.view.VerticalLauncherDisplayController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class VerticalLauncherSimulator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        VerticalLauncherDisplayController.load(primaryStage);
        primaryStage.show();
    }
}
