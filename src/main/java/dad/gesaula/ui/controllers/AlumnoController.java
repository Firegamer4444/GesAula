package dad.gesaula.ui.controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable {

    // model

    private ObjectProperty<Alumno> alumno = new SimpleObjectProperty<>();

    // view

    @FXML
    private DatePicker birthdate;

    @FXML
    private TextField nameField;

    @FXML
    private CheckBox repiteCheckbox;

    @FXML
    private ChoiceBox<Sexo> sexChoice;

    @FXML
    private TextField surnameField;

    @FXML
    private GridPane root;

    public AlumnoController (){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/alumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // bindings

        alumno.get().nombreProperty().bind(nameField.textProperty());
        alumno.get().apellidosProperty().bind(surnameField.textProperty());
        alumno.get().fechaNacimientoProperty().bind(birthdate.valueProperty());
        alumno.get().sexoProperty().bind(sexChoice.getSelectionModel().selectedItemProperty());
        alumno.get().repiteProperty().bind(repiteCheckbox.selectedProperty());


    }

    public GridPane getRoot() {
        return root;
    }

    public Alumno getAlumno() {
        return alumno.get();
    }

    public ObjectProperty<Alumno> alumnoProperty() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno.set(alumno);
    }
}
