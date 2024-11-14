package dad.gesaula.ui;

import dad.gesaula.ui.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GesAulaApp extends Application {
    private final RootController rootController = new RootController();
    private final Image icon = new Image(String.valueOf(getClass().getResource("/images/app-icon-64x64.png")));

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(rootController.getRoot());

        stage.setTitle("GesAula");
        stage.setScene(scene);

        stage.getIcons().setAll(icon);

        stage.show();
    }
}
