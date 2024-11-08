package dad.gesaula.ui;

import dad.gesaula.ui.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GesAulaApp extends Application {
    private final RootController rootController = new RootController();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(rootController.getRoot());

        stage.setTitle("GesAula");
        stage.setScene(scene);
        stage.show();
    }
}
