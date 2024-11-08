package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Grupo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // controllers

    private GrupoController grupoController = new GrupoController();

    // model

    private StringProperty rutaFichero = new SimpleStringProperty();

    // view

    @FXML
    private TableView<Alumno> alumnoTable;

    @FXML
    private TableColumn<Alumno, LocalDate> birthdateColumn;

    @FXML
    private TextField fileNameText;

    @FXML
    private TableColumn<Alumno, String> nameColumn;

    @FXML
    private BorderPane placeHolderPane;

    @FXML
    private BorderPane root;

    @FXML
    private TableColumn<?, ?> surnameColumn;

    @FXML
    private Tab studentTab;

    @FXML
    private Tab groupTab;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupTab.setContent(grupoController.getRoot());

        // bindings
        rutaFichero.bind(fileNameText.textProperty());

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onNewFileAction(ActionEvent event) {
        grupoController.getGrupo().setAlumnos(null);
        grupoController.getGrupo().setDenominacion("");
        grupoController.getGrupo().setIniCurso(null);
        grupoController.getGrupo().setFinCurso(null);
        grupoController.getGrupo().getPesos().setActitud(0.0);
        grupoController.getGrupo().getPesos().setExamenes(0.0);
        grupoController.getGrupo().getPesos().setPracticas(0.0);

    }

    @FXML
    void onNewStudentAction(ActionEvent event) {

    }

    @FXML
    void onRemoveStudentAction(ActionEvent event) {

    }

    @FXML
    void onSaveAction(ActionEvent event) throws Exception {
        grupoController.getGrupo().save(new File(rutaFichero.get()));
    }
}
